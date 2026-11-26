package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {

    boolean existsByOrgEmail(String orgEmail);
}