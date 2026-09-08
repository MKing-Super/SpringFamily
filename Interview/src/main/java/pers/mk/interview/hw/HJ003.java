package pers.mk.interview.hw;

import java.util.Arrays;
import java.util.Scanner;

public class HJ003 {
    public static void main(String[] args) {
//        mk();
        m1();
//        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        // 获取第一行数字
        int firstLine = in.nextInt();
        int[] arr = new int[firstLine];
        // 获取第n行数字
        for(int i = 0 ; i < firstLine ; i++){
            arr[i] = in.nextInt();
        }
        // 排序
        for(int i = 0 ; i < firstLine ; i++){
            for(int j = i ; j < firstLine ; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        // 去重
        int[] set = new int[firstLine];

        set[0] = arr[0];

        int tmp = 0;
        for(int i = 0 ; i < firstLine - 1 ; i++){
            for(int j = tmp ; j < firstLine ; j++){
                if(set[i] == arr[j]){
                    continue;
                }else if(set[i] > 0){
                    set[i + 1] = arr[j];
                    tmp = j;
                    break;
                }
            }
        }
        // 输出展示
        for(int i = 0 ; i < firstLine ; i++){
            if(set[i] > 0){
                System.out.println(set[i]);
            }
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int f = 0;
        Integer[] arr = new Integer[501];
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if (f > 0) {
                arr[a] = a;
            }
            f++;
            if (f==count){
                break;
            }
        }
        for (int i = 1; i < 500; i++) {
            if (arr[i] != null) {
                System.out.println(i);
            }
        }
    }

    private static void m2(){
        Scanner sc = new Scanner(System.in);
        boolean []arr = new boolean[505];
        int num = sc.nextInt();
        while(num -- > 0){
            int input = sc.nextInt();
            arr[input] = true;
        }
        for(int i = 0;i < 500; ++i){
            if(arr[i]) {
                System.out.println(i);
            }
        }
    }

}
