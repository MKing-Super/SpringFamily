package pers.mk.algorithm.goal08;

import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        // 你的代码逻辑上是正确的，能得出正确答案，但写法容易让人混淆
//        mk();
        // 修正版（经典 DFS 选/不选）
        m1();
    }

    static int[] intArr;
    static int total;
    static int min = Integer.MAX_VALUE;
    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().split(" ");
        intArr = new int[strArr.length];
        total = 0;
        for (int i = 0 ; i < strArr.length ; i++){
            intArr[i] = Integer.parseInt(strArr[i]);
            total += Integer.parseInt(strArr[i]);
        }

        dfsMk(0,0,0);
        System.out.println(min);
    }

    private static void dfsMk(int left,int layCount,int sum){
        if (left >= 10){
            return ;
        }
        if (layCount == 5){
            int t = Math.abs(total - sum - sum);
            min = Math.min(min,t);
            return;
        }

        for (;left < 10 ; left ++){
            dfsMk(left + 1,layCount + 1,sum + intArr[left]);
        }
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().split(" ");
        intArr = new int[strArr.length];
        total = 0;
        for (int i = 0 ; i < strArr.length ; i++){
            intArr[i] = Integer.parseInt(strArr[i]);
            total += Integer.parseInt(strArr[i]);
        }

        dfs(0,0,0);
        System.out.println(min);
    }

    private static void dfs(int left,int layCount,int sum){
        if (left >= 10){
            return;
        }
        if (layCount + (10 - left) < 5){
            return;
        }
        if (layCount == 5){
            int diff = Math.abs(total - sum - sum);
            min = Math.min(min,diff);
            return;
        }

        dfs(left + 1,layCount + 1,sum + intArr[left]);

        dfs(left + 1,layCount,sum);


    }


}
