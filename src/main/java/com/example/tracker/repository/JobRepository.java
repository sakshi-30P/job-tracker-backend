package com.example.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tracker.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {}
