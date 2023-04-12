package com.fh.fh.models;

public class BalanceResponseDTO {

  private Double amount;
  private String username;

  public BalanceResponseDTO() {
  }

  public BalanceResponseDTO(User user) {
    this.username = user.getUsername();
    this.amount = user.getDollar().getAmount();
  }
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
