package com.fh.fh.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.fh.services.CustomUserDetailService;
import com.fh.fh.services.UserService;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(value = Lifecycle.PER_METHOD)
@Transactional
public class ItemControllerIntegrationTest {

  @Autowired
  MockMvc mvc;
  @Autowired
  CustomUserDetailService userDetailService;
  @Autowired
  UserService userService;
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  ItemService itemService;

  @Value("${items.page.size}")
  int pageSize;
  @Before
  public void init() {
    userService.createUserWithDollars("zbyna", "1234");
  }
  @org.junit.Test
  public void createItem_ReturnItemResponseDTO() throws Exception {

    ItemRequestDTO itemRequestDTO = new ItemRequestDTO("test", "test description", 1000.0, 2000.0);

    mvc.perform(post("/items")
        .with(csrf())
        .with(user(userDetailService.loadUserByUsername("zbyna")))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(itemRequestDTO)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(notNullValue())))
        .andExpect(jsonPath("$.name", is("test")))
        .andExpect(jsonPath("$.description", is("test description")))
        .andExpect(jsonPath("$.startingPrice", is(1000.0)));
  }

  @org.junit.Test
  public void createItemWithMissingName_ReturnErrorResponse() throws Exception {

    ItemRequestDTO itemRequestDTO = new ItemRequestDTO("test description", 1000.0, 2000.0);

    mvc.perform(post("/items")
            .with(csrf())
            .with(user(userDetailService.loadUserByUsername("zbyna")))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(itemRequestDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is("400")))
        .andExpect(jsonPath("$.message", is("Field name is missing")))
        .andExpect(jsonPath("$.path", is("/items")));
  }

  @org.junit.Test
  public void listAllItems_ReturnList() throws Exception {
    Authentication authentication = new UsernamePasswordAuthenticationToken("zbyna", "1234");
    ItemRequestDTO itemRequestDTO1 = new ItemRequestDTO("test1", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO2 = new ItemRequestDTO("test2", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO3 = new ItemRequestDTO("test3", "test des", 1.1, 2.2);
    itemService.createItem(itemRequestDTO1, authentication);
    itemService.createItem(itemRequestDTO2, authentication);
    itemService.createItem(itemRequestDTO3, authentication);

    mvc.perform(get("/items")
            .with(csrf())
            .with(user(userDetailService.loadUserByUsername("zbyna"))))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].length()").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[1].length()").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[2].length()").value(3));
  }

  @org.junit.Test
  public void listAllItemsPage1_ReturnListWithSize5() throws Exception {
    Authentication authentication = new UsernamePasswordAuthenticationToken("zbyna", "1234");

    ItemRequestDTO itemRequestDTO1 = new ItemRequestDTO("test1", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO2 = new ItemRequestDTO("test2", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO3 = new ItemRequestDTO("test3", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO4 = new ItemRequestDTO("test4", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO5 = new ItemRequestDTO("test5", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO6 = new ItemRequestDTO("test6", "test des", 1.1, 2.2);
    itemService.createItem(itemRequestDTO1, authentication);
    itemService.createItem(itemRequestDTO2, authentication);
    itemService.createItem(itemRequestDTO3, authentication);
    itemService.createItem(itemRequestDTO4, authentication);
    itemService.createItem(itemRequestDTO5, authentication);
    itemService.createItem(itemRequestDTO6, authentication);

    mvc.perform(get("/items")
            .with(csrf())
            .with(user(userDetailService.loadUserByUsername("zbyna"))))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(pageSize)))
        .andExpect(jsonPath("$.[0].id", is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].length()").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[1].length()").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[2].length()").value(3));
  }
  @org.junit.Test
  public void listAllItemsPage2_ReturnListWithSize1() throws Exception {
    Authentication authentication = new UsernamePasswordAuthenticationToken("zbyna", "1234");

    ItemRequestDTO itemRequestDTO1 = new ItemRequestDTO("test1", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO2 = new ItemRequestDTO("test2", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO3 = new ItemRequestDTO("test3", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO4 = new ItemRequestDTO("test4", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO5 = new ItemRequestDTO("test5", "test des", 1.1, 2.2);
    ItemRequestDTO itemRequestDTO6 = new ItemRequestDTO("test6", "test des", 1.1, 2.2);
    itemService.createItem(itemRequestDTO1, authentication);
    itemService.createItem(itemRequestDTO2, authentication);
    itemService.createItem(itemRequestDTO3, authentication);
    itemService.createItem(itemRequestDTO4, authentication);
    itemService.createItem(itemRequestDTO5, authentication);
    itemService.createItem(itemRequestDTO6, authentication);

    mvc.perform(get("/items")
            .param("page", "2")
            .with(user(userDetailService.loadUserByUsername("zbyna"))))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(1)))
        .andExpect(jsonPath("$.[0].id", is(12)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].length()").value(3));
  }

  @org.junit.Test
  public void listAllItemsWherePageIsNegative() throws Exception {

    mvc.perform(get("/items")
            .param("page", "-1")
            .with(user(userDetailService.loadUserByUsername("zbyna"))))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is("400")))
        .andExpect(jsonPath("$.path", is("/items")));
  }

  @org.junit.Test
  public void listAllItems_ReturnEmptyList() throws Exception {

    String response = mvc.perform(get("/items")
            .with(csrf())
            .with(user(userDetailService.loadUserByUsername("zbyna"))))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andReturn().getResponse().getContentAsString();

    assertEquals(0, response.length());
  }
}