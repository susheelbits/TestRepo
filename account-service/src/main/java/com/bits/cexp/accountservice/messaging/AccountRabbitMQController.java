package com.bits.cexp.accountservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.cexp.accountservice.dto.AccountDTO;


@RestController
@RequestMapping(value = "/account-to-deposit-rabbitmq/")
public class AccountRabbitMQController {

	
	/* @Autowired */
	AccountRabbitMQSender rabbitMQSender;

    @Autowired
    public AccountRabbitMQController(AccountRabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }
    

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("accountNumber") long accountNumber) {
		System.out.println("PRODUCER  "+accountNumber);
		AccountDTO acct=new AccountDTO();
		acct.setAccountNumber(accountNumber);
		//acct.setBalance(balance);
		
		rabbitMQSender.send(acct);
		
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}		
}
