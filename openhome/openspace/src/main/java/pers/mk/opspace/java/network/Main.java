package pers.mk.opspace.java.network;

import java.io.InputStream;
import java.net.InetAddress;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2023/2/8 11:31
 */
public class Main {

    public static void main(String[] args) throws Exception {

        InetAddress address = InetAddress.getByName("www.bilibili.com");

        String hostName = address.getHostName();
        String hostAddress = address.getHostAddress();
        boolean loopbackAddress = address.isLoopbackAddress();
        System.out.println(hostName);
        System.out.println(hostAddress);
        InputStream in = System.in;
        System.out.println(in);
    }

}
