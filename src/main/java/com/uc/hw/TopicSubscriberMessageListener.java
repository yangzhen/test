package com.uc.hw;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicSubscriberMessageListener implements MessageListener{
	public void onMessage(Message msg) {
		if(msg instanceof TextMessage){
			TextMessage textMessage = (TextMessage) msg;
			try {
				System.out.println("--订阅者 MessageListener收到信息："+textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
