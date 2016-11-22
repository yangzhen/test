package com.uc.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class AbstractZooKeeper {
    Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
  
    //缓存时间  
    private static final int SESSION_TIME  = 3000;
    protected ZooKeeper zooKeeper;  
    protected CountDownLatch countDownLatch=new CountDownLatch(1);
  
    //连接zk集群
    public void connect(String hosts) throws IOException, InterruptedException{
            zooKeeper = new ZooKeeper(hosts,SESSION_TIME, new haWatcher());
            countDownLatch.await();    
      }

    /**
     * 监听child状态
     */
    public class haWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            logger.info("======"+event.getType());
            switch (event.getType()) {
                case None:
                    try {
                        countDownLatch.countDown();
                        ;
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case NodeDeleted:
                    logger.info("Node " + event.getPath() + " deleted");
                case NodeCreated:
                    logger.info("Node " + event.getPath() + " created");
                    break;
                case NodeDataChanged:
                    logger.info("Children changed for node " + event.getPath());
                    break;
                default:
                    logger.info("其它为考虑到异常");
                    break;

            }


        }
    }

    //关闭集群
    public void close() throws InterruptedException{    
        zooKeeper.close();    
    }

}