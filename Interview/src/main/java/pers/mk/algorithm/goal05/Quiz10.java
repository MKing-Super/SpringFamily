package pers.mk.algorithm.goal05;

import java.util.Scanner;

public class Quiz10 {
    public static void main(String[] args) {
        // 代码逻辑基本正确，但有一些小问题和优化空间
//        mk();

        // 标准的滑动窗口
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int target = Integer.parseInt(in.nextLine().trim());
        String[] split = in.nextLine().trim().split(",");
        int[] arr = new int[split.length];
        for (int i = 0 ; i < split.length ; i++){
            arr[i] = Integer.parseInt(split[i]);
        }

        int right = 0;
        int minLen = Integer.MAX_VALUE;
        for (int left = 0 ; left < arr.length ; left++){
            if (left == 0 && arr[left] >= target){
                minLen = 1;
                break;
            }
            while (left <= right && right < arr.length){
                int total = 0;
                for (int i = left ; i <= right ; i++){
                    total += arr[i];
                }
                if (total >= target){
                    minLen = Math.min(minLen,right - left + 1);
                    break;
                }else {
                    right++;
                }
            }
        }
        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }


    private static void m1(){
        Scanner in = new Scanner(System.in);
        int target = Integer.parseInt(in.nextLine().trim());
        String[] split = in.nextLine().trim().split(",");
        int[] arr = new int[split.length];
        for (int i = 0 ; i < split.length ; i++){
            arr[i] = Integer.parseInt(split[i]);
        }

        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0 ; right < arr.length ; right++){
            sum += arr[right];  // 扩展右边界

            // 当窗口满足条件时，尝试收缩左边界
            while (sum >= target){
                minLen = Math.min(minLen,right - left + 1);
                sum -= arr[left];   // 移除左边界元素
                left++;     // 左边界右移
            }
        }
        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }

}
