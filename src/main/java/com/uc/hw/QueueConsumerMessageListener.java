package com.uc.hw;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueConsumerMessageListener implements MessageListener{
	public void onMessage(Message msg) {
		if(msg instanceof TextMessage){
			TextMessage textMessage = (TextMessage) msg;
			try {
				System.out.println("--队列 MessageListener收到信息："+textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
