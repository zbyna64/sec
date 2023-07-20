package com.fh.fh.services;

import com.fh.fh.models.RegisterDTO;
import com.fh.fh.models.RegisterSuccessDTO;
import com.fh.fh.models.User;
import com.fh.fh.repositories.UserRepository;
import java.security.InvalidParameterException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

  private PasswordEncoder encoder = new BCryptPasswordEncoder();
  private final UserRepository userRepository;
  private final UserService userService;

  public RegisterService(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  public RegisterSuccessDTO register(RegisterDTO registerDTO) {

    String username = registerDTO.getUsername();
    String password = registerDTO.getPassword();

    if (userRepository.existsByUsername(username)) {
      throw new InvalidParameterException("Username: `" + username + "` already taken");
    }
//    User user = userService.createUserWithDollars(username, password);
    return new RegisterSuccessDTO("Success", "Registration completed with username: " + username);
  }
}
