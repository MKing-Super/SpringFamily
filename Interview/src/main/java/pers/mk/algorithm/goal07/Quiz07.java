package pers.mk.algorithm.goal07;

import java.util.Scanner;

public class Quiz07 {
    public static void main(String[] args) {
        // 思路正确，当时代码混乱！！！！
//        mk();


        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine().trim();
        int n = s.length();
        int maxLen = 1;

        for (int idx = 0 ; idx < n ; idx++){
            int leftLen = idx;
            int len = 1;
            // 奇数
            for (int i = 1 ; idx - i >= 0 && idx + i < n ; i++){
                if (s.charAt(idx - i) == s.charAt(idx + i)){
                    len += 2;
                }else {
                    break;
                }
            }
            maxLen = Math.max(maxLen,len);

            // 偶数
            len = 0;
            for (int i = 0 ; idx - i >= 0 && idx + 1 + i < n ; i++){
                if (s.charAt(idx - i) == s.charAt(idx + 1 + i)){
                    len += 2;
                }else {
                    break;
                }
            }
            maxLen = Math.max(maxLen,len);
        }
        System.out.println(maxLen);
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine().trim();
        int n = s.length();
        if (n == 0){
            System.out.println(0);
            return;
        }

        int maxLen = 1;

        for (int i = 0 ; i < n ; i++){
            int lenOdd = expand(s,i,i);
            int lenEven = expand(s,i,i+1);
            maxLen = Math.max(maxLen,Math.max(lenOdd,lenEven));
        }
        System.out.println(maxLen);
    }
    // abba
    // 0123
    private static int expand(String s,int left,int right){
        int n = s.length();
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right) ){
            left--;
            right++;
        }
        return right - left - 1;
    }
}
