package com.makedsaeasy.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(unique = true, nullable = false)
    private String username;


    @Column(unique = true)
    private String email;


    @Column(name = "password_hash")
    private String passwordHash;


    @Column(name = "display_name")
    private String displayName;


    private String role = "USER";


    @Column(name = "created_at")
    private OffsetDateTime createdAt;


    @Column(name = "last_seen")
    private OffsetDateTime lastSeen;


// getters/setters omitted for brevity


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }


    @Override
    public String getPassword() { return passwordHash; }


    @Override
    public String getUsername() { return username; }


    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}