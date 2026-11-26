package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    // POST /api/internships?orgId=1
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Internship create(@RequestParam Long orgId, @RequestBody Internship internship) {
        return internshipService.create(orgId, internship);
    }

    // GET /api/internships?field=software&maxDuration=6&location=abuja&skills=react,java
    @GetMapping
    public List<Internship> search(@RequestParam(required = false) String field,
                                   @RequestParam(required = false) Integer maxDuration,
                                   @RequestParam(required = false) String location,
                                   @RequestParam(required = false) String skills) {
        return internshipService.search(field, maxDuration, location, skills);
    }

    @GetMapping("/{id}")
    public Internship get(@PathVariable Long id) {
        return internshipService.get(id);
    }

    // all listings by one organization (open and closed)
    @GetMapping("/organization/{orgId}")
    public List<Internship> byOrganization(@PathVariable Long orgId) {
        return internshipService.getByOrganization(orgId);
    }

    @PatchMapping("/{id}/close")
    public Internship close(@PathVariable Long id) {
        return internshipService.close(id);
    }
}