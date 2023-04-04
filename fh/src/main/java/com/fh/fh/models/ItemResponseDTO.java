package com.fh.fh.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemResponseDTO {

  private Long id;
  private String name;
  private String description;
  private Double startingPrice;
  private Double purchasePrice;

  public ItemResponseDTO() {
  }

  public ItemResponseDTO(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.description = item.getDescription();
    this.startingPrice = item.getStartingPrice();
    this.purchasePrice = item.getPurchasePrice();
  }

  public ItemResponseDTO(Long id, String name, String description, Double startingPrice, Double purchasePrice) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
