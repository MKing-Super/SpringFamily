package pers.mk.interview.a;

/*
锁资源指的是通过synchronized关键字或Lock对象获取的线程同步控制权。当一个线程持有锁时，其他试图获取同一锁的线程会被阻塞。
 */
public class Java05_2 {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 线程1：演示sleep不释放锁
        new Thread(() -> {
            try {
                Thread.sleep(200); // 确保线程1先获取锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                System.out.println("线程1获取锁，开始sleep");
                try {
                    Thread.sleep(3000); // 不释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1结束sleep，释放锁");
            }
        }).start();

        // 线程2：演示wait释放锁
        new Thread(() -> {
            try {
                Thread.sleep(100); // 确保线程1先获取锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                System.out.println("线程2获取锁，开始wait");
                try {
                    lock.wait(2000); // 释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2被唤醒，重新获得锁");
            }
        }).start();
    }
}
