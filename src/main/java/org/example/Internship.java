package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "internships")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internshipID;

    // many internships belong to one organization
    @ManyToOne(optional = false)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 80)
    private String field;

    @Column(name = "duration_months", nullable = false)
    private int durationMonths;

    // creates a separate table: internship_skills (internship_id, skill)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "internship_skills", joinColumns = @JoinColumn(name = "internship_id"))
    @Column(name = "skill", length = 80)
    private List<String> requiredSkills = new ArrayList<>();

    @Column(length = 4000)
    private String description;

    @Column(name = "is_open", nullable = false)
    private boolean open = true;

    public Internship() {
    }

    public Internship(Organization organization) {
        this.organization = organization;
    }

    public Long getInternshipID() {
        return internshipID;
    }

    public Organization getOrganization() {
        return organization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void addRequiredSkill(String skill) {
        this.requiredSkills.add(skill);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}