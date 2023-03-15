package pers.mk.opspace.java.async;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.util.concurrent.*;

/**
 * @describe: 创建线程的四种方式 && 同步工具类
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.java.async
 * @Date 2023/3/9 14:01
 * @Version 1.0
 */
public class Learn {



    /**
     * @describe: 继承Thread类
     * @param: []
     * @return: void
     * @author: MK
     * @version: 1.0.0
     * @date 2023/3/9 13:58
     **/
    static void method1(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("创建线程方法一");
            }
        };
        thread.start();
    }

    /** 
     * @describe: 实现Runnable接口（推荐）
     * @param: []
     * @return: void
     * @author: MK
     * @version: 1.0.0
     * @date 2023/3/9 14:02
     **/
    static void method2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("创建线程方法二");
            }
        }).start();
    }
    
    /** 
     * @describe: 实现Callable接口，并且结合Future实现（底层还是通过Runnable）
     * @param: []
     * @return: void
     * @author: MK
     * @version: 1.0.0
     * @date 2023/3/9 14:05
     **/
    static void method3() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "创建线程方法三";
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
    
    /** 
     * @describe: 通过JDK自带的线程池Executors来创建对象
     * @param: []
     * @return: void
     * @author: MK
     * @version: 1.0.0
     * @date 2023/3/9 14:10
     **/
    static void method4(){
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("创建线程方法四");
            }
        });
        executor.shutdown();
    }
    
    /** 
     * @describe: 同步工具类CountDownLatch（闭锁）
     * @param: []
     * @return: void
     * @author: MK
     * @version: 1.0.0
     * @date 2023/3/9 14:13
     **/
    static void method5() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("CountDownLatch 线程1 start");
                Thread.sleep(200);
                System.out.println("CountDownLatch 线程1 end");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("CountDownLatch 线程2 start");
                System.out.println("CountDownLatch 线程2 end");
                countDownLatch.countDown();
            }
        }).start();
        System.out.println(countDownLatch.getCount());
        countDownLatch.await();
        System.out.println("end <<<");
    }



    private static CyclicBarrier barrier;

    static class Worker implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            System.out.println("第一次 " + Thread.currentThread().getName());
            barrier.await();
        }
    }
    
    /** 
     * @describe: 同步工具类CycleBarrier（栅栏）
     * @param: []
     * @return: void
     * @author: MK
     * @version: 1.0.0
     * @date 2023/3/9 14:45
     **/
    static void method6() throws InterruptedException {
        barrier = new CyclicBarrier(5, new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println();
                System.out.println("我是栅栏");
//                Thread.sleep(1000);
            }
        });
        //第一个5次
        for (int i = 0 ; i < 5 ; i++){
            new Thread(new Worker()).start();
        }

        System.out.println();
        //第二个5次
        for (int i = 0 ; i < 5 ; i++){
            new Thread(new Worker()).start();
        }

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        method1();
//        method2();
//        method3();
//        method4();
//        method5();
//        method6();

        BigDecimal divide = new BigDecimal(324)
                .divide(new BigDecimal(68), BigDecimal.ROUND_CEILING);
        System.out.println(divide);


    }
    
}
