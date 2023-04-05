package com.fh.fh.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.fh.models.User;
import com.fh.fh.services.CustomUserDetailService;
import com.fh.fh.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class HomeControllerTest {

  @Autowired
  ObjectMapper mapper;
  @Autowired
  MockMvc mockMvc;
  @Autowired
  CustomUserDetailService userDetailService;
  @Autowired
  UserService userService;

  @Test
  public void testUnauthorizedUser() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.status", is("401")))
        .andExpect(jsonPath("$.path", is("/")))
        .andDo(print());
  }

  @Test
  public void testAuthorizedUser() throws Exception {

    userService.createUserWithDollars("zbyna", "1234");
    mockMvc.perform(MockMvcRequestBuilders.get("/")
            .with(user(userDetailService.loadUserByUsername("zbyna"))))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString().equals("hello");

  }
}