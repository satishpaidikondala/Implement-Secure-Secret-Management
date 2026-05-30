package com.example.userprofileservice.controller;

import com.example.userprofileservice.model.UserProfile;
import com.example.userprofileservice.repository.UserProfileRepository;
import com.example.userprofileservice.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private final UserProfileRepository repository;
    private final JwtService jwtService;

    @Value("${api.signing-key}")
    private String signingKey;

    public ProfileController(UserProfileRepository repository, JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    @PostMapping("/profile")
    public ResponseEntity<UserProfile> createProfile(@RequestBody UserProfile profile) {
        UserProfile saved = repository.save(profile);
        String token = jwtService.generateToken(saved.getUserId(), signingKey);
        return ResponseEntity
                .status(201)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(saved);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable String userId) {
        return repository.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
