package com.fh.fh.repositories;

import com.fh.fh.models.GreenBayDollar;
import com.fh.fh.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreenBayDollarRepository extends JpaRepository<GreenBayDollar, Long> {

  GreenBayDollar findByUser(User user);
}
