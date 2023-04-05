package com.fh.fh.controllers;

import com.fh.fh.models.BidRequestDTO;
import com.fh.fh.models.ItemDetailResponseDTO;
import com.fh.fh.models.ItemListResponseDTO;
import com.fh.fh.models.ItemRequestDTO;
import com.fh.fh.models.ItemResponseDTO;
import com.fh.fh.services.ItemService;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

  private final ItemService itemService;

  @Autowired
  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping()
  public ResponseEntity<?> listAllItems(@Min (1) @RequestParam(required = false, defaultValue = "1") int page) {
    List<ItemListResponseDTO> listResponseDTOList = itemService.listAllItems(page);
    if (listResponseDTOList.isEmpty()) {
      return ResponseEntity.status(204).build();
    }
    return ResponseEntity.ok().body(listResponseDTOList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemDetailResponseDTO> listItem(@PathVariable Long id) {

    ItemDetailResponseDTO itemDetail = itemService.listItemById(id);
    return ResponseEntity.status(200).body(itemDetail);

  }

  @PostMapping
  public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO, Authentication authentication) {
    return ResponseEntity.status(201).body(itemService.createItem(itemRequestDTO, authentication));
  }

  @PostMapping("/{id}")
  public ResponseEntity bid(@PathVariable Long id, @RequestBody BidRequestDTO bidRequestDTO, Authentication authentication) {

    ItemDetailResponseDTO itemDetail = itemService.bidItem(id, bidRequestDTO, authentication);
    return ResponseEntity.status(200).body(itemDetail);

  }


}
