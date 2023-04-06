package com.fh.fh.services;

import com.fh.fh.models.Bid;
import com.fh.fh.models.BidDTO;
import com.fh.fh.models.Item;
import com.fh.fh.models.User;
import com.fh.fh.repositories.BidRepository;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BidService {

  private final BidRepository bidRepository;

  @Autowired
  public BidService(BidRepository bidRepository) {
    this.bidRepository = bidRepository;
  }

  public Bid createBid(User user, Item item, Double bidPrice) {
    Bid bid = new Bid(user, item, bidPrice);
    return bidRepository.save(bid);
  }

  public List<Bid> listBidsForItemByPriceDesc(Item item) {
    return bidRepository.findAllByItem(item, Sort.by("bidPrice").descending());
  }

  public List<BidDTO> convertToBidDTOList(Item item) {
    return listBidsForItemByPriceDesc(item)
        .stream()
        .map(BidDTO::new)
        .collect(Collectors.toList());
  }

  public Double listHighestBidForItem(Item item) {
    return listBidsForItemByPriceDesc(item).stream()
        .findFirst()
        .map(Bid::getBidPrice)
        .orElse(item.getStartingPrice() - 0.1);
  }

  public void isBidHigherThanLastBid(Item item, Double bidPrice) {
    if (listHighestBidForItem(item) >= bidPrice) {
      throw new InvalidParameterException("Your bid is smaller than the highest bid for item. Highest bid = " + listHighestBidForItem(item));
    }
  }

  public boolean isBidEnoughToPurchaseItem(Item item, Double bidPrice) {
    return item.getPurchasePrice() <= bidPrice;
  }
}
