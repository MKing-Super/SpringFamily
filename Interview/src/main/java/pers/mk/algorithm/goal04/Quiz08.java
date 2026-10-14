package pers.mk.algorithm.goal04;

import java.util.Scanner;

public class Quiz08 {
    public static void main(String[] args) {
        // 思路基本正确~
//        mk();

        // 这是一个"摆动排序"（Wiggle Sort）问题，要求最小化移动距离
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        int[] arr = new int[split.length];
        try {
            for (int i = 0 ; i < split.length ; i++){
                arr[i] = Integer.parseInt(split[i]);
            }
        }catch (Exception e){
            System.out.println("[]");
            return;
        }

        for (int i = 0 ; i < arr.length ; i++){
            if (i == 0 ){
                if (arr[i] < arr[i + 1]){
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }else if (i < arr.length - 1 && i % 2 != 0){
                if (arr[i] > arr[i + 1]){
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }else if (i < arr.length - 1 && i % 2 == 0){
                if (arr[i] < arr[i + 1]){
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < arr.length ; i++){
            if (i > 0 ){
                sb.append(" ");
            }
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }


    private static void m1(){
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().trim();

        // 处理非法输入
        if (input.isEmpty()) {
            System.out.println("[]");
            return;
        }

        String[] split = input.split(" ");
        int[] arr = new int[split.length];

        try {
            for (int i = 0; i < split.length; i++) {
                arr[i] = Integer.parseInt(split[i]);
            }
        } catch (Exception e) {
            System.out.println("[]");
            return;
        }

        // 检查是否所有元素都是正整数
        for (int num : arr) {
            if (num <= 0) {
                System.out.println("[]");
                return;
            }
        }

        // 摆动排序：奇数索引位置（高）>= 相邻，偶数索引位置（矮）<= 相邻
        for (int i = 0 ; i < arr.length - 1 ; i++){
            if (i % 2 == 0){
                // 高位（偶数索引），需要 >= 下一位
                if (arr[i] < arr[i + 1]){
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }else {
                // 低位（奇数索引），需要 <= 下一位
                if (arr[i] > arr[i + 1]){
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }

        // 输出结果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(arr[i]);
        }
        System.out.println(sb);

    }

}
