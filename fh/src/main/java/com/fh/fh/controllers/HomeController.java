package com.fh.fh.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping
  @ResponseBody
  public String home() {
    return "hello";
  }


  @GetMapping("/admin")
  @ResponseBody
  @SecurityRequirement(name = "bearer Authentication")
  public String admin() {
    return "hello admin";
  }


  @GetMapping("/developer")
  @ResponseBody
  public String developer() {
    return "hello developer";
  }

}
