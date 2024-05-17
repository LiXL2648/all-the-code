package com.li.config.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.CountDownLatch;

/**
 * @author LiXL
 * @date 2024/4/21
 */
@Component
public class ZKClient {

    private ZooKeeper zooKeeper;

    @PostConstruct
    public void init() {
        // 容器启动时，创建连接
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper("192.168.26.48:2181", 30000, watchedEvent -> {
                Watcher.Event.KeeperState state = watchedEvent.getState();
                if (Watcher.Event.KeeperState.SyncConnected.equals(state) && Watcher.Event.EventType.None.equals(watchedEvent.getType())) {
                    System.out.println("获取连接");
                    countDownLatch.countDown();
                } else if (Watcher.Event.KeeperState.Closed.equals(state)) {
                    System.out.println("关闭连接");
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        // 释放 zk 连接
        try {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public DistributedZKLock getLock(String lockName) {
        return new DistributedZKLock(zooKeeper, lockName);
    }
}
