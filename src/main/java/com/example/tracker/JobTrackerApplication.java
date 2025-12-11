package com.example.tracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.tracker.model.User;
import com.example.tracker.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JobTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobTrackerApplication.class, args);
    }

    // Create a default admin user on startup if not exists
    @Bean
    CommandLineRunner init(UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if admin already exists
            if (userService.findByEmail("admin@gmail.com").isEmpty()) {
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                userService.saveUser(admin);
                System.out.println("✅ Default admin created -> admin@gmail.com / admin123");
            } else {
                System.out.println("ℹ️ Admin already exists, skipping creation");
            }
        };
    }
}
