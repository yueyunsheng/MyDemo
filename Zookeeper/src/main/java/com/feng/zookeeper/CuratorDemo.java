package com.feng.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorDemo {


    private static String CONNECTION_STR = "106.53.66.20:2181";
    public static void main(String[] args) throws Exception {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(CONNECTION_STR).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

        //ExponentialBackoffRetry 衰减重试
        //RetryOneTime 只重试一次
        //RetryUntilElapsed

        curatorFramework.start(); //启动
     //   createData(curatorFramework);  //创建
      //  updateDate(curatorFramework);  //修改
        deleteDate(curatorFramework); //删除
    }
    private static void createData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                forPath("/data/program","test".getBytes());


    }

    private static void updateDate(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData().
                forPath("/data/program","update".getBytes());

    }

    private static void deleteDate(CuratorFramework curatorFramework) throws Exception {
        Stat stat = new Stat();
        String value = new String(curatorFramework.getData().storingStatIn(stat).forPath("/data/program"));
        curatorFramework.delete().withVersion(stat.getVersion()).forPath("/data/program");

    }

}
