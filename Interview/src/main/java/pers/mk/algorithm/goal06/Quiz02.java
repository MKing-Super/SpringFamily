package pers.mk.algorithm.goal06;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        // 思路复杂！！！
//        mk();

        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[][] nArr = new int[n][n];
        for (int i = 0 ; i < n ; i++){
            String[] split = in.nextLine().split(" ");
            for (int j = 0 ; j < n ; j++){
                nArr[i][j] = Integer.parseInt(split[j]);
            }
        }
        int k = Integer.parseInt(in.nextLine());
        int kI = k - 1;
        int kJ = k - 1;

        System.out.println(dfsMk(nArr,n,kI,kJ,kJ) );
    }

    private static int dfsMk(int[][] nArr,int n ,int kI,int kJ,int middle){
        System.out.println(kI + " "+ kJ + " - " + nArr[kI][kJ]);

        if (kJ == 0 && kI == 0 ){
            return nArr[kI][kJ];
        }

        int time = 0;
        time += nArr[kI][kJ];

        for (int left = 0 ; left < n ; left++){
            if (left == kJ){
                continue;
            }
            if (nArr[kI][left] != 0 && left < kJ){
                time += dfsMk(nArr,n,left,left,kJ);
            }else if (nArr[kI][left] != 0 && left > kJ && left > middle){
                time += dfsMk(nArr,n,left,left,kJ);
            }
        }

        return time;
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[][] usedTime = new int[n][n];
        for (int i = 0 ; i < n ; i++){
            String[] split = in.nextLine().split(" ");
            for (int j = 0 ; j < n ; j++){
                usedTime[i][j] = Integer.parseInt(split[j]);
            }
        }
        int k = Integer.parseInt(in.nextLine()) - 1;

        int[] dealtArr = new int[n];
        Arrays.fill(dealtArr,-1);

        System.out.println(dfs(usedTime,n,dealtArr,k));
        System.out.println(JSON.toJSONString(dealtArr));
    }

    private static int dfs(int[][] usedTime,int n,int[] dealtArr,int service){
        if (dealtArr[service] != -1){
            return dealtArr[service];
        }
        int maxDependencyTime = 0;
        for (int j = 0 ; j < n ; j++){
            if (j != service && usedTime[service][j] == 1){
                int dependencyTime = dfs(usedTime, n, dealtArr, j);
                maxDependencyTime = Math.max(maxDependencyTime,dependencyTime);
            }
        }
        int totalTime = usedTime[service][service] + maxDependencyTime;
        dealtArr[service] = totalTime;
        return totalTime;
    }


}
