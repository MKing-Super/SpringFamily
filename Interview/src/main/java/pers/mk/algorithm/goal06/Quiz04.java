package pers.mk.algorithm.goal06;

import com.alibaba.fastjson.JSON;

import java.util.*;

// 网格红绿灯最短路径
public class Quiz04 {
    public static void main(String[] args) {
        // dfs
        // 代码在这个特定示例上能输出正确答案，是因为运气好（迷宫小、障碍物多）。但这不是一个可靠的解法，在实际的100×100网格上会失败。
//        mk();

        // bfs
        m1();
    }


    static int width = 0;
    static int height = 0;
    static int minTime = Integer.MAX_VALUE;

    private static void mk(){
        int[][] grids = {
                {0, 1, 0},
                {0, 2, 1},
                {0, 0, 0}
        };
        int[][] lights = {
                {1, 1, 3}
        };

        width = grids[0].length;
        height = grids.length;

        mkDfs(grids,lights,0,0,0);

        if (minTime == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(minTime);
        }
    }

    private static void mkDfs(int[][] grids,int[][] lights,int curHeight,int curWidth,int time){
//        System.out.println(curHeight + " " + curWidth + " " + time);
        if (curHeight < 0 || curWidth < 0 || curHeight >= height || curWidth >= width){
            return;
        }
        if (curHeight == height - 1 && curWidth == width -1){
            minTime = Math.min(minTime,time);
            return;
        }
        if (time > minTime){
            return;
        }
        int add = 0;
        if (grids[curHeight][curWidth] == 1){
            return;
        }else if (grids[curHeight][curWidth] == 0){
            add = 1;
        }else if (grids[curHeight][curWidth] == 2){
            for (int i = 0 ; i < lights.length ; i++){
                if (curHeight == lights[i][0] && curWidth == lights[i][1]){
                    add = lights[i][2];
                    break;
                }
            }
        }
        // 向 右
        mkDfs(grids,lights,curHeight,curWidth + 1,time + add);
        // 向 下
        mkDfs(grids,lights,curHeight + 1,curWidth ,time + add);
        // 向 左
        mkDfs(grids,lights,curHeight,curWidth - 1,time + add);
        // 向 上
        mkDfs(grids,lights,curHeight - 1,curWidth ,time + add);

    }



    // [[0,1,0],[0,2,1],[0,0,0]]
    // [[1,1,3]]
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    private static void m1(){
        Scanner in = new Scanner(System.in);
        String gridLine = in.nextLine().trim();
        gridLine = gridLine.substring(2,gridLine.length() - 2);
        String[] rows = gridLine.split("\\],\\[");
        int m = rows.length;
        int n = rows[0].split(",").length;
        int[][] grids = new int[m][n];
        for (int i = 0 ; i < m ; i++){
            String[] cols = rows[i].split(",");
            for (int j = 0 ; j < n ; j++){
                grids[i][j] = Integer.parseInt(cols[j]);
            }
        }

        String lightLine = in.nextLine().trim();
        lightLine = lightLine.substring(2,lightLine.length() - 2);
        String[] lightRows = lightLine.split("\\],\\[");

        HashMap<String, Integer> lightMap = new HashMap<>();
        if (!lightLine.isEmpty()){
            for (String lightRow : lightRows){
                String[] parts = lightRow.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                int wait = Integer.parseInt(parts[2]);
                lightMap.put(x + "," + y,wait);
            }
        }

        int[][] dist = new int[m][n];
        for (int[] row : dist){
            Arrays.fill(row,Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.offer(new int[]{0,0,0});
        while (!pq.isEmpty()){
            System.out.println(JSON.toJSONString(pq));
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];

            if (time > dist[x][y]){
                continue;
            }
            if (x == m - 1 && y == n - 1){
                System.out.println(time);
                for (int[] row : dist){
                    System.out.println(JSON.toJSONString(row));
                }
                return;
            }

            for (int d = 0 ; d < 4 ; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n){
                    continue;
                }
                if (grids[nx][ny] == 1){
                    continue;
                }

                int nextTime = time + 1;
                String key = nx + "," + ny;
                if (lightMap.containsKey(key)){
                    nextTime += lightMap.get(key);
                }

                if (nextTime < dist[nx][ny]){
                    dist[nx][ny] = nextTime;
                    pq.offer(new int[]{nx,ny,nextTime});
                }
            }
        }



        System.out.println(-1);

    }
}
