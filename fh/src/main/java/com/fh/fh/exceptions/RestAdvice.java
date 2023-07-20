package com.fh.fh.exceptions;

import com.fh.fh.models.ErrorResponse;
import java.security.InvalidParameterException;
import javax.naming.InsufficientResourcesException;
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

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponse handleAuthenticationError(AuthenticationException e, HttpServletRequest request) {
    return new ErrorResponse("401", e.getMessage(), request.getRequestURI());

  }

}
