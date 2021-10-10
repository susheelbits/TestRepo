package com.bits.cexp.accountservice.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

public class AccountRabitMQListener {
	public void onMessage(Message message) {
		System.out.println("Consuming Message - " + new String(message.getBody()));
		
		//Get the Acknowledge Flag, value
		// If the flag is true, the Add, Update, Delete record accordingly
	}
}
