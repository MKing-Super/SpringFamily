package pers.mk.algorithm.goal07;

import com.alibaba.fastjson.JSON;

import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        // dfs 数量大会爆炸！！！
//        mk();

        // dp 动态规划
        m1();
    }

    static int like = 0;
    static int x;
    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        x = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);
        int[][] food = new int[n][3];
        for (int i = 0 ; i < n ; i++){
            String[] s = in.nextLine().trim().split(" ");
            // 价格
            food[i][0] = Integer.parseInt(s[0]);
            // 数量
            food[i][1] = Integer.parseInt(s[1]);
            // 喜爱度
            food[i][2] = Integer.parseInt(s[2]);
        }
        dfsMk(0,0,food);

        System.out.println(like);
    }

    private static void dfsMk(int curUsedMoney,int curLike,int[][] food){
        for (int i = 0 ; i < food.length ; i++){
            int nextUsedMoney = curUsedMoney + food[i][0];
            food[i][1]--;
            int nextLike = curLike + food[i][2];
            if (nextUsedMoney > x || food[i][1] < 0){
                food[i][1]++;
                continue;
            }
            like = Math.max(like,nextLike);
            dfsMk(nextUsedMoney,nextLike,food);
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        int x = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);
        int[][] food = new int[n][3];
        for (int i = 0 ; i < n ; i++){
            String[] s = in.nextLine().trim().split(" ");
            // 价格
            food[i][0] = Integer.parseInt(s[0]);
            // 数量
            food[i][1] = Integer.parseInt(s[1]);
            // 喜爱度
            food[i][2] = Integer.parseInt(s[2]);
        }

        int[] dp = new int[x + 1];
        for (int i = 0 ; i < n ;i++){
            int price = food[i][0];
            int count = food[i][1];
            int like = food[i][2];


            for (int money = x ; money >= 0 ; money--){
                // 从 dp[money] 开始，保留了不买当前零食的情况
                int maxLike = dp[money];
                for (int j = 1 ; j <= count ; j++){
                    if (money - j * price >= 0){
                        int tempLike = dp[money - j * price] + j * like;
                        maxLike = Math.max(maxLike,tempLike);
                    }
                }
                dp[money] = maxLike;
            }
        }
        System.out.println(dp[x]);
    }


}
