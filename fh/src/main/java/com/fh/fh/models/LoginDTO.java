package com.fh.fh.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginDTO {

  @NotBlank(message = "username can not be blank")
  private String username;
  @NotBlank(message = "password can not be blank")
  private String password;

  public LoginDTO() {
  }

  public LoginDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
