package com.fh.fh.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ItemDTO {

  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @Min(value = 0, message = "price must be positive number")
  private Double startingPrice;
  @Min(value = 0, message = "price must be positive number")
  private Double purchasePrice;

  public ItemDTO() {
  }

  public ItemDTO(String name, String description, Double startingPrice, Double purchasePrice) {
    this.name = name;
    this.description = description;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(Double startingPrice) {
    this.startingPrice = startingPrice;
  }

  public Double getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }
}
