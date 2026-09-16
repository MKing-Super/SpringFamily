package pers.mk.interview.hw;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Scanner;

public class HJ024 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for(int i = 0 ; i < n ; i++){
            nums[i] = in.nextInt();
        }
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1,1);
        Arrays.fill(dp2,1);
        for(int i = 1 ; i < n ; i++){
            for(int j = 0 ; j < i ; j++){
                if(nums[i] > nums[j]){
                    dp1[i] = Math.max(dp1[i],dp1[j] + 1);
                }
            }
        }
        System.out.println("dp1 -> " + JSON.toJSONString(dp1));
        for(int i = n - 2 ; i >= 0; i--){
            for(int j = n - 1 ; j > i ; j--){
                if(nums[i] > nums[j]){
                    dp2[i] = Math.max(dp2[i],dp2[j] + 1);
                }
            }
        }
        System.out.println("dp2 -> " + JSON.toJSONString(dp2));
        int max = 0;
        for(int i = 0 ; i < n ; i++){
            max = Math.max(dp1[i] + dp2[i] - 1,max);
        }
        System.out.println(n - max);
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
