package com.ekart.jwt.core.library.util;

import com.ekart.jwt.core.library.model.UserDetails;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;


class JwtUtilTest {

  private final String VALID_SECRET = Base64.getEncoder().encodeToString("my-very-secure-and-long-secret-key-123456".getBytes());
  private static final long TOKEN_VALIDITY = 3600000L; // 1 hour

    @Test
    void testGenerateTokenReturnsNonNullToken() {
        JwtUtil jwtUtil = new JwtUtil(VALID_SECRET, TOKEN_VALIDITY);
        UserDetails userDetails = new UserDetails("testuser", "admin");
        String token = jwtUtil.generateToken(userDetails);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testConstructorThrowsExceptionForShortSecret() {
        String shortSecret = "short";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new JwtUtil(shortSecret, TOKEN_VALIDITY));
        assertTrue(exception.getMessage().contains("Secret key must be at least 256 bits"));
    }
}