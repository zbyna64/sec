package com.fh.fh.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.Instant;
import org.springframework.format.annotation.DateTimeFormat;

@JsonPropertyOrder({"username", "validity", "token"})
public class LoginSuccesDTO {


  private String username;
  @JsonProperty("JWT token:")
  @DateTimeFormat
  private String token;
  @JsonProperty("Valid until:")
  private Instant validity;

  public LoginSuccesDTO(String username, String token, Instant validity) {
    this.username = username;
    this.token = token;
    this.validity = validity;
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

  public Instant getValidity() {
    return validity;
  }

  public void setValidity(Instant validity) {
    this.validity = validity;
  }
}
