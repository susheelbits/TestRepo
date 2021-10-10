package com.bits.cexp.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bits.cexp.accountservice.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
