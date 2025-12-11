package com.example.tracker.controller;

import com.example.tracker.model.Application;
import com.example.tracker.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) { this.applicationService = applicationService; }

    // Apply for a job
    @PostMapping
    public ResponseEntity<Application> applyJob(@RequestBody Application application) {
        application.setAppliedDate(LocalDate.now());
        application.setStatus("Applied");
        return ResponseEntity.ok(applicationService.saveApplication(application));
    }

    // Get all applications
    @GetMapping
    public List<Application> getAllApplications() { return applicationService.findAll(); }

    // Get applications by user
    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUser(@PathVariable Long userId) {
        return applicationService.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("Application deleted successfully");
    }
}
