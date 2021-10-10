package com.bits.cexp.accountservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bits.cexp.accountservice.dto.AccountDTO;


/**
 * Sending message to Query DB for adding, updating, deletig the Customer 
 * 
 *
 */
@Service
public class AccountRabbitMQSender {
	private RabbitTemplate rabbitTemplate;

    @Autowired
    public AccountRabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${spring.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(AccountDTO acctDTO) {
		System.out.println("AccountRabbitMQSender Send msg = " + acctDTO);
		rabbitTemplate.convertAndSend(exchange, routingkey, acctDTO);
	}	
}
