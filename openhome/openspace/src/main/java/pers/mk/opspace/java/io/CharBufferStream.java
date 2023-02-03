package pers.mk.opspace.java.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description: 字符缓存流
 * @Author: kun.ma
 * @Date: 2023/2/3 9:10
 */
public class CharBufferStream {

    public static void main(String[] args) throws Exception {




    }

    private static void test2() throws IOException {
        FileWriter w = new FileWriter("D:/test.txt");
        BufferedWriter bw = new BufferedWriter(w);
        bw.write('我');
        bw.newLine();
        bw.write("wozhaole");
        bw.append("啊啊啊啊啊");
        bw.close();
    }

    private static void test1() throws IOException {
        FileReader r = new FileReader("D:/test.txt");
        BufferedReader br = new BufferedReader(r);
        String vv;
        while ((vv = br.readLine()) != null){
            System.out.println(vv);
        }
    }

    private static void test0() throws IOException {
        FileReader r = new FileReader("D:/test.txt");
        BufferedReader br = new BufferedReader(r);
        char[] arr = new char[1024];
        int len;
        while ((len = br.read(arr)) != -1){
            String s = new String(arr, 0, len);
            System.out.println(s);
        }
        br.close();
    }

}
