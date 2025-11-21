package com.tcs.retotecnico.jwt.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TokenInfo {
    private final Instant expiresAt;
    private boolean used;

    public TokenInfo(Instant expiresAt) {
        this.expiresAt = expiresAt;
        this.used = false;
    }
}
