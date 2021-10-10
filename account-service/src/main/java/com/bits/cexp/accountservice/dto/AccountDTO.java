package com.bits.cexp.accountservice.dto;

public class AccountDTO {



    private Long accountNumber;	
	

    private double balance;
    
    private String name;
    private CustomerDTO holder;
    
    private long customerId;	

	private String firstName; 
	
	public AccountDTO() {

	}
	
	public AccountDTO(String name, double balance, CustomerDTO holder) {
		this.name = name;
		this.balance = balance;
		this.holder = holder;
	}		

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}		

	public CustomerDTO getCustomer() {
		return holder;
	}
	
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
