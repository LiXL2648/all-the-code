package com.li.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author LiXL
 * @date 2024/4/20
 */
@SpringBootTest
public class DistributedLockApplicationTests {

    @Test
    public void testZookeeper() {
        ZooKeeper zooKeeper = null;
        try {
            // 由于该获取连接是异步执行，防止在外部使用zooKeeper对象空指针
            // 因此需要使用闭锁进行阻塞
            // 获取zookeeper连接
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper("192.168.26.48:2181", 30000, watchedEvent -> {

                Watcher.Event.KeeperState state = watchedEvent.getState();
                if (Watcher.Event.KeeperState.SyncConnected.equals(state) && Watcher.Event.EventType.None.equals(watchedEvent.getType())) {
                    System.out.println("获取连接");
                    countDownLatch.countDown();
                } else if (Watcher.Event.KeeperState.Closed.equals(state)) {
                    System.out.println("获取关闭");
                }
                // 可在此监听节点事件
                else {
                    System.out.println("节点事件：" + watchedEvent.getType().toString());
                }
            });
            countDownLatch.await();
            // 新增节点
            // 永久
            zooKeeper.create("/lixl/yucx", "ycx".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            /*// 临时
            zooKeeper.create("/lixl/test1", "test1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            // 永久序列化
            zooKeeper.create("/lixl/test2-", "test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            zooKeeper.create("/lixl/test2-", "test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            zooKeeper.create("/lixl/test2-", "test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            // 临时序列化
            zooKeeper.create("/lixl/test3-", "test3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zooKeeper.create("/lixl/test3-", "test3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zooKeeper.create("/lixl/test3-", "test3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            */

            // 查询节点
            // 查询判断节点是否存在，绑定事件类似于 stat -w
            Stat stat = zooKeeper.exists("/lixl/yucx", watchedEvent -> {
                Watcher.Event.EventType type = watchedEvent.getType();
                if (Watcher.Event.EventType.NodeCreated.equals(type)) {
                    System.out.println("节点创建");
                } else if (Watcher.Event.EventType.NodeDeleted.equals(type)) {
                    System.out.println("节点删除");
                } else if (Watcher.Event.EventType.NodeDataChanged.equals(type)) {
                    System.out.println("节点数据修改");
                }
            });
            if (stat != null) {
                System.out.println("节点/lixl/yucx存在");
            } else {
                System.out.println("节点/lixl/yucx不存在");
            }
            // 获取当前节点中的数据，绑定事件类似于 get -w
            byte[] data = zooKeeper.getData("/lixl/yucx", true, stat);
            System.out.println("节点/lixl/yucx内容为：" + new String(data));
            // 获取当前节点的子节点，绑定事件类似于 ls -w
            List<String> children = zooKeeper.getChildren("/lixl", watchedEvent -> {
                System.out.println("子结点发生变化");
            });
            System.out.println("节点/lixl/yucx的子结点为：" + children);

            // 更新节点：版本号必须和当前节点版本号一致，否则更新失败，也可以指定为-1，代表不关心版本号
            stat = zooKeeper.setData("/lixl/yucx", "ycx 0829".getBytes(), stat.getVersion());

            // 删除
            zooKeeper.delete("/lixl/yucx", stat.getVersion());
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
