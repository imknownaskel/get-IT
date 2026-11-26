package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InternRepo extends JpaRepository<Intern, Long> {

    boolean existsByEmail(String email);
}