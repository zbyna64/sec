package com.fh.fh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  @GetMapping
  @ResponseBody
  public String home() {
    return "hello";
  }


  @GetMapping("/admin")
  @ResponseBody
  public String admin() {
    return "hello admin";
  }


  @GetMapping("/developer")
  @ResponseBody
  public String developer() {
    return "hello developer";
  }

}
