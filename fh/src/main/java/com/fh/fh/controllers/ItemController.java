package com.fh.fh.controllers;

import com.fh.fh.models.ItemDTO;
import com.fh.fh.services.ItemService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

  private final ItemService itemService;

  @Autowired
  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping
  public ResponseEntity listAllItems() {
    return null;
  }

  @PostMapping
  public ResponseEntity createItem(@Valid @RequestBody ItemDTO itemDTO) {
    return ResponseEntity.status(201).body(itemService.createItem(itemDTO));
  }
}
