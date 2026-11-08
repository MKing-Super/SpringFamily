package pers.mk.algorithm.goal06;

import java.util.*;

public class Quiz06 {
    public static void main(String[] args) {
        // 思路正确，代码判断不对
//        mk();

        // 修正版
        m1();
    }

    static int[] addX = {1,-1,0,0};
    static int[] addY = {0,0,1,-1};
    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] param0 = in.nextLine().trim().split(" ");
        String[] param1 = in.nextLine().trim().split(" ");
        int m = Integer.parseInt(param0[0]) ;
        int n = Integer.parseInt(param0[1]) ;
        int row = Integer.parseInt(param1[0]) ;
        int col = Integer.parseInt(param1[1]) ;

        int[][] park = new int[row][col];
        for (int i = 0 ; i < row ; i++){
            String[] temp = in.nextLine().trim().split(" ");
            for (int j = 0 ; j < col ; j++){
                park[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int[][] dist = new int[row][col];
        for (int i = 0 ; i < row ; i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        queue.offer(new int[]{0,0,0});
        while (!queue.isEmpty()){
            int[] ints = queue.poll();
            int curX = ints[0];
            int curY = ints[1];
            int steps = ints[2];

            if (curX < 0 || curY < 0 || curX >= row || curY >= col){
                continue;
            }
            if (park[curX][curY] == 1){
                continue;
            }
            dist[curX][curY] = steps;
            if (curX == m && curY == n){
                System.out.println(steps);
                return;
            }

            for (int i = 0 ; i < 4 ; i++){
                int nextX = curX + addX[i];
                int nextY = curY + addY[i];
                // 加上就对了，但过度设计了
//                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col){
//                    continue;
//                }
//                if (dist[nextX][nextY] != Integer.MAX_VALUE) continue;
                queue.offer(new int[]{nextX,nextY,steps + 1});
            }

        }

        System.out.println(-1);

    }


    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] param0 = in.nextLine().trim().split(" ");
        String[] param1 = in.nextLine().trim().split(" ");
        int m = Integer.parseInt(param0[0]) ;
        int n = Integer.parseInt(param0[1]) ;
        int row = Integer.parseInt(param1[0]) ;
        int col = Integer.parseInt(param1[1]) ;

        int[][] park = new int[row][col];
        for (int i = 0 ; i < row ; i++){
            String[] temp = in.nextLine().trim().split(" ");
            for (int j = 0 ; j < col ; j++){
                park[i][j] = Integer.parseInt(temp[j]);
            }
        }

        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,0});
        visited[0][0] = true;

        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int steps = cur[2];

            if (curX == m && curY == n){
                System.out.println(steps);
                return;
            }

            for (int i = 0 ; i < 4 ; i++){
                int nextX = curX + addX[i];
                int nextY = curY + addY[i];

                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col){
                    continue;
                }
                if (park[nextX][nextY] == 1){
                    continue;
                }
                if (visited[nextX][nextY] ){
                    continue;
                }

                visited[nextX][nextY] = true;
                queue.offer(new int[]{nextX,nextY,steps + 1});
            }

        }


        System.out.println(-1);
    }

}
