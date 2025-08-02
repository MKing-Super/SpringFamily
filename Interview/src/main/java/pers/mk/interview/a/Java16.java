package pers.mk.interview.a;


import java.util.concurrent.*;

public class Java16 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //继承thread
        ThreadClass thread = new ThreadClass();
        thread.start();
        Thread.sleep(100);
        System.out.println("#####################");

        //实现runnable
        RunnableClass runnable = new RunnableClass();
        new Thread(runnable).start();
        Thread.sleep(100);
        System.out.println("#####################");

        //实现callable
        FutureTask futureTask = new FutureTask(new CallableClass());
        futureTask.run();
        System.out.println("callable返回值：" + futureTask.get());
        Thread.sleep(100);
        System.out.println("#####################");

        //线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.shutdown();
        Thread.sleep(100);
        System.out.println("#####################");

        //使用并发包Executors
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(thread);
        executorService.shutdown();


    }

}


class ThreadClass extends Thread{
    @Override
    public void run() {
        System.out.println("我是继承thread形式：" + Thread.currentThread().getName());
    }
}

class RunnableClass implements Runnable{
    @Override
    public void run(){
        System.out.println("我是实现runnable接口：" + Thread.currentThread().getName());
    }
}

class CallableClass  implements Callable<String> {
    @Override
    public String call(){
        System.out.println("我是实现callable接口：");
        return "我是返回值，可以通过get方法获取";
    }
}