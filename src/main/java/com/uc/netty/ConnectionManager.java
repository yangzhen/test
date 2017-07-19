package com.uc.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yangzhen on 17/7/14.
 */
public class ConnectionManager {

    private static ConnectionManager instance = new ConnectionManager();

    LinkedBlockingQueue queue = new LinkedBlockingQueue();

    ExecutorService pool = Executors.newFixedThreadPool(5);


    private ConnectionManager() {

    }

    public static ConnectionManager getInstance() {
        return instance;
    }


    public void put(final ConnBean bean) {
//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                ChannelHandlerContext ctx = bean.ctx;
//                String text = bean.text;
//                System.out.println("threadName:" + Thread.currentThread().getName() + ",text:" + text);
//                ctx.write(Unpooled.copiedBuffer(text.getBytes()));
//
//            }
//        });

        new Thread() {
            @Override
            public void run() {
                ChannelHandlerContext ctx = bean.ctx;
                String text = bean.text;
                System.out.println("threadName:" + Thread.currentThread().getName() + ",text:" + text);
                ctx.write(Unpooled.copiedBuffer(text.getBytes()));
            }
        }.start();
    }

    public ConnBean get() {
        return (ConnBean) queue.poll();
    }

    public void run() {

    }
}
