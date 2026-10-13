package pers.mk.algorithm.goal04;

import java.util.Scanner;

public class Quiz06 {
    public static void main(String[] args) {
        // dfs 逻辑错误！！！
//        mk();


        // 三重循环搞定
        m1();
    }

    static int[] levelArr;
    static int levelLength;
    static int count = 0;
    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        String[] split = in.nextLine().trim().split(" ");

        levelArr = new int[split.length];
        levelLength = split.length;
        for (int i = 0 ; i < split.length ; i++){
            levelArr[i] = Integer.parseInt(split[i]);
        }
        dfsMk(0,0,0);
        System.out.println(count);

    }

    private static void dfsMk(int idx,int lastLevel,int layCount){
        System.out.println(idx + " " + lastLevel + " " + layCount);
        if (idx >= levelLength){
            return;
        }
        if (lastLevel != 0 && levelArr[idx] <= lastLevel){
            return;
        }
        if (layCount == 3){
            count++;
            System.out.println("-----------------------");
            return;
        }

        dfsMk(idx + 1,levelArr[idx],layCount + 1);
        dfsMk(idx,levelArr[idx],layCount );

    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        String[] split = in.nextLine().trim().split(" ");

        int[] levels = new int[n];
        for (int i = 0; i < n; i++) {
            levels[i] = Integer.parseInt(split[i]);
        }

        count = 0;

        // 枚举所有三元组 (i, j, k)，其中 i < j < k
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    // 检查是否严格递增或严格递减
                    if ((levels[i] < levels[j] && levels[j] < levels[k]) ||
                            (levels[i] > levels[j] && levels[j] > levels[k])) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

}
