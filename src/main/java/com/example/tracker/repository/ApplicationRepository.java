package com.example.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tracker.model.Application;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUser_Id(Long userId);
    List<Application> findByJob_Id(Long jobId);
}
