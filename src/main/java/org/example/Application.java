package org.example;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications", uniqueConstraints = {
        @UniqueConstraint(name = "uq_intern_internship", columnNames = {"intern_id", "internship_id"})
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "intern_id", nullable = false)
    private Intern intern;

    @ManyToOne(optional = false)
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship;

    @Column(name = "cover_letter", length = 4000)
    private String coverLetter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ApplicationStatus status = ApplicationStatus.SUBMITTED;

    @Column(name = "date_applied", updatable = false)
    private LocalDateTime dateApplied;

    public Application() {
    }

    public Application(Intern intern, Internship internship, String coverLetter) {
        this.intern = intern;
        this.internship = internship;
        this.coverLetter = coverLetter;
        this.dateApplied = LocalDateTime.now();
    }

    public Long getAppID() {
        return appID;
    }

    public Intern getIntern() {
        return intern;
    }

    public Internship getInternship() {
        return internship;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateApplied() {
        return dateApplied;
    }
}