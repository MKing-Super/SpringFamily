package pers.mk.algorithm.goal07;

import com.alibaba.fastjson.JSON;

import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        // 部分示例计算错误！
//        mk();
        // 用滑窗方式处理
        // 错误：你的代码只在 right == n-1 时才处理左指针的移动，这导致大部分右指针位置都没有进行完整的计数
//        m1();

        // 每次 left 移动时都要计算，不能漏算
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split1 = in.nextLine().trim().split(" ");
        int n = Integer.parseInt(split1[0]);
        int x = Integer.parseInt(split1[1]);
        String[] split2 = in.nextLine().trim().split(" ");
        int[] arr = new int[n];
        int total = 0;
        for (int i = 0 ; i< n ; i++){
            int t = Integer.parseInt(split2[i]);
            arr[i] = t;
            total += t;
        }
        if ( total < x){
            System.out.println(0);
            return;
        }
        if (total == x){
            System.out.println(1);
            return;
        }
        int count = 0;

        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0 ; i < n ; i++){
            for (int j = n -1 ; j >= 0 ;j--){
                if (j >= i){
                    if (total - sumLeft - sumRight >= x){
                        count++;
                        sumRight += arr[j];
                    }else {
                        break;
                    }
                }else {
                    break;
                }
            }
            sumRight = 0;
            sumLeft += arr[i];
        }

        System.out.println(count);
    }




    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int x = Integer.parseInt(str[1]);
        String[] strArr = in.nextLine().split(" ");
        int[] intArr = new int[n];
        for (int i = 0 ; i < n ; i++){
            intArr[i] = Integer.parseInt(strArr[i]);
        }


        int count = 0 ;
        int num = 0;
        int left = 0;
        for (int right = 0 ; right < n ; right++){
            num += intArr[right];
            if (num >= x){
                count++;
                System.out.println(left + " - " + right);
            }
            if (right == n - 1){
                left += 1;
                num -= intArr[left];
                while (left <= right){
                    if (num >= x){
                        count++;
                        left++;
                        System.out.println(left + " - " + right);
                    }else {
                        break;
                    }
                }
            }

        }

        System.out.println(count);
    }




    private static void m2(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int x = Integer.parseInt(str[1]);
        String[] strArr = in.nextLine().split(" ");
        int[] intArr = new int[n];
        for (int i = 0 ; i < n ; i++){
            intArr[i] = Integer.parseInt(strArr[i]);
        }


        int count = 0 ;
        int num = 0;
        int left = 0;

        for (int right = 0 ; right < n ; right++){
            num += intArr[right];
            while (num >= x){
                count += (n - right);
                num -= intArr[left];
                left++;
            }
        }

        System.out.println(count);
    }




}
