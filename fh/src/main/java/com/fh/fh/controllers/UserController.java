package com.fh.fh.controllers;

import com.fh.fh.models.BalanceDTO;
import com.fh.fh.models.BalanceResponseDTO;
import com.fh.fh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/balance")
  public ResponseEntity<BalanceResponseDTO> balance(Authentication authentication) {
    return ResponseEntity.ok().body(userService.getBalance(authentication));
  }

  @PostMapping("/balance")
  public ResponseEntity<BalanceResponseDTO> updateBalance(@RequestBody BalanceDTO balanceDTO, Authentication authentication) {
    return ResponseEntity.ok().body(userService.updateBalance(balanceDTO, authentication));
  }
}
