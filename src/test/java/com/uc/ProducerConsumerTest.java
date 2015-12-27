package com.uc;
 
import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ProducerConsumerTest {
 
    @Autowired
    private ProducerServiceImpl producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;
    
    @Test
    public void testSend() throws InterruptedException {
    	Thread.sleep(1000 *10);
        for (int i=0; i<5; i++) {
        	try {
				Thread.sleep(1000 *1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));
        }
        Thread.sleep(1000 *10);
    }
    
}
