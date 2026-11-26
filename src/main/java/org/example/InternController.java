package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interns")
public class InternController {

    private final InternService internService;

    public InternController(InternService internService) {
        this.internService = internService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Intern register(@RequestBody Intern intern) {
        return internService.register(intern);
    }

    @GetMapping("/{id}")
    public Intern get(@PathVariable Long id) {
        return internService.get(id);
    }
}