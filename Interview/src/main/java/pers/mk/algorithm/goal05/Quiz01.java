package pers.mk.algorithm.goal05;

import java.util.Arrays;
import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {
        // 思路完全错误！先找出各种情况再注意判断哪种最长
//        mk();

//        m1();

        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int m = Integer.parseInt(in.nextLine());
        String[] mStrArr = in.nextLine().split(" ");
        int[] mIdxArr = new int[mStrArr.length];
        for (int i = 0; i < mIdxArr.length ; i++){
            mIdxArr[0] = Integer.parseInt(mStrArr[i]) - 1;
        }
        int k = Integer.parseInt(in.nextLine());

        boolean[] nArr = new boolean[n];
        Arrays.fill(nArr,true);
        for (int t : mIdxArr){
            nArr[t] = false;
        }


    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int m = Integer.parseInt(in.nextLine());
        String[] mStrArr = in.nextLine().split(" ");
        int[] mIdxArr = new int[mStrArr.length];
        for (int i = 0; i < mIdxArr.length ; i++){
            mIdxArr[i] = Integer.parseInt(mStrArr[i]) - 1;
        }
        int k = Integer.parseInt(in.nextLine());

        int[] nArr = new int[n];
        Arrays.fill(nArr,1);
        for (int t : mIdxArr){
            nArr[t] = 0;
        }

        int deadCount = 0;
        int leftIndex = 0;
        int maxLen = 0;

        for (int right = 0 ; right < n ; right++){
            if (nArr[right] == 0){
                deadCount++;
            }
            while (deadCount > k){
                if (nArr[leftIndex] == 0){
                    deadCount--;
                }
                leftIndex++;
            }
            maxLen = Math.max(maxLen,right - leftIndex + 1);
        }
        System.out.println(maxLen);
    }

    private static void m2(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int m = Integer.parseInt(in.nextLine());
        String[] mStrArr = in.nextLine().split(" ");
        int[] mIdxArr = new int[mStrArr.length];
        for (int i = 0; i < mIdxArr.length ; i++){
            mIdxArr[i] = Integer.parseInt(mStrArr[i]) - 1;
        }
        int k = Integer.parseInt(in.nextLine());

        // 直接在枯死位置上做滑动窗口
        int[] nArr = new int[n];
        Arrays.fill(nArr,1);
        for (int t : mIdxArr){
            nArr[t] = 0;
        }

        // 直接在枯死位置上做滑动窗口
        int left = 0;
        int maxLen = 0;// 指向枯死位置数组

        for (int right = 0 ; right < m ; right++){
            // 窗口内的枯死数量 = right - left + 1
            // 我们需要补种的数量 = 窗口内枯死数量
            // 实际能补种 K 棵

            // 如果窗口内枯死数量超过 K，移动左指针
            while (right - left + 1 > k){
                left++;
            }

            // 计算当前能形成的连续区间
            // 左边界：deadPos[left] 之前的第一个活树
            // 右边界：deadPos[right] 之后的第一个活树
            int leftBound = (left == 0) ? 0 : mIdxArr[left - 1] + 1;
            int rightBound = (right == m - 1) ? n - 1 : mIdxArr[right + 1] - 1;

            // 区间长度 = 右边界 - 左边界 + 1
            int len = rightBound - leftBound + 1;
            maxLen = Math.max(maxLen,len);
        }

        if (k >= m){
            maxLen = n + 1;
        }

        System.out.println(maxLen);

    }


}
