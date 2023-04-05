package com.fh.fh.services;

import com.fh.fh.models.Item;
import com.fh.fh.models.ItemListResponseDTO;
import com.fh.fh.models.ItemRequestDTO;
import com.fh.fh.models.ItemResponseDTO;
import com.fh.fh.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ItemService {

  @Value("${items.page.size}")
  private int pageSize;
  private final ItemRepository itemRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<ItemListResponseDTO> listAllItems(int page) {
     return listItemsAscOrderById(page);

  }

  public List<ItemListResponseDTO> listItemsAscOrderById(int page) {

    Pageable pageToShow = PageRequest.of(page - 1, pageSize, Sort.by("id").ascending());

    return itemRepository.findAll(pageToShow).stream()
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
