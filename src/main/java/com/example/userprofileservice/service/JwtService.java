package com.example.userprofileservice.service;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    public String generateToken(String userId, String signingKey) {
        SecretKey key = new SecretKeySpec(
                signingKey.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }
}
