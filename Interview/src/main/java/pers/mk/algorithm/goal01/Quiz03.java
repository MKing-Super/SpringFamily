package pers.mk.algorithm.goal01;

import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().split(" ");
        System.out.println(split[split.length - 1].length());
    }

    private static void m1(){

    }
}
