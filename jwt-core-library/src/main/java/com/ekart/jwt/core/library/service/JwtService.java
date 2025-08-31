package com.ekart.jwt.core.library.service;

import com.ekart.jwt.core.library.model.UserDetails;
import com.ekart.jwt.core.library.util.JwtUtil;

public record JwtService(JwtUtil jwtUtil) {

  public String generateToken(UserDetails userDetails) {
    return jwtUtil.generateToken(userDetails);
  }

}
