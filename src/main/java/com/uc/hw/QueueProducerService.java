package com.uc.hw;

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class QueueProducerService{
	JmsTemplate jmsTemplate;

	Destination destination;

	public void send() {
		MessageCreator messageCreator = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText("QueueProducerService发送消息"+new Date());
				return message;
			}

		};
		jmsTemplate.send(this.destination,messageCreator);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
}