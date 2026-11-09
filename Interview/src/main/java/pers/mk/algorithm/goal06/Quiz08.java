package pers.mk.algorithm.goal06;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Quiz08 {
    public static void main(String[] args) {
        // 思路错误！！！
//        mk();


        m1();
    }

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    private static void mk(){
        Scanner in = new Scanner(System.in);
        int R = Integer.parseInt(in.nextLine().trim());
        int C = Integer.parseInt(in.nextLine().trim());

        int[][] grids = new int[R][C];
        for (int i = 0 ; i < R ; i++){
            String[] split = in.nextLine().trim().split(" ");
            for (int j = 0 ; j < C ; j++){
                grids[i][j] = Integer.parseInt(split[j]);
            }
        }

        int fx = R -1;
        int fy = C - 1;

        int[][] dist = new int[R][C];
        for (int i = 0 ; i < R ; i++){
            Arrays.fill(dist[i],-1);
        }

        Queue<int[]> queue = new PriorityQueue<>((a,b) -> b[2] - a[2]);
        queue.offer(new int[]{0,0,grids[0][0]});
        int totalSc = 0;
        int sc = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int scores = cur[2];
            dist[curX][curY] = scores;

            if (scores > totalSc){
                totalSc = scores;
                sc = Math.min(sc,grids[curX][curY]);
            }else {
                continue;
            }
            if (curX == fx && curY == fy){
                System.out.println(sc);
                return;
            }

            for (int i = 0 ; i < 4 ; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
                    continue;
                }

                queue.offer(new int[]{nextX,nextY,scores + grids[nextX][nextY]});
            }
        }

        System.out.println(-1);
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        int R = Integer.parseInt(in.nextLine().trim());
        int C = Integer.parseInt(in.nextLine().trim());

        int[][] grids = new int[R][C];
        for (int i = 0 ; i < R ; i++){
            String[] split = in.nextLine().trim().split(" ");
            for (int j = 0 ; j < C ; j++){
                grids[i][j] = Integer.parseInt(split[j]);
            }
        }

        int[][] maxMin = new int[R][C];
        for (int[] row : maxMin){
            Arrays.fill(row,-1);
        }
        maxMin[0][0] = grids[0][0];

        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> b[2] - a[2]);
        queue.offer(new int[]{0,0,grids[0][0]});
        while (!queue.isEmpty()){

            // 演示
//            for (int[] row : maxMin){
//                System.out.println(JSON.toJSONString(row));
//            }
//            System.out.println(JSON.toJSONString(queue));
//            System.out.println("----------------------------");

            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curMin = cur[2];

            if (curMin < maxMin[curX][curY]){
                continue;
            }

            if (curX == R -1 && curY == C - 1){
                System.out.println(curMin);
                return;
            }

            for (int i = 0 ; i < 4 ; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C){
                    continue;
                }

                int newMin = Math.min(curMin, grids[nextX][nextY]);

                if (newMin > maxMin[nextX][nextY]){
                    maxMin[nextX][nextY] = newMin;
                    queue.offer(new int[]{nextX,nextY,newMin});
                }
            }

        }


    }

}
