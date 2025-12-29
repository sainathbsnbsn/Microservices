package com.makedsaeasy.service.impl;


import com.makedsaeasy.backend.repository.RefreshTokenRepository;
import com.makedsaeasy.backend.model.RefreshToken;
import com.makedsaeasy.backend.service.RefreshTokenService;
import org.springframework.stereotype.Service;


import java.time.OffsetDateTime;
import java.util.Optional;


@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {


    private final RefreshTokenRepository repo;


    public RefreshTokenServiceImpl(RefreshTokenRepository repo) {
        this.repo = repo;
    }


    @Override
    public void createRefreshToken(String username, String token) {
        RefreshToken rt = new RefreshToken();
        rt.setUsername(username);
        rt.setToken(token);
        rt.setCreatedAt(OffsetDateTime.now());
        repo.save(rt);
    }


    @Override
    public boolean validateRefreshToken(String username, String token) {
        Optional<RefreshToken> r = repo.findByToken(token);
        return r.isPresent() && r.get().getUsername().equals(username);
    }


    @Override
    public void revokeRefreshToken(String token) {
        repo.findByToken(token).ifPresent(repo::delete);
    }
}