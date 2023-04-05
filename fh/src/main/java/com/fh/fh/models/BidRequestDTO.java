package com.fh.fh.models;


public class BidRequestDTO {

  private Double bidPrice;

  public BidRequestDTO() {
  }

  public BidRequestDTO(Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(Double bidPrice) {
    this.bidPrice = bidPrice;
  }
}
