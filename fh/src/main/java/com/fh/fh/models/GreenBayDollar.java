package com.fh.fh.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "dollars")
public class GreenBayDollar {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;
  private Long amount;
  @OneToOne(mappedBy = "dollar")
  @Nullable
  private User user;

  public GreenBayDollar() {
  }

  public GreenBayDollar(Long amount) {
    this.amount = amount;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
