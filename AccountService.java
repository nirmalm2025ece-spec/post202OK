package com.sece.eceb.service;

import com.sece.eceb.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountService extends JpaRepository<Account, String> {
}