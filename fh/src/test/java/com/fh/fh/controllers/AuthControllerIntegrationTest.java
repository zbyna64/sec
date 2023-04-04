package com.fh.fh.controllers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.fh.models.LoginDTO;
import com.fh.fh.models.RegisterDTO;
import com.fh.fh.models.RegisterSuccessDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

  @Autowired
  private WebApplicationContext context;
  @Autowired
  ObjectMapper mapper;
  @Autowired
  MockMvc mockMvc;


  @Test
  public void testSuccessfulRegistration() throws Exception {

    RegisterDTO registerDTO = new RegisterDTO("zbynaa", "1234");

    mockMvc.perform(post("/auth/register").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(registerDTO)))
        .andExpect(status().isCreated())
        .andDo(print())
        .andExpect(jsonPath("$.status", is("Success")));
  }

  @Test
  public void testRegistrationWithExistingUsername() throws Exception {

    RegisterDTO registerDTO = new RegisterDTO("zbyna", "1234");

    mockMvc.perform(post("/auth/register").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(registerDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is("400")))
        .andDo(print());
  }

  @Test
  public void testLoginWithCorrectUsername() throws Exception {

    LoginDTO loginDTO = new LoginDTO("zbyna", "1234");

    mockMvc.perform(post("/auth/login").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(loginDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username", is("zbyna")))
        .andDo(print());
  }

  @Test
  public void testLoginWithIncorrectUsername() throws Exception {

    LoginDTO loginDTO = new LoginDTO("test", "1234");

    mockMvc.perform(post("/auth/login").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(loginDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is("400")))
        .andDo(print());
  }
}