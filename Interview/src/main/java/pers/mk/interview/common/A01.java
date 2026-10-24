package pers.mk.interview.common;

import com.alibaba.fastjson.JSON;

import java.util.Scanner;

public class A01 {
    public static void main(String[] args) {
        // 4 2 5 7 1 9 45 7

        // 选择排序
//        m1();

        // 冒泡排序
        m2();

    }

    // 选择排序的特征：每次找到剩余部分的最小值，放到前面。
    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().trim().split(" ");
        int[] arr = new int[str.length];

        for (int i = 0 ; i < arr.length ; i++){
            arr[i] = Integer.parseInt(str[i]);
        }

        for (int i = 0 ; i < arr.length ; i++){
            for (int j = i ; j < arr.length ; j++){
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println(JSON.toJSONString(arr));
    }


    // 冒泡排序的特征：相邻元素两两比较，大的往后"冒泡"。
    private static void m2(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().trim().split(" ");
        int[] arr = new int[str.length];

        for (int i = 0 ; i < arr.length ; i++){
            arr[i] = Integer.parseInt(str[i]);
        }

        for (int i = 0 ; i < arr.length ; i++){
            for (int j = 0 ; j < arr.length - 1 - i ; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println(JSON.toJSONString(arr));
    }

}
