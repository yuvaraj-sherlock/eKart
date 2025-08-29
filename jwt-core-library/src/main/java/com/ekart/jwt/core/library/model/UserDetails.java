package com.ekart.jwt.core.library.model;

import lombok.Getter;

@Getter
public class UserDetails {
  private final String username;
  private final String role;

  public UserDetails(String username, String role) {
    this.username = username;
    this.role = role;
  }

}
