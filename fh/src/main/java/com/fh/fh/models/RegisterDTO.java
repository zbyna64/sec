package com.fh.fh.models;

import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class RegisterDTO {
  @Length(min = 4, message = "Length must be atleast 4 characters")
  private String username;
  @Length(min = 4, message = "Length must be atleast 4 characters")
  private String password;

  public RegisterDTO() {
  }

  public RegisterDTO(String username, String password) {
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
