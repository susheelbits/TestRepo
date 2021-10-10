package com.bits.cexp.accountservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNumber;	
	
    @Column(name = "balance")
    private double balance;
    
    @Column(name = "name")
    private String name;
    //private Customer holder;
    
    //private Arraylist<Tansaction> transactions;
	
	public Account() {

	}
	
	public Account(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}		

	public Long getAccountNumber() {
		return accountNumber;
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
}
