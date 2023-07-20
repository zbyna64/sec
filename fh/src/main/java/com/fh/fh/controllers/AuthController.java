package com.fh.fh.controllers;

import com.fh.fh.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final TokenService tokenService;

  public AuthController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

//  @PostMapping("/login")
//  public ResponseEntity<LoginSuccesDTO> token(@Valid @RequestBody LoginDTO loginDTO) {
//    LoginSuccesDTO token = loginService.login(loginDTO);
//    return ResponseEntity.ok().body(token);
//  }

  @PostMapping("/login")
  public ResponseEntity<String> token(Authentication authentication) {
    String token = tokenService.generateToken();
    return ResponseEntity.ok().body(token);
  }

  @GetMapping("/token")
  public ResponseEntity<String> generateToken() {
    String token = tokenService.generateToken();
    return ResponseEntity.ok().body(token);
  }


}
