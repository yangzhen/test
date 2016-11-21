package com.uc.lj;

import com.uc.util.BaseTestAbstact;
import com.uc.util.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yangzhen on 16/10/31.
 */
@Component
public class HouseMainTest extends BaseTestAbstact {

    @Autowired
    private LJTest lj;

    @Autowired
    private WJTest wj;

    @Autowired
    private HshbTest hshb;

    @Test

    public void testAll() {
        CountDownLatch latch = new CountDownLatch(3);
        try {
            new Thread(){
                @Override
                public void run() {
                    lj.dohh(latch);
                }
            }.start();

            new Thread(){
                @Override
                public void run() {
                    wj.dohh(latch);
                }
            }.start();

            new Thread() {
                @Override
                public  void run() {
                    hshb.dohh(latch);
                }
            }.start();
            latch.await();
            logger.info("all job end,time:" + DateUtils.getCurrentDateTimeStr());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
