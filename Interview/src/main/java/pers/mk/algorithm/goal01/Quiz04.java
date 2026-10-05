package pers.mk.algorithm.goal01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Quiz04 {
    public static void main(String[] args) {
        mk();
        // 更简洁
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim().replaceAll(" ", "");
        str = str.toLowerCase();
        char[] chars = in.nextLine().trim().toLowerCase().toCharArray();
        char c = chars[0];
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0 ; i < str.length() ; i++ ){
            map.putIfAbsent(str.charAt(i),0);
            map.put(str.charAt(i),map.get(str.charAt(i)) + 1 );
        }
        System.out.println(map.get(c));
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);

        // 读取字符串并去除空格
        String str = in.nextLine().replaceAll(" ", "");

        // 读取目标字符并统一转为小写
        char target = in.nextLine().trim().toLowerCase().charAt(0);

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.toLowerCase(str.charAt(i)) == target) {
                count++;
            }
        }

        System.out.println(count);
    }


}
