package com.uc.util;

import java.net.URL;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTestAbstact {

	static {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("logback.xml");
		String name = url.getFile();
		System.setProperty("logback.configurationFile", name);
	}

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

}