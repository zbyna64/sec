package com.fh.fh.repositories;

import com.fh.fh.models.Item;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

  List<Item> findAllBySold(boolean sold, Pageable pageable);
}
