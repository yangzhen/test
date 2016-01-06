package com.uc.util;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BaseTestAbstact {

	static {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("logback.xml");
		String name = url.getFile();
		System.setProperty("logback.configurationFile", name);
	}

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

}