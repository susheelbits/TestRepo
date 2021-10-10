package com.bits.cexp.accountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bits.cexp.accountservice.dto.AccountDTO;
import com.bits.cexp.accountservice.model.Account;
import com.bits.cexp.accountservice.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}	

	// build create Account REST API
	@PostMapping()
	public ResponseEntity<Account> saveCustomer(@RequestBody Account account){
		return new ResponseEntity<Account>(accountService.saveAccount(account), HttpStatus.CREATED);
	}	

	// build get all Accounts REST API
	@GetMapping
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}	

	// build get Accounts by id REST API
	// http://localhost:8080/api/accounts/1
	@GetMapping("{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id") long accountId){
		return new ResponseEntity<AccountDTO>(accountService.getAccountById(accountId), HttpStatus.OK);
	}

	// build update Accounts REST API
	// http://localhost:8080/api/accounts/1
	@PutMapping("{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable("id") long id
												  ,@RequestBody Account account){
		return new ResponseEntity<Account>(accountService.updateAccount(account, id), HttpStatus.OK);
	}	

	// build delete Accounts REST API
	// http://localhost:8080/api/accounts/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") long id){
		
		// delete employee from DB
		accountService.deleteAccount(id);
		
		return new ResponseEntity<String>("Account deleted successfully!.", HttpStatus.OK);
	}		

}
