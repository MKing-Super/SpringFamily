package pers.mk.algorithm.goal05;

import java.util.Scanner;

public class Quiz05 {
    public static void main(String[] args) {
        // 逻辑不对！要求 连续时间段最长 且 大于容忍值
//        mk();


        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        String[] split = in.nextLine().trim().split(" ");
        if (split.length != 168){
            return;
        }
        int[] arr = new int[168];
        for (int i = 0 ; i < 168 ; i++){
            arr[i] = Integer.parseInt(split[i]);
        }

        int left = 0;
        int right = 0;
        int minTotalHours = Integer.MAX_VALUE;
        int minLeft = Integer.MAX_VALUE;
        int minRight = Integer.MAX_VALUE;

        for (left = 0 ; left < 168 ; left++){

            if (right >= 168){
                right = 168 - right;
            }
            boolean pass = true;
            while (left <= right && right < 168){
                int total = 0;
                for (int i = left ; i <= right ; i++){
                    total += arr[i];
                }
                if (total > n){
                    if (total < minTotalHours){
                        minLeft = left;
                        minRight = right;
                        minTotalHours = total;
                    }
                    pass = false;
                    break;
                }else {
                    right++;
                }
            }
            if (pass ){
                right++;
            }
        }
        System.out.println(minLeft + " " + minRight);
    }


    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        String[] split = in.nextLine().trim().split(" ");

        if (split.length != 168){
            return;
        }

        int[] arr = new int[168];
        for (int i = 0 ; i < 168 ; i++){
            arr[i] = Integer.parseInt(split[i]);
        }

        int[] doubleArr = new int[336];
        for (int i = 0 ; i < 336 ; i++){
            doubleArr[i] = arr[i % 168];
        }

        int maxLen = -1;
        int resultLeft = -1;
        int resultRight = -1;

        for (int left = 0 ; left < 168 ; left++){
            long sum = 0 ;

            for (int right = left ; right < left + 168 && right < 336 ; right++){
                sum += doubleArr[right];

                if (sum > n){
                    int windowLen = right - left + 1;

                    if (windowLen > maxLen){
                        maxLen = windowLen;
                        resultLeft = left;
                        resultRight = right;
                    }
                    break;
                }
            }
        }

        if (resultLeft == -1){
            System.out.println("-1 -1");
        }else {
            System.out.println(resultLeft +  " " + resultRight);
        }

    }

}
