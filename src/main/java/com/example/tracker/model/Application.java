package com.example.tracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Job applied to
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    // User who applied
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate appliedDate;
    private String status; // Applied, Shortlisted, etc.

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
