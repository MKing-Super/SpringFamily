package pers.mk.interview.a;

import java.util.LinkedList;
import java.util.Queue;

/*
对sleep和wait中锁的理解
 */
public class Java05_1 {

    private static final int CAPACITY = 5;
    private static final Queue<Integer> queue = new LinkedList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 生产者
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = 0;
            while (true) {
                synchronized (lock) {
                    while (queue.size() == CAPACITY) {
                        try {
                            System.out.println("队列已满，生产者等待...");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("生产者生产: " + value);
                    queue.offer(value++);

                    lock.notifyAll();

                    try {
                        Thread.sleep(500); // 模拟生产时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // 消费者
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("队列为空，消费者等待...");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    int value = queue.poll();
                    System.out.println("消费者消费: " + value);

                    lock.notifyAll();

                    try {
                        Thread.sleep(1000); // 模拟消费时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
