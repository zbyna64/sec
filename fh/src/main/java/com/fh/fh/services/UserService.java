package com.fh.fh.services;

import com.fh.fh.models.GreenBayDollar;
import com.fh.fh.models.User;
import com.fh.fh.repositories.GreenBayDollarRepository;
import com.fh.fh.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private PasswordEncoder encoder = new BCryptPasswordEncoder();

  private final UserRepository userRepository;
  private final GreenBayDollarRepository dollarRepository;

  @Autowired
  public UserService(UserRepository userRepository, GreenBayDollarRepository dollarRepository) {
    this.userRepository = userRepository;
    this.dollarRepository = dollarRepository;
  }

  public User createUserWithDollars(String username, String password) {
    User user = new User(username, encoder.encode(password));
    GreenBayDollar dollar = new GreenBayDollar(100L);
    user.setDollar(dollar);
    dollarRepository.save(dollar);
    dollar.setUser(user);
    return userRepository.save(user);
  }
}
