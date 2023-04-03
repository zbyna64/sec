package com.fh.fh.services;

import com.fh.fh.models.LoginDTO;
import com.fh.fh.models.User;
import com.fh.fh.repositories.UserRepository;
import com.fh.fh.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private final UserRepository userRepository;
  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  public LoginService(UserRepository userRepository, TokenService tokenService,
      AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
  }

  public String login(LoginDTO loginDTO) {
    String loginUsername = loginDTO.getUsername();
    String loginPassword = loginDTO.getPassword();
    User user = findUser(loginDTO);

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsername, loginPassword));

    return tokenService.generateToken(user);
  }

  public User findUser(LoginDTO loginDTO) {
    return userRepository.findByUsername(loginDTO.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("Not found username: " + loginDTO.getUsername()));
  }
}
