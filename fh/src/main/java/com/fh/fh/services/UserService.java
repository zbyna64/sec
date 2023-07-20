package com.fh.fh.services;

import com.fh.fh.models.User;
import com.fh.fh.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private PasswordEncoder encoder = new BCryptPasswordEncoder();

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("Username not found: " + username));
  }
}
