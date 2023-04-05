package com.fh.fh.models;

public class ItemListResponseDTO {

  private Long id;
  private String name;
  private Double purchasePrice;

  public ItemListResponseDTO(Long id, String name, Double purchasePrice) {
    this.id = id;
    this.name = name;
    this.purchasePrice = purchasePrice;
  }

  public ItemListResponseDTO(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.purchasePrice = item.getPurchasePrice();
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

  public Double getStartingPrice() {
    return purchasePrice;
  }

  public void setStartingPrice(Double startingPrice) {
    this.purchasePrice = startingPrice;
  }
}
