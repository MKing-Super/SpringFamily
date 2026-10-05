package pers.mk.algorithm.goal01;

import java.util.Scanner;

public class Quiz06 {
    public static void main(String[] args) {
        // 从右向左
        mk();
        // 从左向右
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim().substring(2);
        int num = 0 ;
        for (int i = str.length() - 1 ; i >= 0 ; i--){
            char c = str.charAt(i);
            int t = 0;
            if ('A' == c){
                t = 10;
            }else if ('B' == c){
                t = 11;
            }else if ('C' == c){
                t = 12;
            }else if ('D' == c){
                t = 13;
            }else if ('E' == c){
                t = 14;
            }else if ('F' == c){
                t = 15;
            }else {
                t = Integer.parseInt(String.valueOf(c));
            }
            int pow = str.length() - 1 - i;
            num += t * Math.pow(16,pow);
        }
        System.out.println(num);
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String hex = in.nextLine().trim().substring(2);  // 去掉 "0x"
        int decimal = 0;

        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            if (c >= '0' && c <= '9') {
                decimal = decimal * 16 + (c - '0');
            } else {
                decimal = decimal * 16 + (c - 'A' + 10);
            }
        }

        System.out.println(decimal);
    }
}
