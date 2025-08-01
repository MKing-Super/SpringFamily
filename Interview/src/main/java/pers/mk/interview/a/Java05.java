package pers.mk.interview.a;

import java.time.LocalDateTime;
/*
sleep和wait区别
 */
public class Java05 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("-------- sleep() 示例");
        // sleep() 示例
        System.out.println("程序开始，当前时间: " + LocalDateTime.now());
        try {
            // 主线程休眠3秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3秒后，当前时间: " + LocalDateTime.now());

        // 多个线程使用sleep()
        new Thread(() -> {
            System.out.println("线程1开始执行: " + LocalDateTime.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1结束执行: " + LocalDateTime.now());
        }).start();

        new Thread(() -> {
            System.out.println("线程2开始执行: " + LocalDateTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2结束执行: " + LocalDateTime.now());
        }).start();

        Thread.sleep(2000);

        System.out.println("----- wait()/notify() 示例");

        final Object lock = new Object();
        // 等待线程
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("等待线程获取锁，开始等待: " + LocalDateTime.now());
                try {
                    lock.wait(); // 释放锁并等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("等待线程被唤醒: " + LocalDateTime.now());
            }
        }).start();

        // 唤醒线程
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("唤醒线程获取锁，准备唤醒: " + LocalDateTime.now());
                try {
                    Thread.sleep(3000); // 模拟工作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify(); // 唤醒等待的线程
                System.out.println("已发出唤醒通知: " + LocalDateTime.now());
            }
        }).start();

    }

}
