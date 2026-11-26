package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepo organizationRepo;

    public OrganizationService(OrganizationRepo organizationRepository) {
        this.organizationRepo = organizationRepository;
    }

    public Organization register(Organization org) {
        if (org.getOrgName() == null || org.getOrgName().isBlank()
                || org.getOrgEmail() == null || org.getOrgEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "orgName and orgEmail are required");
        }
        if (organizationRepo.existsByOrgEmail(org.getOrgEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }
        return organizationRepo.save(org);
    }

    public List<Organization> getAll() {
        return organizationRepo.findAll();
    }

    public Organization get(Long id) {
        return organizationRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found"));
    }
}