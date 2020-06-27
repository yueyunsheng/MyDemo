package com.feng.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

public class AclDemo {

    private static String CONNECTION_STR = "106.53.66.20:2181";

    public static void main(String[] args) throws Exception {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(CONNECTION_STR).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

        curatorFramework.start();
        List<ACL> list = new ArrayList<ACL>();
        ACL acl = new ACL(ZooDefs.Perms.READ|ZooDefs.Perms.WRITE,new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        list.add(acl);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(list)
                .forPath("/auth");

    }
}
