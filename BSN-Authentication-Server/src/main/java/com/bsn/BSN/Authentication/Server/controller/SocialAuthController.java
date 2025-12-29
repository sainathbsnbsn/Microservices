package com.makedsaeasy.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makedsaeasy.backend.config.JwtUtil;
import com.makedsaeasy.backend.dto.AuthResponse;
import com.makedsaeasy.backend.model.User;
import com.makedsaeasy.backend.repository.UserRepository;
import com.makedsaeasy.backend.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/social")
public class SocialAuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${facebook.app-id}")
    private String facebookAppId;

    public SocialAuthController(UserRepository userRepository, JwtUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    // Mobile flow: client sends Google ID token (JWT) obtained from Google Sign-In SDK on device
    @PostMapping("/google")
    public ResponseEntity<AuthResponse> googleSignIn(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");
        // Verify token by calling Google's tokeninfo endpoint or using Google API libs.
        RestTemplate rt = new RestTemplate();
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
        String res = rt.getForObject(url, String.class);
        try {
            JsonNode node = objectMapper.readTree(res);
            String aud = node.get("aud").asText();
            if (!aud.equals(googleClientId)) return ResponseEntity.status(401).build();
            String email = node.has("email") ? node.get("email").asText() : null;
            String sub = node.has("sub") ? node.get("sub").asText() : null; // google user id
            String username = email != null ? email : "google_" + sub;
            User user = userRepository.findByUsername(username).orElseGet(() -> {
                User u = new User();
                u.setUsername(username);
                u.setEmail(email);
                u.setCreatedAt(java.time.OffsetDateTime.now());
                return userRepository.save(u);
            });
            String access = jwtUtil.generateAccessToken(user.getUsername());
            String refresh = jwtUtil.generateRefreshToken(user.getUsername());
            refreshTokenService.createRefreshToken(user.getUsername(), refresh);
            return ResponseEntity.ok(new AuthResponse(access, refresh));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    // Mobile flow: client sends Facebook access token obtained from FB SDK on device
    @PostMapping("/facebook")
    public ResponseEntity<AuthResponse> facebookSignIn(@RequestBody Map<String, String> body) {
        String accessToken = body.get("accessToken");
        RestTemplate rt = new RestTemplate();
        // Validate token and get user profile
        String debugUrl = String.format("https://graph.facebook.com/debug_token?input_token=%s&access_token=%s|%s", accessToken, facebookAppId, "YOUR_APP_SECRET");
        // For simplicity we will call /me?fields=id,email
        String meUrl = "https://graph.facebook.com/me?fields=id,email&access_token=" + accessToken;
        try {
            String res = rt.getForObject(meUrl, String.class);
            JsonNode node = objectMapper.readTree(res);
            String id = node.get("id").asText();
            String email = node.has("email") ? node.get("email").asText() : null;
            String username = email != null ? email : "facebook_" + id;
            User user = userRepository.findByUsername(username).orElseGet(() -> {
                User u = new User();
                u.setUsername(username);
                u.setEmail(email);
                u.setCreatedAt(java.time.OffsetDateTime.now());
                return userRepository.save(u);
            });
            String access = jwtUtil.generateAccessToken(user.getUsername());
            String refresh = jwtUtil.generateRefreshToken(user.getUsername());
            refreshTokenService.createRefreshToken(user.getUsername(), refresh);
            return ResponseEntity.ok(new AuthResponse(access, refresh));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}