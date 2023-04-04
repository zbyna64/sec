package com.fh.fh.services;

import com.fh.fh.models.Item;
import com.fh.fh.models.ItemListResponseDTO;
import com.fh.fh.models.ItemRequestDTO;
import com.fh.fh.models.ItemResponseDTO;
import com.fh.fh.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

  private final ItemRepository itemRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<ItemListResponseDTO> listAllItems() {
    return itemRepository.findAll().stream()
        .filter(item -> !item.isSold())
        .map(ItemListResponseDTO::new)
        .collect(Collectors.toList());

  }

  public ItemResponseDTO createItem(ItemRequestDTO itemRequestDTO) {
    Item item = new Item(itemRequestDTO.getName(), itemRequestDTO.getDescription(), itemRequestDTO.getStartingPrice(), itemRequestDTO.getPurchasePrice());
    item = itemRepository.save(item);
    return convertResponseObject(item);
  }

  public ItemResponseDTO convertResponseObject(Item item) {
    return new ItemResponseDTO(item);
  }
}
