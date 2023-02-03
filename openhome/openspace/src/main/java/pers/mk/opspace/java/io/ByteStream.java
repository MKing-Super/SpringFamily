package pers.mk.opspace.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description: 字节流
 * @Author: kun.ma
 * @Date: 2023/2/2 13:40
 */
public class ByteStream {

    public static void main(String[] args) throws IOException {

    }

    private static void test8() throws IOException {
        File file = new File("D:/test.txt");
        FileInputStream inp = new FileInputStream(file);
        FileOutputStream oup = new FileOutputStream("D:/test/test.txt");
        byte[] arr = new byte[(int)file.length()];
        inp.read(arr);
        oup.write(arr);
        inp.close();
        oup.close();
    }

    private static void test7() throws IOException {
        OutputStream oup = new FileOutputStream("D:/test.txt",true);
        byte[] arr = {'a',97,'b','c'};
        byte[] arr1 = "\r\n".getBytes();
        byte[] chinese = "中国".getBytes();
        oup.write(arr1);
        oup.write(arr);
        oup.write(chinese);
        oup.write(arr1);
        oup.close();
    }

    private static void test6() throws IOException {
        OutputStream oup = new FileOutputStream("D:/test.txt");
        byte[] arr = {'a',97,'b','c'};
        byte[] chinese = "\r\n".getBytes();
        oup.write(chinese);
        oup.write(arr);
        oup.close();
    }

    private static void test5() throws IOException {
        OutputStream oup = new FileOutputStream("D:/test.txt");
        byte[] arr = {'a',97,'b',98,'c'};
        byte[] chinese = "中国".getBytes();
        oup.write(arr);
        oup.write(chinese);
        oup.close();
    }

    private static void test4() throws IOException {
        OutputStream oup = new FileOutputStream("D:/test.txt");
        oup.write('G');
        oup.write(97);
        oup.flush();
        oup.close();
    }

    private static void test3() throws IOException {
        File file = new File("D:/test.txt");
        FileInputStream inp = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        int read = inp.read(bytes);
        System.out.println(read);
        String s = new String(bytes);
        System.out.println(s);
    }

    private static void test2() throws IOException {
        FileInputStream inp = new FileInputStream(new File("D:/test.txt"));
        byte[] arr = new byte[4];
        int len;
        while ((len = inp.read(arr)) != -1){
            String res = new String(arr, 0, len);
            System.out.println(res);
        }
    }

    private static void test1() throws IOException {
        FileInputStream inp = new FileInputStream(new File("D:/test.txt"));
        byte[] arr = new byte[3];
        int len = inp.read(arr);
        String res = new String(arr);
        System.out.println(res);
    }

    private static void test0() throws IOException {
        FileInputStream inp = new FileInputStream(new File("D:/test.txt"));
        int read = inp.read();
        System.out.println((char) read);
    }

}
