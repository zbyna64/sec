package com.fh.fh.services;

import com.fh.fh.models.Bid;
import com.fh.fh.models.Item;
import com.fh.fh.models.User;
import com.fh.fh.repositories.BidRepository;
import java.util.List;
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

  public List<Bid> listBidsForItem(Item item) {
    return bidRepository.findAllByItem(item, Sort.by("bidPrice").descending());
  }
}
