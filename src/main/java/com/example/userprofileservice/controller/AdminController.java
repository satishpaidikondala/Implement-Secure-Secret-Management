package com.example.userprofileservice.controller;

import com.example.userprofileservice.dto.VaultStatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Value("${db.username}")
    private String dbUsername;

    @GetMapping("/vault-status")
    public ResponseEntity<VaultStatusResponse> vaultStatus() {
        VaultStatusResponse response = new VaultStatusResponse(
                true,
                "secret/user-profile-service",
                dbUsername
        );
        return ResponseEntity.ok(response);
    }
}
