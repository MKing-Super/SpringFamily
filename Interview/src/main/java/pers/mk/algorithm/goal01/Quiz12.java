package pers.mk.algorithm.goal01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quiz12 {
    public static void main(String[] args) {
        // 常规 双指针交换法
        mk();
        // 使用 Collections.reverse()
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().trim().split(" ");
        int left = 0 ;
        int right = strArr.length - 1;

        while (left < right){
            String temp = strArr[left];
            strArr[left] = strArr[right];
            strArr[right] = temp;
            left++;
            right--;
        }

        for (String t : strArr){
            System.out.print(t + " ");
        }
        System.out.println();
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] words = in.nextLine().trim().split(" ");

        List<String> wordList = Arrays.asList(words);
        Collections.reverse(wordList);  // 直接反转列表

        System.out.println(String.join(" ", wordList));
    }


}
