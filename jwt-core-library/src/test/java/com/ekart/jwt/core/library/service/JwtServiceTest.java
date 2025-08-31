package com.ekart.jwt.core.library.service;

import com.ekart.jwt.core.library.model.UserDetails;
import com.ekart.jwt.core.library.util.JwtUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    @Test
    void testGenerateTokenDelegatesToJwtUtil() {
        JwtUtil jwtUtil = mock(JwtUtil.class);
        JwtService jwtService = new JwtService(jwtUtil);
        UserDetails userDetails = new UserDetails("testuser", "admin");
        String expectedToken = "mockedToken";

        when(jwtUtil.generateToken(userDetails)).thenReturn(expectedToken);

        String actualToken = jwtService.generateToken(userDetails);

        assertEquals(expectedToken, actualToken);
        verify(jwtUtil, times(1)).generateToken(userDetails);
    }
}
