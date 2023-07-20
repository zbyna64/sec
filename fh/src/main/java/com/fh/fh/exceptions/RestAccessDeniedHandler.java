package com.fh.fh.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.fh.models.ErrorResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {

    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    mapper.writeValue(response.getOutputStream(), new ErrorResponse("403",
        accessDeniedException.getMessage(), request.getRequestURI()));
  }
}

