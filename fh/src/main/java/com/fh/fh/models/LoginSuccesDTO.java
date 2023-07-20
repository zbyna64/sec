package com.fh.fh.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;

@JsonPropertyOrder({"username", "token"})
public class LoginSuccesDTO {


  private String username;
  @JsonProperty("JWT token:")
  @DateTimeFormat
  private String token;

  public LoginSuccesDTO(String username, String token) {
    this.username = username;
    this.token = token;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
