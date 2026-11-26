package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepo extends JpaRepository<Internship, Long> {

    List<Internship> findByOpenTrue();

    List<Internship> findByOrganizationOrgID(Long orgID);
}