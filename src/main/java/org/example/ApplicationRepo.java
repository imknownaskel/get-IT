package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepo extends JpaRepository<Application, Long> {

    List<Application> findByInternIntID(Long intID);

    List<Application> findByInternshipInternshipID(Long internshipID);

    boolean existsByInternIntIDAndInternshipInternshipID(Long intID, Long internshipID);
}