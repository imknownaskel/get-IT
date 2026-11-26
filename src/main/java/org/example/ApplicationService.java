package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepo applicationRepo;
    private final InternRepo internRepo;
    private final InternshipRepo internshipRepo;

    public ApplicationService(ApplicationRepo applicationRepo,
                              InternRepo internRepo,
                              InternshipRepo internshipRepo) {
        this.applicationRepo = applicationRepo;
        this.internRepo = internRepo;
        this.internshipRepo = internshipRepo;
    }

    public Application apply(Long internId, Long internshipId, String coverLetter) {
        Intern intern = internRepo.findById(internId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Intern not found"));

        Internship internship = internshipRepo.findById(internshipId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Internship not found"));

        if (!internship.isOpen()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This internship is closed");
        }

        if (applicationRepo.existsByInternIntIDAndInternshipInternshipID(internId, internshipId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You have already applied to this internship");
        }

        return applicationRepo.save(new Application(intern, internship, coverLetter));
    }

    public List<Application> getByIntern(Long internId) {
        return applicationRepo.findByInternIntID(internId);
    }

    public List<Application> getByInternship(Long internshipId) {
        return applicationRepo.findByInternshipInternshipID(internshipId);
    }

    public Application updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        application.setStatus(status);
        return applicationRepo.save(application);
    }
}