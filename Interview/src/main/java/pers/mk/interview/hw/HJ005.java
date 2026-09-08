package pers.mk.interview.hw;

import java.util.Scanner;

public class HJ005 {
    public static void main(String[] args) {
//        mk();
        m1();
//        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String num16 = in.nextLine();
        num16 = num16.substring(2);
        int total = 0;
        for(int i = 0 ; i < num16.length() ; i++){
            // 注意不要用 char 要用 string 否则无法转成 int
            String n = String.valueOf(num16.charAt(i)).toUpperCase() ;
            int n1 = 0;
            if("A".equals(n)){
                n1 = 10;
            }else if( "B".equals(n)){
                n1 = 11;
            }else if("C".equals(n)){
                n1 = 12;
            }else if( "D".equals(n)){
                n1 = 13;
            }else if( "E".equals(n)){
                n1 = 14;
            }else if( "F".equals(n)){
                n1 = 15;
            }else{
                n1 = Integer.valueOf(n);
            }

            total += n1*Math.pow(16,num16.length() - 1 - i);
        }

        System.out.print(total);
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String str = in.nextLine();
        char[] chars = str.toCharArray();
        int n = chars.length;
        long res = 0;
        for(int i = 2; i < n; i++){
            if('0' <= chars[i] && chars[i] <= '9'){
                res += (chars[i] - '0') * Math.pow(16, n - i - 1);
            }else{
                res += (chars[i] - 'A' + 10) * Math.pow(16, n - i - 1);
            }
        }
        System.out.println(res);
    }

    private static void m2(){

    }

}
