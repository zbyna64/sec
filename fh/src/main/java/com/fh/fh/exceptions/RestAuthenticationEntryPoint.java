package com.fh.fh.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.fh.models.ErrorResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    mapper.writeValue(response.getOutputStream(), new ErrorResponse("401",
        authException.getMessage(), request.getRequestURI()));
  }
}