package pers.mk.interview.a;


import java.util.concurrent.atomic.AtomicInteger;

public class Java17 {

    public static void main(String[] args) throws InterruptedException {
//        mehtod1();
//        mehtod2();
//        mehtod3();
//        mehtod4();
//        method5();
        method6();


    }

    static void method6() throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // 执行任务
                System.out.println("Working...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("收到中断信号，准备退出");
                    Thread.currentThread().interrupt(); // 重新设置中断状态
                    break;
                }
            }
        });

        worker.start();
        Thread.sleep(2000);
        worker.interrupt(); // 发送中断信号
    }


    static void mehtod1() throws InterruptedException {
        System.out.println("---------- 示例1：主线程等待子线程结束");
        Thread worker = new Thread(() -> {
            System.out.println("子线程开始工作");
            try {
                Thread.sleep(2000); // 模拟耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程工作完成");
        });

        worker.start();
        System.out.println("主线程等待子线程...");
        worker.join(); // 主线程进入WAITING状态
        System.out.println("主线程继续执行");
    }

    static void mehtod2() throws InterruptedException {
        System.out.println("------------- 示例2：多个线程顺序执行");
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1完成");});
        Thread t2 = new Thread(() -> System.out.println("线程2完成"));
        Thread t3 = new Thread(() -> System.out.println("线程3完成"));

        t1.start();
        t1.join();  // 等待t1结束

        t2.start();
//        t2.join();  // 等待t2结束

        t3.start();
//        t3.join();  // 等待t3结束

        System.out.println("所有线程执行完毕");
    }

    static void mehtod3() throws InterruptedException {
        System.out.println("----------------- 示例3：限制等待时间");
        Thread longTask = new Thread(() -> {
            try {
                Thread.sleep(5000); // 模拟5秒任务
                System.out.println("长时间任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        longTask.start();
        System.out.println("主线程开始等待(最多3秒)");
        longTask.join(3000); // TIMED_WAITING状态

        if (longTask.isAlive()) {
            System.out.println("等待超时，任务仍在运行");
        } else {
            System.out.println("任务已完成");
        }
    }

    static void mehtod4() throws InterruptedException {
        System.out.println("------------------- 示例4：观察join()时的线程状态");
        Thread mainThread = Thread.currentThread();

        Thread observer = new Thread(() -> {
            try {
                System.out.println("observer start " + Thread.currentThread().getName());
                Thread.sleep(100); // 确保主线程先进入join
                System.out.println("观察者看到主线程状态: " + mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread worker = new Thread(() -> {
            try {
                System.out.println("worker start " + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        observer.start();
        worker.start();
        System.out.println(mainThread + " 等待worker执行完再执行main线程");
        worker.join(); // 主线程进入WAITING

        System.out.println("主线程恢复执行");
    }

    static void method5(){
        AtomicInteger counter1 = new AtomicInteger();
        AtomicInteger counter2 = new AtomicInteger();

        Thread t1 = new Thread(() -> {
            while (counter1.get() < 1000000) {
                counter1.incrementAndGet();
                Thread.yield();
            }
        });

        Thread t2 = new Thread(() -> {
            while (counter2.get() < 1000000) {
                counter2.incrementAndGet();
                // 不使用yield
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("使用yield的线程计数: " + counter1.get());
        System.out.println("不使用yield的线程计数: " + counter2.get());
    }

}
