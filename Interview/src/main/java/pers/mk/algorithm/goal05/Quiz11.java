package pers.mk.algorithm.goal05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Quiz11 {
    public static void main(String[] args) {
        // 代码逻辑有误，无法正确处理所有情况
        // 如：dvdf
//        mk();


        // right=0, c='d': map={d:0}, maxLen=1
        // right=1, c='v': map={d:0, v:1}, maxLen=2
        // right=2, c='d': map包含'd'且位置0≥left=0, left=1, map={d:2, v:1}, maxLen=max(2, 2-1+1)=2
        // right=3, c='f': map={d:2, v:1, f:3}, maxLen=max(2, 3-1+1)=3
        // 输出：3
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();

        int left = 0;
        int maxLen = Integer.MIN_VALUE;
        HashSet<Character> set = new HashSet<>();

        for (int right = 0 ; right < str.length() ; right++){
            while (left <= right){
                char c = str.charAt(right);
                if (set.contains(c)){
                    set.clear();
                    maxLen = Math.max(maxLen,right - left);
                    left = right;
                }else {
                    set.add(c);
                    break;
                }
            }
        }
        System.out.println(maxLen == Integer.MIN_VALUE ? str.length() : maxLen);
    }


    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();

        int left = 0;
        int maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();// 记录字符最后出现的位置

        for (int right = 0 ; right < str.length() ; right++){
            char c = str.charAt(right);

            // 如果字符出现过且在窗口内，移动左边界
            if (map.containsKey(c) && map.get(c) >= left){
                left = map.get(c) + 1;
            }

            // 更新字符的最新位置
            map.put(c,right);

            // 更新最大长度
            maxLen = Math.max(maxLen,right - left + 1);
        }
        System.out.println(maxLen);

    }

}
