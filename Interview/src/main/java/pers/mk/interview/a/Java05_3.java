package pers.mk.interview.a;

import java.util.LinkedList;
import java.util.Queue;

public class Java05_3 {
    private static final int CAPACITY = 1; // 缓冲区容量设为1更容易暴露问题
    private static final Queue<Integer> buffer = new LinkedList<>();
    private static final Object lock = new Object();

    // 有问题的生产者实现（使用if）
    static class Producer implements Runnable {
        private int value = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // 危险！这里使用if而不是while
                    if (buffer.size() == CAPACITY) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ": 缓冲区满，等待");
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    // 生产数据
                    System.out.println(Thread.currentThread().getName() + ": 生产 " + value);
                    buffer.offer(value++);

                    lock.notifyAll(); // 通知消费者
                }

                try {
                    Thread.sleep(100); // 模拟生产耗时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // 有问题的消费者实现（使用if）
    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // 危险！这里使用if而不是while
                    if (buffer.isEmpty()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ": 缓冲区空，等待");
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    // 消费数据
                    int value = buffer.poll();
                    System.out.println(Thread.currentThread().getName() + ": 消费 " + value);

                    lock.notifyAll(); // 通知生产者
                }

                try {
                    Thread.sleep(150); // 模拟消费耗时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 创建2个生产者和2个消费者（更容易暴露问题）
        Thread p1 = new Thread(new Producer(), "生产者1");
        Thread p2 = new Thread(new Producer(), "生产者2");
        Thread p3 = new Thread(new Producer(), "生产者3");
        Thread c1 = new Thread(new Consumer(), "消费者1");


        p1.start();
        p2.start();
        p3.start();
        c1.start();
    }

}
