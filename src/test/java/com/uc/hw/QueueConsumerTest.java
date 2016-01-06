package com.uc.hw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class QueueConsumerTest {

	@Autowired
	private QueueConsumerService consumerService;

	private void receive() {
		consumerService.receive();
	}

	@Test
	public void test() {
		receive();
	}

}
