package com.fh.fh;

import com.fh.fh.models.User;
import com.fh.fh.repositories.UserRepository;
import com.fh.fh.security.RsaKeyProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class FhApplication implements CommandLineRunner {


  private final UserRepository userRepository;

  public FhApplication(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(FhApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    User user = new User("zbyna", passwordEncoder.encode("1234"), "ROLE_USER");
    userRepository.save(user);
  }
}