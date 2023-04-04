package com.fh.fh.security;

import static org.junit.Assert.assertNotNull;

import com.fh.fh.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenServiceTest {

  @Autowired
  TokenService tokenService;
  @Autowired
  JwtEncoder encoder;
  @Autowired
  JwtDecoder decoder;

  @Test
  public void generateToken() {

    User user = new User("zbyna", "1234", "ROLE_USER");
    String token = tokenService.generateToken(user);

    assertNotNull(token);
  }
}