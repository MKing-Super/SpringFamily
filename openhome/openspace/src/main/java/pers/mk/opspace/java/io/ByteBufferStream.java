package pers.mk.opspace.java.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description: 字节缓冲流
 * @Author: kun.ma
 * @Date: 2023/2/2 17:05
 */
public class ByteBufferStream {

    public static void main(String[] args) throws Exception {
        test0();

    }

    private static void test0() throws IOException {
        FileInputStream inp = new FileInputStream("D:/test.txt");
        BufferedInputStream inb = new BufferedInputStream(inp);
        FileOutputStream oup = new FileOutputStream("D:/test/test.txt");
        BufferedOutputStream oub = new BufferedOutputStream(oup);

        byte[] arr = new byte[1024];
        int len;
        while ((len = inb.read(arr)) != -1){
            String s = new String(arr, 0, len);
            oub.write(s.getBytes());
        }
        inb.close();
        oub.close();
    }

}
