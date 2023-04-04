package com.fh.fh.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.Instant;
import org.springframework.format.annotation.DateTimeFormat;

@JsonPropertyOrder({"username", "dollars", "token"})
public class LoginSuccesDTO {


  private String username;
  @JsonProperty("JWT token:")
  @DateTimeFormat
  private String token;
  @JsonProperty("Green-bay dollars: $ ")
  private Long dollars;

  public LoginSuccesDTO(String username, String token, Long dollars) {
    this.username = username;
    this.token = token;
    this.dollars = dollars;
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

  public Long getDollars() {
    return dollars;
  }

  public void setDollars(Long dollars) {
    this.dollars = dollars;
  }
}
