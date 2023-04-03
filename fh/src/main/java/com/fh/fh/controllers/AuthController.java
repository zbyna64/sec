package com.fh.fh.controllers;

import com.fh.fh.models.ErrorResponse;
import com.fh.fh.models.LoginDTO;
import com.fh.fh.models.RegisterDTO;
import com.fh.fh.models.RegisterSuccessDTO;
import com.fh.fh.services.LoginService;
import com.fh.fh.services.RegisterService;
import java.security.InvalidParameterException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final LoginService loginService;
  private final RegisterService registerService;

  public AuthController(LoginService loginService, RegisterService registerService) {
    this.loginService = loginService;
    this.registerService = registerService;
  }

  @PostMapping("/register")
  public RegisterSuccessDTO token(@RequestBody RegisterDTO registerDTO) {
    return registerService.register(registerDTO);
  }

  @PostMapping("/login")
  public String token(@RequestBody LoginDTO loginDTO) {
    String token = loginService.login(loginDTO);
    return token;
  }

  @ExceptionHandler(AuthenticationException.class)
  public ErrorResponse handleAuthenticationError(AuthenticationException e, HttpServletRequest request) {
    return new ErrorResponse("401", e.getMessage(), request.getRequestURI());

  }

  @ExceptionHandler(InvalidParameterException.class)
  public ErrorResponse handleRegistrationException(InvalidParameterException e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getMessage(), request.getRequestURI());
  }
}
