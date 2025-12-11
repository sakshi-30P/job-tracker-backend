package com.example.tracker.controller;

import com.example.tracker.model.Job;
import com.example.tracker.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) { this.jobService = jobService; }

    @GetMapping
    public List<Job> getAllJobs() { return jobService.findAll(); }

    @PostMapping
    public ResponseEntity<Job> addJob(@RequestBody Job job) {
        return ResponseEntity.ok(jobService.saveJob(job));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        return jobService.findById(id)
                .map(job -> {
                    job.setCompanyName(jobDetails.getCompanyName());
                    job.setPosition(jobDetails.getPosition());
                    job.setDescription(jobDetails.getDescription());
                    job.setLocation(jobDetails.getLocation());
                    job.setCompanyLogo(jobDetails.getCompanyLogo());
                    return ResponseEntity.ok(jobService.saveJob(job));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }
}
