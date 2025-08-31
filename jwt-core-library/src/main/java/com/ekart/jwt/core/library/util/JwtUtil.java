package com.ekart.jwt.core.library.util;

import com.ekart.jwt.core.library.model.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Objects;

public class JwtUtil {

  private final Key secretKey;
  private final long tokenValidityInMs;

  public JwtUtil(String secretKey, long tokenValidityInMs) {
    if (secretKey == null || secretKey.length() < 32) {
      throw new IllegalArgumentException("Secret key must be at least 256 bits (32 chars base64).");
    }
    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    this.tokenValidityInMs = tokenValidityInMs;
  }

  public String generateToken(UserDetails userDetails) {
    Objects.requireNonNull(userDetails, "userDetails must not be null");
    return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .claim("role", userDetails.getRole())  // Add role as a custom claim
            .setIssuer("eKart")
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + tokenValidityInMs)) // 1 hour
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
  }
}
