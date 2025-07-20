package com.li.base;

import java.util.concurrent.TimeUnit;

public class DaemonDemo {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 开始运行，" +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true) {

            }
        });
        thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 主线程结束");
    }
}
