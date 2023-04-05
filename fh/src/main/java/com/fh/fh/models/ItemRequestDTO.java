package com.fh.fh.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemRequestDTO {

  @NotBlank(message = "Field name is missing")
  private String name;
  @NotBlank(message = "Field description is missing")
  private String description;
  @Min(value = 0, message = "price must be positive number")
  @NotNull(message = "Starting price must have value assigned")
  private Double startingPrice;
  @Min(value = 0, message = "price must be positive number")
  @NotNull(message = "Purchase price must have value assigned")
  private Double purchasePrice;

  public ItemRequestDTO() {
  }

  public ItemRequestDTO(String description, Double startingPrice, Double purchasePrice) {
    this.description = description;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
  }
  public ItemRequestDTO(String name, String description, Double startingPrice, Double purchasePrice) {
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
