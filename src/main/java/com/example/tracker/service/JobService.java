package com.example.tracker.service;

import com.example.tracker.model.Job;
import java.util.List;
import java.util.Optional;

public interface JobService {
    Job saveJob(Job job);
    Optional<Job> findById(Long id);
    List<Job> findAll();
    void deleteJob(Long id);
}
