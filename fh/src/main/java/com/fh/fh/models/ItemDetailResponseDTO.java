package com.fh.fh.models;

import java.util.List;
import org.springframework.lang.Nullable;

public class ItemDetailResponseDTO {
  private Long id;
  private String name;
  private String description;
  private Double sellingPrice;
  private Double startingPrice;
  private List<BidDTO> bids;
  @Nullable
  private String seller;
  private String buyer;

  public ItemDetailResponseDTO() {
  }

  public ItemDetailResponseDTO(Item item, List<BidDTO> bids) {
    this.id = item.getId();
    this.name = item.getName();
    this.description = item.getDescription();
    this.bids = bids;
    this.sellingPrice = item.getPurchasePrice();
    this.startingPrice = item.getStartingPrice();
    this.seller = item.getSeller().getUsername();
    if (item.getBuyer() != null) {
      this.buyer = item.getBuyer().getUsername();
    }

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

  public List<BidDTO> getBids() {
    return bids;
  }

  public void setBids(List<BidDTO> bids) {
    this.bids = bids;
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public Double getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(Double startingPrice) {
    this.startingPrice = startingPrice;
  }
}
