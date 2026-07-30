package com.veterinaria.gestion_mascotas.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "clave_super_secreta_veterinaria_2026";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String numLicencia) {
        return Jwts.builder()
                .subject(numLicencia)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey())
                .compact();
    }

    public String extractNumLicencia(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, String numLicencia) {
        String tokenNumLicencia = extractNumLicencia(token);
        return tokenNumLicencia.equals(numLicencia) && !isTokenExpired(token);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
