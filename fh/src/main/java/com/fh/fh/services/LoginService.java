package com.fh.fh.services;

import com.fh.fh.models.LoginDTO;
import com.fh.fh.models.LoginSuccesDTO;
import com.fh.fh.models.User;
import com.fh.fh.repositories.UserRepository;
import com.fh.fh.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private final UserRepository userRepository;
  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;
  private final JwtDecoder decoder;

  public LoginService(UserRepository userRepository, TokenService tokenService,
      AuthenticationManager authenticationManager, JwtDecoder decoder) {
    this.userRepository = userRepository;
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
    this.decoder = decoder;
  }

  public LoginSuccesDTO login(LoginDTO loginDTO) {
    String loginUsername = loginDTO.getUsername();
    String loginPassword = loginDTO.getPassword();
    User user = findUser(loginDTO);

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsername, loginPassword));

    String token = tokenService.generateToken(user);

    LoginSuccesDTO dto = new LoginSuccesDTO(loginUsername, token, user.getDollar().getAmount());
    return dto;
  }

  public User findUser(LoginDTO loginDTO) {
    return userRepository.findByUsername(loginDTO.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("Not found username: " + loginDTO.getUsername()));
  }
}
