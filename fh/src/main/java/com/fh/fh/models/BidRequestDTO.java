package com.fh.fh.models;


import javax.validation.constraints.Min;

public class BidRequestDTO {

  @Min(value = 0, message = "Bid must be positive number")
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
