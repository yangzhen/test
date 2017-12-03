package com.uc.rpc;


import backtype.storm.LocalCluster;

/**
 * Created by yangzhen on 17/9/1.
 */
public class StormTest {
    public static void main(String[] args) {
        LocalCluster cluster = new LocalCluster();
        cluster.shutdown();
    }
}
