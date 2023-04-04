package com.fh.fh.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.fh.models.LoginDTO;
import com.fh.fh.models.RegisterDTO;
import com.fh.fh.services.UserService;
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
  @Autowired
  UserService userService;

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
  public void testRegistrationConstraintLengthUsername() throws Exception {

    RegisterDTO registerDTO = new RegisterDTO("z", "1234");

    mockMvc.perform(post("/auth/register").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(registerDTO)))
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andExpect(jsonPath("$.status", is("400")));
  }

  @Test
  public void testRegistrationConstraintLengthPassword() throws Exception {

    RegisterDTO registerDTO = new RegisterDTO("zbynaa", "14");

    mockMvc.perform(post("/auth/register").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(registerDTO)))
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andExpect(jsonPath("$.status", is("400")));
  }

  @Test
  public void testRegistrationWithExistingUsername() throws Exception {

    userService.createUserWithDollars("zbyna", "1234");
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
        .andExpect(jsonPath("$.*", hasSize(3)))
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