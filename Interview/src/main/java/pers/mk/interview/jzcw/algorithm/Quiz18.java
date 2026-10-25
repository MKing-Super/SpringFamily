package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Quiz18 {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        // 思路正确~   使用了   深度优先搜索  dfs
//        System.out.println(mk(grid2));



        // 使用    广度有限搜索    bfs
        System.out.println(m1(grid2));
    }

    private static int mk(char[][] grid) {
        int high = grid.length;
        int width = grid[0].length;
        char[][] table = new char[high + 2][width + 2];
        for (int i = 0 ; i < high + 2 ; i++){
            if (i == 0 || i == high + 1){
                Arrays.fill(table[i],'0');
                continue;
            }
            for (int j = 0 ; j < width + 2 ; j++){
                if (j == 0 || j == width + 1){
                    table[i][j] = '0';
                }else {
                    table[i][j] = grid[i - 1][j - 1];
                }
            }
        }

        high = high + 2;
        width = width + 2;
        boolean[][] island = new boolean[high][width];

        int total = 0;
        for (int i = 1 ; i < high - 1 ; i++){
            for (int j = 1 ; j < width - 1 ; j++){
                if (!island[i][j] && table[i][j] == '1'){
                    dfsMk(table,island,high,width,i,j);
                    total++;
                }
            }
        }

        return total;
    }

    private static void dfsMk(char[][] table,boolean[][] island,int high,int width,int curHigh,int curWidth){
        if (curHigh >= high - 1 || curHigh < 1 || curWidth >= width -1 || curWidth < 1){
            return;
        }

        if (island[curHigh][curWidth]){
            return;
        }

        if (table[curHigh][curWidth] == '1'){
            island[curHigh][curWidth] = true;
        }else {
            return;
        }

        // 向右
        dfsMk(table,island,high,width,curHigh,curWidth + 1);
        // 向下
        dfsMk(table,island,high,width,curHigh + 1,curWidth);
        // 向左
        dfsMk(table,island,high,width,curHigh,curWidth - 1);
        // 向上
        dfsMk(table,island,high,width,curHigh - 1,curWidth);
    }



    private static int m1(char[][] grid){
        int high = grid.length;
        int width = grid[0].length;
        boolean[][] island  = new boolean[high][width];

        int total = 0;
        for (int i = 0 ; i < high ; i++){
            for (int j = 0 ; j < width ; j++){
                if (!island[i][j] && grid[i][j] == '1'){


                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i,j});
                    while (!queue.isEmpty()){
                        int[] poll = queue.poll();
                        int curHigh = poll[0];
                        int curWidth = poll[1];
                        if (curHigh >= high || curHigh < 0 || curWidth >= width || curWidth < 0){
                            continue;
                        }
                        if (!island[curHigh][curWidth] && grid[curHigh][curWidth] == '1' ){
                            island[curHigh][curWidth] = true;

                            queue.offer(new int[]{poll[0] , poll[1] + 1});
                            queue.offer(new int[]{poll[0] + 1 , poll[1] });
                            queue.offer(new int[]{poll[0] , poll[1] - 1});
                            queue.offer(new int[]{poll[0] - 1 , poll[1] });
                        }
                    }

                    total++;
                }
            }
        }

        return total;
    }

}
