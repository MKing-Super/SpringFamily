package pers.mk.interview.b;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Java11 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== ABA问题演示 =====");
        demonstrateABAProblem();

        System.out.println("\n===== 使用AtomicStampedReference解决ABA问题 =====");
        solveABAWithStampedReference();
    }

    // 正确演示ABA问题
    private static void demonstrateABAProblem() throws InterruptedException {
        AtomicInteger atomicInt = new AtomicInteger(0);

        Thread thread1 = new Thread(() -> {
            // 线程1：A -> B
            boolean success1 = atomicInt.compareAndSet(0, 1);
            System.out.println("线程1: CAS(0->1) " + (success1 ? "成功" : "失败"));

            // 模拟一些处理时间
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            // B -> A
            boolean success2 = atomicInt.compareAndSet(1, 0);
            System.out.println("线程1: CAS(1->0) " + (success2 ? "成功" : "失败"));
        });

        Thread thread2 = new Thread(() -> {
            // 先让线程1执行第一次CAS
            try { Thread.sleep(50); } catch (InterruptedException e) {}

            // 线程2第一次读取值
            int firstRead = atomicInt.get();
            System.out.println("线程2: 第一次读取值 = " + firstRead);

            // 等待线程1完成ABA过程
            try { Thread.sleep(150); } catch (InterruptedException e) {}

            // 线程2尝试：期望值A(0)，新值C(2)
            boolean success = atomicInt.compareAndSet(0, 2);
            System.out.println("线程2: CAS(0->2) " + (success ? "成功" : "失败"));
            System.out.println("最终值: " + atomicInt.get());
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("结果：虽然值回到了0，但中间经历了变化（ABA问题）");
    }

    // 正确使用AtomicStampedReference解决ABA问题
    private static void solveABAWithStampedReference() throws InterruptedException {
        // 初始值0，版本号0
        AtomicStampedReference<Integer> stampedRef = new AtomicStampedReference<>(0, 0);

        Thread thread1 = new Thread(() -> {
            int[] stampHolder = new int[1];
            int currentValue = stampedRef.get(stampHolder);
            int currentStamp = stampHolder[0];

            // A -> B (版本0->1)
            boolean success1 = stampedRef.compareAndSet(
                    currentValue, 1,
                    currentStamp, currentStamp + 1
            );
            System.out.println("线程1: CAS(0->1) " + (success1 ? "成功" : "失败"));

            // 模拟一些处理时间
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            // B -> A (版本1->2)
            currentValue = stampedRef.get(stampHolder);
            currentStamp = stampHolder[0];
            boolean success2 = stampedRef.compareAndSet(
                    currentValue, 0,
                    currentStamp, currentStamp + 1
            );
            System.out.println("线程1: CAS(1->0) " + (success2 ? "成功" : "失败"));
        });

        Thread thread2 = new Thread(() -> {
            // 先让线程1执行第一次CAS
            try { Thread.sleep(50); } catch (InterruptedException e) {}

            int[] stampHolder = new int[1];
            // 线程2第一次读取值和版本号
            int firstValue = stampedRef.get(stampHolder);
            int firstStamp = stampHolder[0];
            System.out.println("线程2: 第一次读取值 = " + firstValue + ", 版本号 = " + firstStamp);

            // 等待线程1完成ABA过程
            try { Thread.sleep(150); } catch (InterruptedException e) {}

            // 线程2尝试：期望值A(0)，新值C(2)，期望版本号firstStamp
            boolean success = stampedRef.compareAndSet(
                    firstValue, 2,
                    firstStamp, firstStamp + 1
            );

            System.out.println("线程2: CAS(0->2) " + (success ? "成功" : "失败"));
            int finalValue = stampedRef.getReference();
            int finalStamp = stampedRef.getStamp();
            System.out.println("最终值: " + finalValue + ", 版本号: " + finalStamp);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("结果：版本号变化检测到了中间状态，避免了ABA问题");
    }
}