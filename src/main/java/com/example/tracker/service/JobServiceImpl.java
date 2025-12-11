package com.example.tracker.service;

import com.example.tracker.model.Job;
import com.example.tracker.repository.JobRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) { this.jobRepository = jobRepository; }

    @Override
    public Job saveJob(Job job) { return jobRepository.save(job); }

    @Override
    public Optional<Job> findById(Long id) { return jobRepository.findById(id); }

    @Override
    public List<Job> findAll() { return jobRepository.findAll(); }

    @Override
    public void deleteJob(Long id) { jobRepository.deleteById(id); }
}
