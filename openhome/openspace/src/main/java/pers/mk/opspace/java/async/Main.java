package pers.mk.opspace.java.async;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2023/1/31 13:35
 */
public class Main {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
//        test0();
//        test1();
//        test2();
//        test3();


    }

    private static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @SneakyThrows
            @Override
            public Long get() {
                Thread.sleep(100);
                return 9999L;
            }
        });
        while (!completableFuture.isDone()) {
            Thread.sleep(20);
            System.out.println("CompletableFuture is not finished yet...");
        }
        long result = completableFuture.get();
        System.out.println(result);
    }

    private static void test2() throws ExecutionException, InterruptedException {
        System.out.println("main 开始");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future0 = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                System.out.println("===task0 start===");
                Thread.sleep(1000);
                System.out.println("===task0 finish===");
                return 3;
            }
        });
        Future<Integer> future1 = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                System.out.println("===task1 start===");
                Thread.sleep(1000);
                System.out.println("===task1 finish===");
                return 6;
            }
        });

        System.out.println("main函数执行结束");
        //这里需要返回值时会阻塞主线程，如果不需要返回值使用是OK的。倒也还能接收
        System.out.println(future0.get());
        System.out.println(future1.get());
    }

    private static void test1(){
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("bed");
            }
        });
        System.out.println("end");
    }

    private static void test0() throws InterruptedException {
        AsyncThread asyncThread = new AsyncThread();
        asyncThread.start();
        System.out.println(">>>>>>>> end");
        Thread.sleep(1000);
        System.out.println("........ end");
    }

}
