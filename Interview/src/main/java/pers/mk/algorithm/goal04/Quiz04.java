package pers.mk.algorithm.goal04;

import java.util.*;

public class Quiz04 {
    public static void main(String[] args) {
        // 思路错误！
//        mk();

        // 正确思路：二分法
        // 自己模拟排序，然后把模拟排序的天数 与 指点天数（T = mid）相比，
        // 若 模拟排序的天数 <= 指点天数，则说明给的 T 可用，让后再进行 二分
        // 直至找出合适的 mid = T
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] timeArr = in.nextLine().trim().split(",");
        int m = Integer.parseInt(in.nextLine().trim());

        if (timeArr.length <= m){
            System.out.println(0);
            return;
        }

        boolean[] maxNumArr = new boolean[timeArr.length];
        Arrays.fill(maxNumArr,false);
        List<Integer> timeList = new ArrayList<>();
        for (String s : timeArr){
            timeList.add(Integer.parseInt(s));
        }


        int maxNum = Integer.MIN_VALUE;
        int[] idx = new int[m];
        for (int i = 0 ; i < m ; i++){
            idx[i] = i;
        }

        while (true){
            for (int i = 0 ; i < idx.length ; i++){

            }

        }

    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] nums = in.nextLine().trim().split(",");
        int[] time = new int[nums.length];
        for (int i = 0 ; i < nums.length ; i++){
            time[i] = Integer.parseInt(nums[i].trim());
        }

        int m = in.nextInt();

        System.out.println(minTime(time,m));
    }

    private static int minTime(int[] time, int m){
        int n = time.length;

        // 如果天数大于等于题目数，每天看答案即可
        if (m > n){
            return 0;
        }

        // 二分查找范围：0 到 所有题目时间和
        int left = 0 ;
        int right = 0;
        for (int t : time){
            right += t;
        }

        // 列出每天可能的刷题耗时，让后用 二分法 去尝试
        // 关键：假设选中的 mid 为最佳的 T ，看下符不符合条件
        // 如果 mid 过小 则完成的天数会 大于 给定天数 m，那这时候 mid 要调大
        while (left < right){
            int mid = left + (right - left) / 2;
            if (canFinish(time,m,mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    // 判断在每天最大耗时T的情况下，能否在m天内完成
    // m -> 最大练习天数
    // T -> 假设每天的最大耗时
    private static boolean canFinish(int[] time, int m , int T){
        int days = 1; // 当前使用的天数
        int currentSum = 0; // 当天累计耗时
        int maxInDay = 0; // 当天最大的题目耗时（用于看答案）
        boolean usedAnswer = false; // 当天是否已经看过答案

        for (int curT : time){
            // 先尝试加入当前题目
            int newSum = currentSum + curT;
            int newMax = Math.max(maxInDay,curT);

            // 如果不看答案也不超T
            if (newSum <= T){
                currentSum = newSum;
                maxInDay = newMax;
                continue;
            }

            // 如果超T了，尝试看答案
            if (!usedAnswer){
                // 看答案后是否能满足T
                int afterSkip = newSum - newMax;
                if (afterSkip <= T){
                    currentSum = afterSkip;
                    maxInDay = 0;
                    usedAnswer = true;
                    continue;
                }
            }

            // 看答案也不行或者已经用过答案，需要新开一天
            days++;
            if (days > m){
                return false;
            }

            // 新的一天，重新开始
            currentSum = curT;
            maxInDay = curT;
            usedAnswer = false;

        }

        return days <= m;
    }


}
