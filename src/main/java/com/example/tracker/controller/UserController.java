package com.example.tracker.controller;

import com.example.tracker.model.User;
import com.example.tracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 👉 ONLY ONE CONSTRUCTOR
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Email is already registered!");
        }

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        User saved = userService.saveUser(user);
        return ResponseEntity.ok(saved);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginData) {

        return userService.findByEmail(loginData.getEmail())
                .map(user -> {

                    if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
                        return ResponseEntity.ok(user);
                    } else {
                        return ResponseEntity.status(401).body("Invalid password!");
                    }
                })
                .orElse(ResponseEntity.status(404).body("User not found!"));
    }
    
 // DELETE USER BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    userService.deleteUserById(id);
                    return ResponseEntity.ok("User deleted successfully!");
                })
                .orElse(ResponseEntity.status(404).body("User not found!"));
    }

}
