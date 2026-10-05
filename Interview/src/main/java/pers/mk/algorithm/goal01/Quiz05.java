package pers.mk.algorithm.goal01;

import java.util.Arrays;
import java.util.Scanner;

public class Quiz05 {
    public static void main(String[] args) {
//        mk();
        // 更简洁
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        int len = str.length();
        int rows = 0;
        if (len % 8 == 0){
            rows = len / 8;
        }else {
            rows = len / 8 + 1;
        }

        for (int i = 0 ; i < rows ; i++){
            int end = 8 * i + 8;
            StringBuilder substring = new StringBuilder();
            if (end > len){
                substring = new StringBuilder(str.substring(8 * i));
            }else {
                substring = new StringBuilder(str.substring(8 * i, 8 * i + 8));
            }

            int tLen = 8 - substring.length();
            for (int j = 0 ; j < tLen ; j++){
                substring.append("0");
            }
            System.out.println(substring);
        }
    }




    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();

        // 计算需要补0的数量
        int remainder = str.length() % 8;
        if (remainder != 0) {
            int paddingCount = 8 - remainder;
            StringBuilder sb = new StringBuilder(str);
            for (int i = 0; i < paddingCount; i++) {
                sb.append('0');
            }
            str = sb.toString();
        }

        // 每8个字符输出一行
        for (int i = 0; i < str.length(); i += 8) {
            System.out.println(str.substring(i, i + 8));
        }
    }


}
