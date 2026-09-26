package pers.mk.algorithm.goal01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
公元2919年，人类终于发现了一颗宜居星球——X星。
现想在X星一片连绵起伏的山脉间建一个天热蓄水库，如何选取水库边界，使蓄水量最大？

要求：
        山脉用正整数数组s表示，每个元素代表山脉的高度。
        选取山脉上两个点作为蓄水库的边界，则边界内的区域可以蓄水，蓄水量需排除山脉占用的空间
        蓄水量的高度为两边界的最小值。
        如果出现多个满足条件的边界，应选取距离最近的一组边界。
输出边界下标（从0开始）和最大蓄水量；如果无法蓄水，则返回0，此时不返回边界。
例如，当山脉为s=[3,1,2]时，则选取s[0]和s[2]作为水库边界，则蓄水量为1，此时输出：0 2:1
当山脉s=[3,2,1]时，不存在合理的边界，此时输出：0。

给定一个长度为 n 的整数数组 height 。数组的元素表示山的高度，选择两个元素作为水库的边界，求蓄水量的最大值并输出蓄水量最大时的边界下标（蓄水量相同时输出下标较近的）。

输入描述：
输入一行数字，空格分隔。

输出描述：
输出蓄水量的最大值及输出蓄水量最大时的边界下标

示例1：

输入：
1 8 6 2 5 4 8 3 7

输出：
1 6:15

说明：蓄水量的最大值为 15
蓄水量最大时的边界下标为1 和 6

 */
public class Quiz01 {

    public static void main(String[] args) {
//        mk();
//        https://blog.csdn.net/qq2279523723/article/details/129027270
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] arr = str.split(" ");
        int[] arrInt = new int[arr.length];
        for (int i  = 0 ; i < arr.length ; i++){
            arrInt[i] = Integer.valueOf(arr[i]);
        }

        int maxSaving = 0;
        int startIndex = 0;
        int endIndex = 2;
        for (int i = 0 ; i < arr.length - 2 ; i++){
            for (int j = i + 2 ; j < arr.length ; j++){
                System.out.println(arrInt[i] + " - " + arrInt[j - 1] + " - " + arrInt[j]);
                if( arrInt[i] > arrInt[j - 1] && arrInt[j] > arrInt[j - 1]){
                    int tempSaving = 0;
                    if (arrInt[j] > arrInt[i]){
                        for(int n = i + 1 ; n < j ; n++){
                            tempSaving = tempSaving + arrInt[i] - arrInt[n];
                        }
                    }else {
                        for(int n = i + 1 ; n < j ; n++){
                            tempSaving = tempSaving + arrInt[j] - arrInt[n];
                        }
                    }
                    if (tempSaving > maxSaving){
                        startIndex = i;
                        endIndex = j;
                    }else if (tempSaving == maxSaving){
                        if((j - i) < (endIndex - startIndex)){
                            startIndex = i;
                            endIndex = j;
                        }else if((j - i) == (endIndex - startIndex)){
                            if(i < startIndex){
                                startIndex = i;
                                endIndex = j;
                            }

                        }
                    }

                } else {
                    break;
                }
            }
        }
        if (maxSaving == 0){
            System.out.println(0);
        }else {
            System.out.println(startIndex + " " + endIndex + ":" + maxSaving);
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] arr = str.split(" ");
        int[] arrInt = new int[arr.length];
        for(int i = 0 ; i < arr.length ; i++){
            arrInt[i] = Integer.parseInt(arr[i]);
        }

        // 索引、最大面积
        int left = 0;
        int right = 0;
        int maxSaving = 0;

        for (int m = 0 , n = arrInt.length - 2 ; m < n ; m++){
            // 升序时跳过
            if (arrInt[m] <= arrInt[m + 1]){
                continue;
            }
            // 右指针
            for (int j = m + 2 ; j < arrInt.length ; j++){
                // 降序时跳过
                if (arrInt[j - 1] > arrInt[j]){
                    continue;
                }
                // 高度
                int minHigh = Math.min(arrInt[m],arrInt[j]);
                int tSaving = 0;
                // 计算蓄水量总和
                for(int t = m + 1 ; t < j ; t++){
                    tSaving = tSaving + minHigh - arrInt[t];
                }
                // 超出已有最大面积时赋值
                if (tSaving > maxSaving || (tSaving == maxSaving &&  j - m < right - left) ){
                    left = m;
                    right = j;
                    maxSaving = tSaving;
                }

            }

        }

        System.out.println(maxSaving == 0? 0 : left + " " + right + ":" + maxSaving);


    }




}
