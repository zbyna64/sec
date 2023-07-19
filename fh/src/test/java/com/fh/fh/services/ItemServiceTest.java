package com.fh.fh.services;

import com.fh.fh.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
  @Mock
  private UserService userService;
  @Mock
  private ItemRepository itemRepository;
  @InjectMocks
  private ItemService itemService;

  @Test
  public void testCreateItemWithUser() {

    ItemRequestDTO requestDTO = new ItemRequestDTO("item", "test purpose", 1000.0, 2000.0);
    Authentication authentication = mock(Authentication.class);
    User testUser = new User("zbyna", "1234");
    Item item = new Item("item", "test purpose", 1000.0, 2000.0 , testUser);

    Mockito.when(authentication.getName()).thenReturn("zbyna");
    Mockito.when(userService.findByUsername(any(String.class))).thenReturn(testUser);
    Mockito.when(itemRepository.save(any(Item.class))).thenReturn(item);

    ItemResponseDTO response = itemService.createItem(requestDTO, authentication);

    assertEquals("item", response.getName());
    verify(itemRepository, times(1)).save(any());
  }
}