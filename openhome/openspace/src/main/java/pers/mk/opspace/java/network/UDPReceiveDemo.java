package pers.mk.opspace.java.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2023/2/8 13:34
 */
public class UDPReceiveDemo {

    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(12345);
        while (true){
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            ds.receive(dp);
            System.out.println("收到的数据：" + new String(dp.getData(),0,dp.getLength()));
        }

    }

}
