package com.fh.fh.repositories;

import com.fh.fh.models.Bid;
import com.fh.fh.models.Item;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {

  List<Bid> findAllByItem(Item item, Sort bidPrice);
}
