package com.fh.fh.services;

import com.fh.fh.models.GreenBayDollar;
import com.fh.fh.models.User;
import com.fh.fh.repositories.GreenBayDollarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DollarService {

  private final GreenBayDollarRepository dollarRepository;

  @Autowired
  public DollarService(GreenBayDollarRepository dollarRepository) {
    this.dollarRepository = dollarRepository;
  }

  public GreenBayDollar getDollarByUser(User user) {
    return dollarRepository.findByUser(user);
  }

  public GreenBayDollar changeAmountForUser(User user, Double bidPrice) {
    GreenBayDollar dollar = getDollarByUser(user);
    Double initialAmount = dollar.getAmount();
    Double newAmount = initialAmount - bidPrice;
    dollar.setAmount(newAmount);
    return dollarRepository.save(dollar);
  }

  public GreenBayDollar increaseBalance(User user, Double amount) {
    GreenBayDollar dollar = getDollarByUser(user);
    Double initialAmount = dollar.getAmount();
    Double newAmount = initialAmount + amount;
    dollar.setAmount(newAmount);
    return dollarRepository.save(dollar);
  }
}
