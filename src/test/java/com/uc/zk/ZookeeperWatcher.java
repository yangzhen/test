package com.uc.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;

/**
 * Created by yangzhen on 16/11/22.
 */
public class ZookeeperWatcher implements Watcher{

    Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    //zk处理
    @Override
    public void process(WatchedEvent event) {
        System.out.println("=======,"+event.getPath()+","+event.getState()+","+event.getType()+","+event.toString());
        logger.info("=======,"+event.getPath()+","+event.getState()+","+event.getType()+","+event.toString());
    }

}
