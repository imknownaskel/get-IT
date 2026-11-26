package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InternService {

    private final InternRepo internRepo;

    public InternService(InternRepo internRepository) {
        this.internRepo = internRepository;
    }

    public Intern register(Intern intern) {
        if (intern.getFirstName() == null || intern.getFirstName().isBlank()
                || intern.getLastName() == null || intern.getLastName().isBlank()
                || intern.getEmail() == null || intern.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "firstName, lastName and email are required");
        }
        if (internRepo.existsByEmail(intern.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }
        return internRepo.save(intern);
    }

    public Intern get(Long id) {
        return internRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Intern not found"));
    }
}