package com.bits.cexp.accountservice.service;

import java.util.List;

import com.bits.cexp.accountservice.dto.AccountDTO;
import com.bits.cexp.accountservice.model.Account;

/**
 * Interface for Account Command Service
 * 
 * It exposes methods for Create, Update, Delete
 *
 */
public interface AccountService {

	 public Account saveAccount(Account account);
	 
	 public AccountDTO getAccountById(Long accountId);
	 
	 public List<Account> getAllAccounts();
	 
	 public Account updateAccount(Account account, long id);
	 
	 void deleteAccount(long id);	

}
