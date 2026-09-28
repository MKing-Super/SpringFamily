package pers.mk.algorithm.goal03;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        // 思路完全错误！要使用位运算！
//        mk();
        //
        m1();
    }

    private static void mk(){
//        int n = 1000;
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());

        String s = "";
        if (n == 0){
            System.out.println("00");
            return;
        }else {
            while (n > 0){
                int i = n % 2;
                n = n / 2;
                s = i + s;
            }
        }

//        System.out.println(s);
//        System.out.println("----------------------");
        ArrayList<String> list = new ArrayList<>();
        for (int i = s.length() ; i > 0  ; i = i - 7){

            if (i - 7 > 0){
                String t = s.substring(i - 7, i);
//                System.out.println(t);
                t = "1" + t;
                list.add(t);
//                System.out.println(t);
            }else if (i - 7 == 0){
                String t = s.substring(i - 7, i);
//                System.out.println(t);
                t = "0" + t;
                list.add(t);
//                System.out.println(t);
            } else {
                String t = s.substring(0, i);
//                System.out.println(t);
                int bu = 8 - t.length();
                for (int j = 0 ; j < bu ; j++){
                    t = "0" + t;
                }
                list.add(t);
//                System.out.println(t);
            }
        }

//        System.out.println("----------------------------");
        List<Integer> form10List = new ArrayList<>();
        for (int i = 0 ; i< list.size() ; i++){
            String s1 = list.get(i);
            int number = 0;
            for (int j = 0 ; j < s1.length() ; j++){
                if (s1.charAt(j) == '1'){
                    number += Math.pow(2,7 - j);
                }
            }
//            System.out.println(number);
            form10List.add(number);
        }

//        System.out.println("===================================");
        for (int i = 0 ; i < form10List.size() ; i++){
            Integer form10 = form10List.get(i);
            int i1 = form10 % 16;
            int i2 = form10 / 16;
            if (i2 < 10){
                System.out.print(i2);
            }else {
                switch (i2){
                    case 10: System.out.print("A"); break;
                    case 11: System.out.print("B"); break;
                    case 12: System.out.print("C"); break;
                    case 13: System.out.print("D"); break;
                    case 14: System.out.print("E"); break;
                    case 15: System.out.print("F"); break;
                }

            }
            if (i1 < 10){
                System.out.print(i1);
            }else {
                switch (i1){
                    case 10: System.out.print("A"); break;
                    case 11: System.out.print("B"); break;
                    case 12: System.out.print("C"); break;
                    case 13: System.out.print("D"); break;
                    case 14: System.out.print("E"); break;
                    case 15: System.out.print("F"); break;
                }
            }
        }

        System.out.println();

    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        BigInteger n = new BigInteger(in.next());
        if (n.equals(BigInteger.ZERO)){
            System.out.println("00");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (!n.equals(BigInteger.ZERO)){
            int b = n.and(BigInteger.valueOf(0x7f)).intValue();
            n = n.shiftRight(7);
            if (!n.equals(BigInteger.ZERO)){
                b = b | 0x80;
            }
            sb.append(String.format("%02X",b));
        }
        System.out.println(sb);

    }



}
