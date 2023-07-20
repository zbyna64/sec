package com.fh.fh.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class TokenResponse {

    @JsonProperty(value = "JWT token")
    private String token;
    @JsonProperty(value = "expires at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Prague")
    private Instant expiresAt;

    public TokenResponse(Instant expiresAt) {
        this.expiresAt = expiresAt;
        this.token = null;
    }


    public TokenResponse(String token, Instant expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
