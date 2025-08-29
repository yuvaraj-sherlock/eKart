package com.ekart.jwt.core.library.util;

import com.ekart.jwt.core.library.model.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Objects;

public class JwtUtil {
  private final String secretKey;
  private final long tokenValidityInMs;

  public JwtUtil(String secretKey, long tokenValidityInMs) {
    this.secretKey = secretKey;
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
