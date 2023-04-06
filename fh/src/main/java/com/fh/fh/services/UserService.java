package com.fh.fh.services;

import com.fh.fh.models.BalanceDTO;
import com.fh.fh.models.BalanceResponseDTO;
import com.fh.fh.models.GreenBayDollar;
import com.fh.fh.models.Item;
import com.fh.fh.models.User;
import com.fh.fh.repositories.GreenBayDollarRepository;
import com.fh.fh.repositories.UserRepository;
import javax.naming.InsufficientResourcesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private PasswordEncoder encoder = new BCryptPasswordEncoder();

  private final UserRepository userRepository;
  private final GreenBayDollarRepository dollarRepository;
  private final DollarService dollarService;

  @Autowired
  public UserService(UserRepository userRepository, GreenBayDollarRepository dollarRepository,
      DollarService dollarService) {
    this.userRepository = userRepository;
    this.dollarRepository = dollarRepository;
    this.dollarService = dollarService;
  }

  public User createUserWithDollars(String username, String password) {
    User user = new User(username, encoder.encode(password));
    GreenBayDollar dollar = new GreenBayDollar(100.0);
    user.setDollar(dollar);
    dollarRepository.save(dollar);
    dollar.setUser(user);
    return userRepository.save(user);
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("Username not found: " + username));
  }

  public Double userDollars(User user) {
    return user.getDollar().getAmount();
  }

  public void canAffordBid(Double bidPrice, User user) throws InsufficientResourcesException {
    if (userDollars(user) <= bidPrice) {
      throw new InsufficientResourcesException("Not enough resources to bid. Dollars = " + userDollars(user));
    }
  }

  public void buyItem(User user, Item item, Double bidPrice) {
    GreenBayDollar dollar = dollarService.changeAmountForUser(user, bidPrice);
  }

  public BalanceResponseDTO updateBalance(BalanceDTO balanceDTO, Authentication authentication) {
    User user = findByUsername(authentication.getName());
    dollarService.increaseBalance(user, balanceDTO.getAmount());
    return new BalanceResponseDTO(user);
  }

  public BalanceResponseDTO getBalance(Authentication authentication) {
    User user = findByUsername(authentication.getName());
    return new BalanceResponseDTO(user);
  }
}
