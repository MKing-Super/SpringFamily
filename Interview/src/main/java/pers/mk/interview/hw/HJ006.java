package pers.mk.interview.hw;

import java.util.Scanner;

public class HJ006 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        long end = (long)Math.sqrt(n);
        for(int i  = 2 ; i <= end ; ){
            if(n%i == 0){
                System.out.print(i + " ");
                n = n/i;
            }else{
                i++;
            }
        }
        if(n > 1){
            System.out.print(n);
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
