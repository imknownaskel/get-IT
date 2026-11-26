package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // body: { "internId": 1, "internshipId": 1, "coverLetter": "..." }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Application apply(@RequestBody Map<String, String> body) {
        Long internId = Long.valueOf(body.get("internId"));
        Long internshipId = Long.valueOf(body.get("internshipId"));
        return applicationService.apply(internId, internshipId, body.get("coverLetter"));
    }

    // an intern tracking their own applications
    @GetMapping("/intern/{internId}")
    public List<Application> byIntern(@PathVariable Long internId) {
        return applicationService.getByIntern(internId);
    }

    // an organization seeing who applied to one of its listings
    @GetMapping("/internship/{internshipId}")
    public List<Application> byInternship(@PathVariable Long internshipId) {
        return applicationService.getByInternship(internshipId);
    }

    // PATCH /api/applications/1/status?status=SHORTLISTED
    @PatchMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status) {
        return applicationService.updateStatus(id, status);
    }
}