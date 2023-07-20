package com.fh.fh.controllers;

import com.fh.fh.models.LoginDTO;
import com.fh.fh.models.LoginSuccesDTO;
import com.fh.fh.models.RegisterDTO;
import com.fh.fh.models.RegisterSuccessDTO;
import com.fh.fh.security.TokenService;
import com.fh.fh.services.LoginService;
import com.fh.fh.services.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final LoginService loginService;
  private final RegisterService registerService;
  private final TokenService tokenService;

  public AuthController(LoginService loginService, RegisterService registerService, TokenService tokenService) {
    this.loginService = loginService;
    this.registerService = registerService;
    this.tokenService = tokenService;
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterSuccessDTO> token(@Valid @RequestBody RegisterDTO registerDTO) {
    return ResponseEntity.status(201).body(registerService.register(registerDTO));
  }

  @PostMapping("/login")
  public ResponseEntity<LoginSuccesDTO> token(@Valid @RequestBody LoginDTO loginDTO) {
    LoginSuccesDTO token = loginService.login(loginDTO);
    return ResponseEntity.ok().body(token);
  }

  @GetMapping("/token")
  public ResponseEntity<String> generateToken() {
    String token = tokenService.generateToken();
    return ResponseEntity.ok().body(token);
  }


}
