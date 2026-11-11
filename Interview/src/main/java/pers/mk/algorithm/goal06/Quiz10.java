package pers.mk.algorithm.goal06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Quiz10 {
    public static void main(String[] args) {
//        char[][] grid = {
//                {'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}
//        };
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        // dfs
//        mk(grid);


        // bfs
        m1(grid);

    }

    static int height;
    static int width;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    private static void mk(char[][] grid){
        height = grid.length;
        width = grid[0].length;
        int num = 0;
        boolean[][] isLand = new boolean[height][width];
        for (int i = 0 ; i < height ; i++){
            for (int j = 0 ; j < width ; j++){
                if (!isLand[i][j] && grid[i][j] == '1'){
                    isLand[i][j] = true;
                    dfsMk(i,j,isLand,grid);
                    num++;
                }
            }
        }
        System.out.println(num);
    }

    private static void dfsMk(int curX,int curY,boolean[][] isLand,char[][] grid ){
        for (int i = 0 ; i < 4 ; i++){
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= height || nextY >= width){
                continue;
            }
            if (grid[nextX][nextY] == '0'){
                continue;
            }
            if (isLand[nextX][nextY]){
                continue;
            }
            isLand[nextX][nextY] = true;
            dfsMk(nextX,nextY,isLand,grid);
        }
    }



    private static void m1(char[][] grid){
        height = grid.length;
        width = grid[0].length;
        int num = 0;
        boolean[][] isLand = new boolean[height][width];

        for (int i = 0 ; i < height ; i++){
            for (int j = 0 ; j < width ; j++){
                if (!isLand[i][j] && grid[i][j] == '1'){
                    isLand[i][j] = true;

                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i,j});
                    while (!queue.isEmpty()){
                        int[] cur = queue.poll();
                        int curX = cur[0];
                        int curY = cur[1];
                        for (int d = 0 ; d < 4 ; d++){
                            int nextX = curX + dx[d];
                            int nextY = curY + dy[d];
                            if (nextX < 0 || nextY < 0 || nextX >= height || nextY >= width){
                                continue;
                            }
                            if (isLand[nextX][nextY]){
                                continue;
                            }
                            if (grid[nextX][nextY] == '0'){
                                continue;
                            }
                            isLand[nextX][nextY] = true;
                            queue.offer(new int[]{nextX,nextY});
                        }
                    }

                    num++;
                }
            }
        }

        System.out.println(num);
    }


}
