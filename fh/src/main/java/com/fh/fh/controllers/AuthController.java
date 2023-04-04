package com.fh.fh.controllers;

import com.fh.fh.models.ErrorResponse;
import com.fh.fh.models.LoginDTO;
import com.fh.fh.models.LoginSuccesDTO;
import com.fh.fh.models.RegisterDTO;
import com.fh.fh.models.RegisterSuccessDTO;
import com.fh.fh.services.LoginService;
import com.fh.fh.services.RegisterService;
import java.security.InvalidParameterException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  public ResponseEntity<RegisterSuccessDTO> token(@Valid @RequestBody RegisterDTO registerDTO) {
    return ResponseEntity.status(201).body(registerService.register(registerDTO));
  }

  @PostMapping("/login")
  public ResponseEntity<LoginSuccesDTO> token(@Valid @RequestBody LoginDTO loginDTO) {
    LoginSuccesDTO token = loginService.login(loginDTO);
    return ResponseEntity.ok().body(token);
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponse handleAuthenticationError(AuthenticationException e, HttpServletRequest request) {
    return new ErrorResponse("401", e.getMessage(), request.getRequestURI());

  }

  @ExceptionHandler({InvalidParameterException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleRegistrationException(Exception e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleRegistrationConstraintException(MethodArgumentNotValidException e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getBindingResult().getFieldError().getDefaultMessage(), request.getRequestURI());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleLoginException(UsernameNotFoundException e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getMessage(), request.getRequestURI());
  }
}
