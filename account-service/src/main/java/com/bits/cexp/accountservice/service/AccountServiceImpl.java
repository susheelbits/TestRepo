package com.bits.cexp.accountservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bits.cexp.accountservice.ResourceNotFoundException;
import com.bits.cexp.accountservice.dto.AccountDTO;
import com.bits.cexp.accountservice.dto.CustomerDTO;
import com.bits.cexp.accountservice.model.Account;
import com.bits.cexp.accountservice.repository.AccountRepository;

/**
 * Implementation for Interface of Account Command Service
 * 
 * It implements methods for Create, Update, Delete
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}	

	/**
	 * 1. Add the Account
	 * 2. Send message to Deposit
	 * 
	 */	
	public Account saveAccount(Account account) {
		
    	System.out.println("Inside saveAccount method of AccountServiceImpl");
    	Account acct = accountRepository.save(account);
    	System.out.println(" Saved and returned " + acct.toString());
    	return acct;
	}	
	
	public AccountDTO getAccountById(Long accountId) {
    	System.out.println("Inside getAccountById method of AccountServiceImpl");
		System.out.println("******* GET ACCOUNT BY ID");
		
		String baseUrl = "http://localhost:8081/api/customers/1"; RestTemplate
		restTemplate = new RestTemplate(); String result =
		restTemplate.getForObject(baseUrl, String.class);
		System.out.println("****** Printing the oject "+result.toString());
		 
		CustomerDTO customer = restTemplate.getForObject(baseUrl, CustomerDTO.class);
		
		System.out.println("*******customer  "+customer.getFirstName());
    	Account acctDb =  accountRepository.findById(accountId).orElseThrow(() -> 
									new ResourceNotFoundException("Account", "Id", accountId));
    	AccountDTO acctDto = new AccountDTO (acctDb.getName(), acctDb.getBalance(), customer);
    	acctDto.setAccountNumber(acctDb.getAccountNumber());
		/*
		 * acctDto.setCustomerId(acctDto.getCustomer().getCustomerId());
		 * acctDto.setFirstName(acctDto.getCustomer().getFirstName());
		 */
    	
    	return acctDto;
	}	

	/**
	 * 1. Update the Account
	 * 2. Send message to Deposit
	 * 
	 */	
	@Override
	public Account updateAccount(Account account, long id){
		
		// we need to check whether customer with given id is exist in DB or not
		Account existingAccount = accountRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Account", "Id", id)); 
		
		existingAccount.setName(account.getName());
		existingAccount.setBalance(account.getBalance());
		// save existing customer to DB
		accountRepository.save(existingAccount);

		System.out.println("update Account  = " );
		//Send message to Customer Query Database
		// Can be refactored to be read from Configuration file
		String baseUrl = "http://localhost:9090/account-to-deposit-rabbitmq/producer?accountNumber=1"; 
		RestTemplate  restTemplate = new RestTemplate(); 
		String result = restTemplate.getForObject(baseUrl, String.class);
		
		return existingAccount;
	}	 

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}	

	/**
	 * 1. Delete the Account
	 * 2. Send message to Deposit
	 * 
	 */		
	@Override
	public void deleteAccount(long id) {
		
		// check whether a customerRepository exist in a DB or not
		accountRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Account", "Id", id));
		accountRepository.deleteById(id);
	}		
}
