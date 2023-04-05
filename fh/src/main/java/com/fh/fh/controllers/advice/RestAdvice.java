package com.fh.fh.controllers.advice;

import com.fh.fh.models.ErrorResponse;
import java.security.InvalidParameterException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestAdvice {

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleRegistrationConstraintException(MethodArgumentNotValidException e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getBindingResult().getFieldError().getDefaultMessage(), request.getRequestURI());
  }

  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleRegistrationConstraintException(IllegalArgumentException e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getMessage(), request.getRequestURI());
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

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleLoginException(UsernameNotFoundException e, HttpServletRequest request) {
    return new ErrorResponse("400", e.getMessage(), request.getRequestURI());
  }
}
