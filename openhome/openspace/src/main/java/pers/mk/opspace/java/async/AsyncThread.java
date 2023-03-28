package pers.mk.opspace.java.async;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2023/1/31 13:35
 */
public class AsyncThread extends Thread{

    private static Integer num = 200;
    static CountDownLatch timeOutCountDownLatch = new CountDownLatch(num);
    public static Integer count = 0;

    @Override
    public void run() {
//        System.out.println("当前线程名称:" + this.getName() + ", 执行线程名称:" + Thread.currentThread().getName() + "-hello");

        Integer businessId = 0;
        Integer userId = 0;
        Random random = new Random();
        for (int i = 0 ; i < 1 ; i++){
            businessId = random.nextInt(1000);
            userId = random.nextInt(1000);
            String reqStr = "vas-wap.autostreets.com/autostValuation/gzBuryingPoint?" +
                    "t=" + System.currentTimeMillis() +
                    "&modules=%5B%7B%22duration%22%3A0%2C%22name%22%3A%22%E8%BD%A6%E8%BE%86%E4%BF%A1%E6%81%AF%22%7D%2C%7B%22duration%22%3A0%2C%22name%22%3A%22%E6%B1%BD%E8%BD%A6%E8%A1%97%E6%8B%8D%E5%8D%96%E4%BC%B0%E5%80%BC%E5%88%86%E6%9E%90%22%7D%2C%7B%22duration%22%3A0%2C%22name%22%3A%22%E5%B8%82%E5%9C%BA%E7%BB%BC%E5%90%88%E7%83%AD%E5%BA%A6%E5%88%86%E6%9E%90%22%7D%2C%7B%22duration%22%3A0%2C%22name%22%3A%22%E5%8E%86%E5%8F%B2%E6%88%90%E4%BA%A4%E5%88%86%E6%9E%90%22%7D%2C%7B%22duration%22%3A0%2C%22name%22%3A%22%E6%B1%BD%E8%BD%A6%E8%A1%97%E5%90%8C%E8%BD%A6%E5%9E%8B%E5%8E%86%E5%8F%B2%E6%88%90%E4%BA%A4%E8%AE%B0%E5%BD%95%22%7D%2C%7B%22duration%22%3A0%2C%22name%22%3A%22%E8%BD%A6%E5%9E%8B%E4%B8%BB%E8%A6%81%E5%9F%8E%E5%B8%82%E4%BB%B7%E6%A0%BC%22%7D%2C%7B%22duration%22%3A0%2C%22name%22%3A%22%E6%9C%AA%E6%9D%A5%E6%AE%8B%E5%80%BC%E8%B5%B0%E5%8A%BF%22%7D%2C%7B%22duration%22%3A0%2C%22name%22%3A%22%E8%BD%A6%E5%9E%8B%E5%B9%B3%E5%9D%87%E8%BD%A6%E5%86%B5%22%7D%5D&source=2" +
                    "&businessId=" +  businessId +
                    "&userId=" + userId;
            String s = HttpUtil.get(reqStr);
            JSONObject jsonObject = JSON.parseObject(s);
            Boolean success = jsonObject.getBoolean("success");
            if (!success){
                count++;
                System.out.println("当前线程名称:" + this.getName() + "当前 =》 " + i);
            }
        }
        timeOutCountDownLatch.countDown();
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0 ; i < num ; i++){
            AsyncThread asyncThread = new AsyncThread();
            asyncThread.start();
        }
        timeOutCountDownLatch.await();
        System.out.println(AsyncThread.count);
    }



}
