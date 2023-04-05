package com.fh.fh.models;


public class BidDTO {

  private Double bidPrice;
  private String username;

  public BidDTO(Double bidPrice, User user) {
    this.bidPrice = bidPrice;
    this.username = user.getUsername();
  }

  public BidDTO(Bid bid) {
    this.bidPrice = bid.getBidPrice();
    this.username = bid.getUser().getUsername();
  }

  public Double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
