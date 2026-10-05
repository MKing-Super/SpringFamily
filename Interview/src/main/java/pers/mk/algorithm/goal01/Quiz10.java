package pers.mk.algorithm.goal01;

import java.util.Scanner;

public class Quiz10 {
    public static void main(String[] args) {
        // 简洁
        mk();
        // StringBuilder 的 reverse()
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        for (int i = str.length() - 1 ; i >= 0 ; i--){
            System.out.print(str.charAt(i));
        }
        System.out.println();
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println(reversed);
    }

}
