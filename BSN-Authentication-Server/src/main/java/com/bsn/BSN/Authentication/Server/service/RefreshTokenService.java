package com.makedsaeasy.service;


public interface RefreshTokenService {
    void createRefreshToken(String username, String token);
    boolean validateRefreshToken(String username, String token);
    void revokeRefreshToken(String token);
}