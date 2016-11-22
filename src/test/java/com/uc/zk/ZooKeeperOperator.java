package com.uc.zk;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 *
 * Descriptions of the class QueryManager.java's implementation：
 * luna ha server
 *
 * @author wangzhao
 */
public class ZooKeeperOperator {

    private Logger logger = Logger.getLogger(ZooKeeperOperator.class);

    private static final int SESSION_TIME = 10000;

    private String ip;
    private static ZooKeeper zooKeeper;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    /**
     * 监听child状态
     */
    public class haWatcher implements Watcher{

        @Override
        public void process(WatchedEvent event) {
            System.out.println("event getType ......"+event.getPath()+"\n"+event.getType());
            switch(event.getType()){
                case None:
                    System.out.println("event getType NONE ......");
                    connectedSignal.countDown();
                    break;
                case NodeDeleted:
                    System.out.println("Node " + event.getPath() + " deleted");
                    try {
                        connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ;
                case NodeCreated:
                    System.out.println("Node " + event.getPath() + " created");
                    break;
                case NodeDataChanged:
                    System.out.println("Children changed for node " + event.getPath());
                    nodeDataChanged("111");
                    break;
                default:
                    System.out.println("其它为考虑到异常");
                    break;

            }


        }

        /**
         * @desc 处理session会话状态异常
         * @param event
         * @throws InterruptedException
         * @throws KeeperException
         * @throws IOException
         * @throws SQLException
         */
        public void haSessionDeal(WatchedEvent event) throws IOException,
                KeeperException, InterruptedException, SQLException {
            System.out.println("session会话状态：" + event.getState());

            switch (event.getState()) {
                case SyncConnected:
                    // 放开闸门, wait在connect方法上的线程将被唤醒
                    if (connectedSignal != null) {
                        connectedSignal.countDown();
                    };
                    break;
                case Expired:
                    // session过期,关掉zk连接，重新连接zk，申请新的sessionId
                    System.out.println(event.getState());
                    connect();
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 连接ZK
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws KeeperException
     */
    public synchronized void connect() throws IOException, KeeperException,
            InterruptedException {
        System.out.println("connect ......");
        try {
            // 连接ZK
            zooKeeper = new ZooKeeper("127.0.0.1:2181", SESSION_TIME, new haWatcher());
            connectedSignal.await();

        } catch (Exception e) {
            Thread.sleep(1000 * 10);
        }
    }

    /**
     * @desc data发生变化重新set
     */
    public void nodeDataChanged(String text){
        System.out.print(zooKeeper+"\t"+text);
        try {
            zooKeeper.setData("/wangzhao", text.getBytes(), -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化zk
     * @throws InterruptedException
     * @throws KeeperException
     * @throws IOException
     * @throws SQLException
     */
    public void init() throws IOException, KeeperException, InterruptedException, SQLException {
        connect();

    }

    public void create () throws Exception{
        zooKeeper.exists("/wangzhao", new haWatcher());
    }

    public static void main(String [] args) throws Exception{
        ZooKeeperOperator zk = new ZooKeeperOperator();
        zk.init();
        zk.create();
        while(true){
            System.out.println("aaaa.....");
            zooKeeper.setData("/wangzhao", "aaaa".getBytes(), -1);
            Thread.sleep(2000);
            System.out.println("456.....");
            zooKeeper.setData("/wangzhao", "456".getBytes(), -1);
            Thread.sleep(2000);
            zooKeeper.create("/hhh","hhh".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            Thread.sleep(2000);
            zooKeeper.delete("/hhh",-1);
            Thread.sleep(2000);
        }
    }

}
