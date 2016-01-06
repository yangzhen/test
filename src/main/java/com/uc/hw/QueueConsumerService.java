package com.uc.hw;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;


public class QueueConsumerService{

	JmsTemplate jmsTemplate;

	Destination destination;

	public void receive() {
		TextMessage message = (TextMessage) jmsTemplate.receive();
		try {
			System.out.println("QueueConsumerService收到消息："+message.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
}