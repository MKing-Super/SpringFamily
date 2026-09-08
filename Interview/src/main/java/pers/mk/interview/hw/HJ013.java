package pers.mk.interview.hw;

import java.util.Scanner;

public class HJ013 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String nStr = in.nextLine();
        String[] nArr = nStr.split(" ");
        for(int i = nArr.length - 1 ; i >= 0 ; i--){
            System.out.print(nArr[i] + " ");
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
