package com.makedsaeasy.controller;

import com.makedsaeasy.backend.config.JwtUtil;
import com.makedsaeasy.backend.dto.AuthRequest;
import com.makedsaeasy.backend.dto.AuthResponse;
import com.makedsaeasy.backend.model.User;
import com.makedsaeasy.backend.repository.UserRepository;
import com.makedsaeasy.backend.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;


    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        String access = jwtUtil.generateAccessToken(req.username());
        String refresh = jwtUtil.generateRefreshToken(req.username());
        refreshTokenService.createRefreshToken(req.username(), refresh);
        return ResponseEntity.ok(new AuthResponse(access, refresh));
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest req) {
        if (userRepository.findByUsername(req.username()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User u = new User();
        u.setUsername(req.username());
        u.setPasswordHash(passwordEncoder.encode(req.password()));
        u.setCreatedAt(OffsetDateTime.now());
        userRepository.save(u);
        String access = jwtUtil.generateAccessToken(req.username());
        String refresh = jwtUtil.generateRefreshToken(req.username());
        refreshTokenService.createRefreshToken(req.username(), refresh);
        return ResponseEntity.ok(new AuthResponse(access, refresh));
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) return ResponseEntity.status(401).build();
        String username = jwtUtil.extractUsername(refreshToken);
        if (!refreshTokenService.validateRefreshToken(username, refreshToken)) return ResponseEntity.status(401).build();
        String newAccess = jwtUtil.generateAccessToken(username);
        String newRefresh = jwtUtil.generateRefreshToken(username);
        refreshTokenService.createRefreshToken(username, newRefresh);
        return ResponseEntity.ok(new AuthResponse(newAccess, newRefresh));
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody String refreshToken) {
        refreshTokenService.revokeRefreshToken(refreshToken);
        return ResponseEntity.ok().build();
    }
}