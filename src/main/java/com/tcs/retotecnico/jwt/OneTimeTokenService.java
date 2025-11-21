package com.tcs.retotecnico.jwt;

import com.tcs.retotecnico.jwt.model.TokenInfo;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OneTimeTokenService {

    private final Map<String, TokenInfo> tokens = new ConcurrentHashMap<>();

    // Generar token de un solo uso, válido por X segundos
    public String generateToken(long ttlSeconds) {
        String token = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plusSeconds(ttlSeconds);
        tokens.put(token, new TokenInfo(expiresAt));
        return token;
    }

    // Consumir token: true si es válido y se marca usado, false si no
    public boolean consumeToken(String token) {
        TokenInfo info = tokens.get(token);
        if (info == null) {
            return false;
        }
        synchronized (info) { // para evitar condiciones de carrera
            if (info.isUsed()) {
                return false;
            }
            if (Instant.now().isAfter(info.getExpiresAt())) {
                return false;
            }
            info.setUsed( true); // lo marcamos como usado
            return true;
        }
    }
}
