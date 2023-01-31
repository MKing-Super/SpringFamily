package pers.mk.opspace.java.async;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2023/1/31 13:35
 */
public class AsyncThread extends Thread{

    @Override
    public void run() {
        System.out.println("当前线程名称:" + this.getName() + ", 执行线程名称:" + Thread.currentThread().getName() + "-hello");
    }
}
