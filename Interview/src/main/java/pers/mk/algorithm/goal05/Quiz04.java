package pers.mk.algorithm.goal05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz04 {
    public static void main(String[] args) {
        // 思路不对只考虑了个例！
//        mk();

        // 利用频率统计：统计输入字符串中0-9每个数字出现的次数
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        String str = split[0];
        int n = Integer.parseInt(split[1]);
        int len = str.length();
        // 个位数
        if (len / n == 0 || (len / n == 1 && len % n == 0) ){
            List<Integer> list = new ArrayList<>();
            for (int i = 0 ; i < str.length() ; i++){
                list.add(Integer.parseInt(String.valueOf(str.charAt(i))));
            }
            list.sort((a,b) -> {return a - -b;});
            System.out.println(list.get(0));
            return;
        }
        // 3 ~ 4位数
        if ( len / n == 3 && len % n > 0 ){
            System.out.println(1000 - n + 1);
            return;
        }

    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] parts = in.nextLine().trim().split(" ");
        String s = parts[0];
        int n = Integer.parseInt(parts[1]);

        int[] targetFreq = new int[10];
        for (char c : s.toCharArray()){
            targetFreq[c - '0']++;
        }

        int totalLen = s.length();
        int avgDigits = totalLen / n;
        int minStart = Math.max(1,(int) Math.pow(10,avgDigits - 1) - n);
        int maxStart = (int)Math.pow(10,avgDigits) - 1;

        for (int start = minStart ; start <= maxStart ; start++){
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i < n ; i++){
                sb.append(start + i);
            }
            if (sb.length() != totalLen) {
                continue;
            }

            int[] curFreq = new int[10];
            for (char c : sb.toString().toCharArray()){
                curFreq[c - '0']++;
            }

            boolean match = true;
            for (int d = 0 ; d < 10 ; d++){
                if (curFreq[d] != targetFreq[d]){
                    match = false;
                    break;
                }
            }

            if (match){
                System.out.println(start);
                return;
            }
        }


    }

}
