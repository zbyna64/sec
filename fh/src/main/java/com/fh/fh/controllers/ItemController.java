package com.fh.fh.controllers;

import com.fh.fh.models.ErrorResponse;
import com.fh.fh.models.ItemListResponseDTO;
import com.fh.fh.models.ItemRequestDTO;
import com.fh.fh.models.ItemResponseDTO;
import com.fh.fh.services.ItemService;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @PostMapping
  public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO) {
    return ResponseEntity.status(201).body(itemService.createItem(itemRequestDTO));
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleRegistrationConstraintException(MethodArgumentNotValidException e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getBindingResult().getFieldError().getDefaultMessage(), request.getRequestURI());
  }

  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleRegistrationConstraintException(IllegalArgumentException e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getMessage(), request.getRequestURI());
  }
}
