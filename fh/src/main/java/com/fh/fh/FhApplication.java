package com.fh.fh;

import com.fh.fh.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class FhApplication {

  public static void main(String[] args) {
    SpringApplication.run(FhApplication.class, args);
  }
}
