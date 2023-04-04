package com.fh.fh.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.fh.models.RegisterDTO;
import com.fh.fh.models.RegisterSuccessDTO;
import com.fh.fh.repositories.UserRepository;
import com.fh.fh.services.LoginService;
import com.fh.fh.services.RegisterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

  @Autowired
  MockMvc mvc;
  @Autowired
  ObjectMapper mapper;

  @MockBean
  private LoginService loginService;
  @MockBean
  private RegisterService registerService;
  @MockBean
  private UserRepository userRepository;

  @Test
  @WithMockUser
  public void registerSuccessfully() throws Exception {

    RegisterDTO registerDTO = new RegisterDTO("zbyna", "1234");
    RegisterSuccessDTO registerSuccessDTO = new RegisterSuccessDTO("Success", "Created new player");

    Mockito.when(registerService.register(registerDTO)).thenReturn(registerSuccessDTO);

    mvc.perform(post("/auth/register").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(registerDTO)))
        .andExpect(status().isCreated())
        .andDo(print());
  }

}