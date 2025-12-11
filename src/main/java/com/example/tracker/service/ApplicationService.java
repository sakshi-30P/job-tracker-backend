package com.example.tracker.service;

import com.example.tracker.model.Application;
import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Application saveApplication(Application application);
    Optional<Application> findById(Long id);
    List<Application> findAll();
    List<Application> findByUserId(Long userId);
    void deleteApplication(Long id);
}
