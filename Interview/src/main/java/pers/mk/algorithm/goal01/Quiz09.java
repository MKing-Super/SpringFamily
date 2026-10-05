package pers.mk.algorithm.goal01;

import java.util.HashSet;
import java.util.Scanner;

public class Quiz09 {
    public static void main(String[] args) {
        // 简洁
        mk();

        // 不适用set的方法
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        HashSet<Object> set = new HashSet<>();
        for (int i = 0 ; i < str.length() ; i++){
            set.add(str.charAt(i));
        }
        System.out.println(set.size());
    }

    private static void m1(){

        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();

        // ASCII 码 0-127，但实际输入范围是 33-126
        // 使用 boolean 数组标记字符是否出现过
        boolean[] seen = new boolean[128];  // 下标对应 ASCII 码
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int ascii = (int) c;

            // 确保在 0-127 范围内
            if (ascii >= 0 && ascii <= 127 && !seen[ascii]) {
                seen[ascii] = true;
                count++;
            }
        }

        System.out.println(count);

    }
}
