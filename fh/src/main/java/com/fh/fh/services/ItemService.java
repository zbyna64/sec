package com.fh.fh.services;

import com.fh.fh.models.Item;
import com.fh.fh.models.ItemDTO;
import com.fh.fh.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private final ItemRepository itemRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public ItemDTO createItem(ItemDTO itemDTO) {
    Item item = new Item(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getStartingPrice());
    itemRepository.save(item);
    return itemDTO;
  }
}
