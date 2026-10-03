package pers.mk.algorithm.goal07;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Quiz01 {
    public static void main(String[] args) {
        // 审题，不是最高失败率，是最长时间段！！！
//        mk();

        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        BigDecimal minAverageLost = new BigDecimal(Integer.parseInt(in.nextLine())) ;
        String[] split = in.nextLine().trim().split(" ");
        int[] arr = new int[split.length];
        for (int i = 0 ; i < split.length ; i++){
            arr[i] = Integer.parseInt(split[i]);
        }

        int left = 0;
        BigDecimal min = new BigDecimal(-1);
        TreeSet<String> minSet = new TreeSet<>();
        for (int right = 0 ; right < arr.length ; right++){

            while (left <= right){
                BigDecimal tAvg = BigDecimal.ZERO;
                for (int i = left ; i <= right ;i++ ){
                    tAvg = tAvg.add(new BigDecimal(arr[i]));
                }
                tAvg = tAvg.divide(new BigDecimal(right - left + 1),2, RoundingMode.UP);

                if (tAvg.compareTo(minAverageLost) <= 0 && tAvg.compareTo(min) > 0){
                    minSet.clear();
                    min = tAvg;
                    minSet.add(left + "-" + right);
                }else if (tAvg.compareTo(min) == 0){
                    minSet.add(left + "-" + right);
                    break;
                } else {
                    left++;
                    break;
                }
            }

        }

        for (String key : minSet ){
            System.out.println(key + " ");
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int minAverageLost = Integer.parseInt(in.nextLine()) ;
        String[] split = in.nextLine().trim().split(" ");
        int[] arr = new int[split.length];
        for (int i = 0 ; i < split.length ; i++){
            arr[i] = Integer.parseInt(split[i]);
        }


        int maxLen = 0;
        TreeSet<String> maxSet = new TreeSet<>();
        for (int i = 0 ; i < arr.length ; i++){
            int sum = 0;
            for (int j = i ; j < arr.length ; j++){
                sum += arr[j];
                int len = j - i + 1;
                double tAvg = sum / len;
                if (tAvg < minAverageLost){
                    if ((j - i +1) > maxLen){
                        maxSet.clear();
                        maxLen = j - i +1;
                        maxSet.add(i + " " + j);
                    }else if (len == maxLen){
                        maxLen = j - i +1;
                        maxSet.add(i + "-" + j);
                    }
                }

            }
        }

        if (maxSet.isEmpty()){
            System.out.println("NULL");
        }else {
            for (String key : maxSet ){
                System.out.println(key + " ");
            }
            System.out.println();
        }


    }



}
