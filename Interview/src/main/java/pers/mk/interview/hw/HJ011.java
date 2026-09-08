package pers.mk.interview.hw;

import java.util.Scanner;

public class HJ011 {
    public static void main(String[] args) {
//        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String nStr = in.nextLine();
        String[] nArr = nStr.split("");
        for(int i = 0 ; i < nArr.length / 2 ; i++){
            String temp = "";
            temp = nArr[i];
            nArr[i] = nArr[nArr.length - 1 - i];
            nArr[nArr.length - 1 - i] = temp;
        }

        for(int i = 0 ; i < nArr.length ; i++){
            System.out.print(nArr[i]);
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String s=in.nextLine();
        for(int i=s.length()-1;i>=0;i--){
            System.out.print(s.charAt(i));
        }
    }

    private static void m2(){

    }

}
