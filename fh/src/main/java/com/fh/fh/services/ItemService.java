package com.fh.fh.services;

import com.fh.fh.models.Bid;
import com.fh.fh.models.BidDTO;
import com.fh.fh.models.BidRequestDTO;
import com.fh.fh.models.Item;
import com.fh.fh.models.ItemDetailResponseDTO;
import com.fh.fh.models.ItemListResponseDTO;
import com.fh.fh.models.ItemRequestDTO;
import com.fh.fh.models.ItemResponseDTO;
import com.fh.fh.models.User;
import com.fh.fh.repositories.ItemRepository;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.InsufficientResourcesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
@Service
public class ItemService {

  @Value("${items.page.size}")
  private int pageSize;
  private final ItemRepository itemRepository;
  private final UserService userService;
  private final BidService bidService;

  @Autowired
  public ItemService(ItemRepository itemRepository,
      UserService userService, BidService bidService) {
    this.itemRepository = itemRepository;
    this.userService = userService;
    this.bidService = bidService;
  }

  public List<ItemListResponseDTO> listAllItems(int page) {
    Pageable pageToShow = PageRequest.of(page - 1, pageSize, Sort.by("id").ascending());
    return itemRepository.findAll(pageToShow).stream()
        .filter(item -> !item.isSold())
        .map(ItemListResponseDTO::new)
        .collect(Collectors.toList());
  }

  public List<ItemListResponseDTO> listAllActiveItems(int page) {
    Pageable pageToShow = PageRequest.of(page - 1, pageSize, Sort.by("id").ascending());
    return itemRepository.findAllBySold(false, pageToShow).stream()
        .map(ItemListResponseDTO::new)
        .collect(Collectors.toList());
  }


  public ItemResponseDTO createItem(ItemRequestDTO itemRequestDTO, Authentication authentication) {
    User user = userService.findByUsername(authentication.getName());
    Item item = new Item(itemRequestDTO.getName(), itemRequestDTO.getDescription(), itemRequestDTO.getStartingPrice(), itemRequestDTO.getPurchasePrice(), user);
    item = itemRepository.save(item);
    return convertItemToResponseDTO(item);
  }

  public ItemResponseDTO convertItemToResponseDTO(Item item) {
    return new ItemResponseDTO(item);
  }

  public Item findItemById(Long id) {
    return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No item with ID:" + id));
  }

  public ItemDetailResponseDTO listItemById(Long id) {
    Item item = findItemById(id);
    List<BidDTO> bidDTOList = bidService.convertToBidDTOList(item);
    return new ItemDetailResponseDTO(item, bidDTOList);
  }

  public ItemDetailResponseDTO bidItem(Long id, BidRequestDTO bidRequestDTO, Authentication authentication)
      throws InsufficientResourcesException {
    Item item = findItemById(id);
    Double bidPrice = bidRequestDTO.getBidPrice();
    User user = userService.findByUsername(authentication.getName());

    isSellable(item);
    userService.canAffordBid(bidPrice, user);
    bidService.isBidHigherThanLastBid(item, bidPrice);
    Bid bid = bidService.createBid(user, item, bidPrice);
    List<BidDTO> bidDTOList = bidService.convertToBidDTOList(item);

    if (bidService.isBidEnoughToPurchaseItem(item, bidPrice)) {
      userService.buyItem(user, item, bidPrice);
      item = buyItem(item, user);
      return new ItemDetailResponseDTO(item, bidDTOList);

    }
    return new ItemDetailResponseDTO(item, bidDTOList);
  }

  public Item buyItem(Item item, User user) {
    item.setSold(true);
    item.setBuyer(user);
    return itemRepository.save(item);
  }

  public void isSellable(Item item) {
    if (item.isSold()) {
      throw new InvalidParameterException("Item is already sold. Owner is " + item.getBuyer().getUsername());
    }
  }
}
