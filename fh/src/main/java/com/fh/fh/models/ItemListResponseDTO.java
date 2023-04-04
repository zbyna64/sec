package com.fh.fh.models;

public class ItemListResponseDTO {

  private Long id;
  private String name;
  private Double startingPrice;

  public ItemListResponseDTO(Long id, String name, Double startingPrice) {
    this.id = id;
    this.name = name;
    this.startingPrice = startingPrice;
  }

  public ItemListResponseDTO(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.startingPrice = item.getStartingPrice();
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
    return startingPrice;
  }

  public void setStartingPrice(Double startingPrice) {
    this.startingPrice = startingPrice;
  }
}
