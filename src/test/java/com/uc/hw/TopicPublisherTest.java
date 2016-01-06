package com.uc.hw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TopicPublisherTest {
	private static ApplicationContext appContext = new ClassPathXmlApplicationContext( "applicationContext.xml");

	private static void send() {
		TopicPublisherService topicPublisherService = (TopicPublisherService) appContext.getBean("topicPublisherService");
		topicPublisherService.send();
	}

	public static void main(String[] args) {
		send();
	}
}