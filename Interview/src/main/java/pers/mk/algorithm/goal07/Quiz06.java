package pers.mk.algorithm.goal07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz06 {
    public static void main(String[] args) {
        // 思路错误！！只能做到局部最优！！
//        mk();

        // 线性 DP（最长递增 + 最长递减）
//        m1();

        // 基础练习
        // 300. 最长递增子序列
        int[] nums = {10,9,2,5,3,7,101,18};
        base1(nums);
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0 ; i < n ; i++){
            arr[i] = in.nextInt();
        }

        int min = Integer.MAX_VALUE;
        for (int idx = 1 ; idx < n - 1 ; idx++){
            int maxHeight = arr[idx];
            // 从中 到左
            int leftCount = 0;
            int cur = arr[idx];
            for (int left = idx ; left > 0 ; left--){
                int nextLeft = arr[left - 1];
                if (nextLeft < cur){
                    cur = nextLeft;
                }else {
                    leftCount++;
                    cur = arr[left];
                }
            }
            if (leftCount == idx ){
                continue;
            }

            // 从 中 到右
            int rightCount = 0;
            cur = arr[idx];
            for (int right = idx ; right < n - 1 ; right++){
                int nextRight = arr[right + 1];
                if (nextRight < cur){
                    cur = nextRight;
                }else {
                    rightCount++;
                    if (rightCount == n - idx - 1){
                        break;
                    }
                    cur = arr[right];
                }
            }
            if (rightCount == n - idx - 1){
                continue;
            }
            min = Math.min(min,leftCount + rightCount);
        }

        System.out.println(min);
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
        }

        int[] L = new int[n]; // L[i]: 以i结尾的最长递增
        int[] R = new int[n]; // R[i]: 以i开始的最长递减

        // 正向求 L[i]
        for (int i = 0 ; i < n ; i++){
            L[i] = 1;
            for (int j = 0 ; j < i ; j++){
                if (h[j] < h[i]){
                    L[i] = Math.max(L[i],L[j] + 1);
                }
            }
        }

        // 反向求 R[i]
        for (int i = n - 1 ; i >= 0 ; i--){
            R[i] = 1;
            for (int j = n - 1 ; j >= 0 ; j--){
                if (h[i] > h[j]){
                    R[i] = Math.max(R[i],R[j] + 1);
                }
            }
        }

        int maxLen = 0 ;
        for (int i = 0 ; i < n - 1 ; i++){
            maxLen = Math.max(maxLen,L[i] + R[i] - 1);
        }

        System.out.println(n - maxLen);
    }

    private static void base1(int[] nums){
        int n = nums.length;

        int[] dp = new int[nums.length];

        // lists[i] 存储以 nums[i] 结尾的最长递增子序列的具体元素
        List<List<Integer>> lists = new ArrayList<>();

        int maxLen = 0;
        int maxIdx = 0;
        for (int i = 0 ; i < n ; i++) {
            dp[i] = 1;
            List<Integer> curList = new ArrayList<>();
            curList.add(nums[i]);

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    curList = new ArrayList<>(lists.get(j));
                    curList.add(nums[i]);
                }
            }
            lists.add(curList);

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }

        }
        System.out.println(maxLen);
        System.out.println(lists.get(maxIdx));
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("i=%d, nums[%d]=%d, dp[%d]=%d, 子序列: %s%n",
                    i, i, nums[i], i, dp[i], lists.get(i));
        }
    }

}
