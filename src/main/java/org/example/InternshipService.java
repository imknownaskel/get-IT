package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternshipService {

    private final InternshipRepo internshipRepo;
    private final OrganizationRepo organizationRepo;

    public InternshipService(InternshipRepo internshipRepository,
                             OrganizationRepo organizationRepository) {
        this.internshipRepo = internshipRepository;
        this.organizationRepo = organizationRepository;
    }

    public Internship create(Long orgID, Internship data) {
        if (data.getTitle() == null || data.getTitle().isBlank()
                || data.getField() == null || data.getField().isBlank()
                || data.getDurationMonths() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "title, field and durationMonths (at least 1) are required");
        }

        Organization org = organizationRepo.findById(orgID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found"));

        Internship internship = new Internship(org);
        internship.setTitle(data.getTitle().trim());
        internship.setField(data.getField().trim());
        internship.setDurationMonths(data.getDurationMonths());
        internship.setRequiredSkills(data.getRequiredSkills());
        internship.setDescription(data.getDescription());
        return internshipRepo.save(internship);
    }

    public Internship get(Long id) {
        return internshipRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Internship not found"));
    }

    public List<Internship> getByOrganization(Long orgID) {
        return internshipRepo.findByOrganizationOrgID(orgID);
    }

    public Internship close(Long id) {
        Internship internship = get(id);
        internship.setOpen(false);
        return internshipRepo.save(internship);
    }

    // every filter is optional; a filter is only applied if a value was given
    public List<Internship> search(String field, Integer maxDuration, String location, String skills) {
        List<Internship> results = new ArrayList<>();

        for (Internship i : internshipRepo.findByOpenTrue()) {
            if (!isBlank(field) && !contains(i.getField(), field)) {
                continue;
            }
            if (maxDuration != null && i.getDurationMonths() > maxDuration) {
                continue;
            }
            if (!isBlank(location) && !contains(i.getOrganization().getOrgAddress(), location)) {
                continue;
            }
            if (!isBlank(skills) && !hasAnySkill(i, skills)) {
                continue;
            }
            results.add(i);
        }
        return results;
    }

    // "react, java" -> true if the internship requires react OR java
    private boolean hasAnySkill(Internship internship, String skills) {
        for (String wanted : skills.split(",")) {
            for (String required : internship.getRequiredSkills()) {
                if (required.trim().equalsIgnoreCase(wanted.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean contains(String text, String part) {
        return text != null && text.toLowerCase().contains(part.trim().toLowerCase());
    }

    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}