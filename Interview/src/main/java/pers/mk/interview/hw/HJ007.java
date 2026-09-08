package pers.mk.interview.hw;

import java.util.Scanner;

public class HJ007 {
    public static void main(String[] args) {
//        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        double a =  in.nextDouble();
        if(a%1 >= 0.5){
            System.out.println((int)a + 1);
        }else{
            System.out.println((int)a );
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        double a=in.nextDouble();
        double b=a%1;
        int s=0;
        if(b>0.5||b==0.5){
            s= (int) (a-b+1);
        }else{
            s= (int) (a-b);
        }
        System.out.print(s);
    }

    private static void m2(){

    }

}
