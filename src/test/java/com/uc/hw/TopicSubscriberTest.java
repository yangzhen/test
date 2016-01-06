package com.uc.hw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TopicSubscriberTest {
	private static ApplicationContext appContext = new ClassPathXmlApplicationContext( "applicationContext.xml");

	private static void receive() {
		TopicSubscriberService topicSubscriberService = (TopicSubscriberService) appContext.getBean("topicSubscriberService");
		topicSubscriberService.receive();
	}

	public static void main(String[] args) {
		receive();
	}
}
