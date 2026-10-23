package pers.mk.algorithm.goal05;

import java.util.Scanner;

public class Quiz09 {
    public static void main(String[] args) {
        // 除2取余法
//        mk();

        // 位运算
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());

        int count = 0;
        while (n > 0){
            if (n % 2 != 0){
                count++;
            }
            n = n / 2;
        }

        System.out.println(count);
    }


    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());

        int count =0;
        while (n != 0){
            n = n & (n - 1);
            count++;
        }
        System.out.println(count);
    }

}
