package pers.mk.algorithm.goal06;

import java.util.*;

public class Quiz09 {
    public static void main(String[] args) {
        // 思路、代码正确！
//        mk();

        // dfs 实现
//        m1();

        // bfs   实现
        m2();
    }

    static int h;
    static int w;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        h = Integer.parseInt(split[0]);
        w = Integer.parseInt(split[1]);
        int[][] grids = new int[h][w];
        for (int i = 0 ; i < h ; i++){
            String[] row = in.nextLine().trim().split(" ");
            for (int j = 0 ; j < w ; j++){
                grids[i][j] = Integer.parseInt(row[j]);
            }
        }

        boolean[][] dist = new boolean[h][w];

        LinkedList<int[]> pathList = new LinkedList<>();
        pathList.push(new int[]{0,0});
        dfsMk(0,0,grids,dist,pathList);
        dist[0][0] = true;

        for (int i = pathList.size() - 1 ; i >= 0 ; i-- ){
            int[] ints = pathList.get(i);
            System.out.println("(" + ints[0] + "," + ints[1] + ")" );
        }
    }

    private static boolean dfsMk(int curX,int curY,int[][] grids,boolean[][] dist,LinkedList<int[]> pathList){
        if (grids[curX][curY] == 1  ){
            return false;
        }
        if (curX == h - 1 && curY == w - 1){
            return true;
        }
        for (int i = 0 ; i < 4 ; i++){
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            if (nextX < 0 || nextY < 0 || nextX >= h || nextY >= w ){
                continue;
            }
            if (dist[nextX][nextY]){
                continue;
            }
            pathList.push(new int[]{nextX , nextY });
            dist[nextX][nextY] = true;
            boolean b = dfsMk(nextX, nextY, grids, dist, pathList);
            if (b){
                return true;
            }else {
                pathList.pop();
            }
        }
        return false;
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        h = Integer.parseInt(split[0]);
        w = Integer.parseInt(split[1]);
        int[][] grids = new int[h][w];
        for (int i = 0 ; i < h ; i++){
            String[] row = in.nextLine().trim().split(" ");
            for (int j = 0 ; j < w ; j++){
                grids[i][j] = Integer.parseInt(row[j]);
            }
        }

        boolean[][] visited = new boolean[h][w];
        visited[0][0] = true;

        LinkedList<int[]> pathList = new LinkedList<>();
        pathList.addLast(new int[]{0,0});

        dfsM1(0,0,grids,visited,pathList);

        for (int[] pos : pathList){
            System.out.println("(" + pos[0] + "," + pos[1] + ")");
        }
    }

    private static boolean dfsM1(int curX,int curY,int[][] grids,boolean[][] visited,LinkedList<int[]> pathList){
        if (curX == h - 1 && curY == w - 1){
            return true;
        }

        for (int i = 0 ; i < 4 ; i++){
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= h || nextY >= w){
                continue;
            }
            if (visited[nextX][nextY]){
                continue;
            }
            if (grids[nextX][nextY] == 1){
                continue;
            }

            visited[nextX][nextY] = true;
            pathList.addLast(new int[]{nextX,nextY});

            boolean b = dfsM1(nextX, nextY, grids, visited, pathList);
            if (b){
                return true;
            }

            pathList.removeLast();
        }

        return false;
    }


    private static void m2(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        h = Integer.parseInt(split[0]);
        w = Integer.parseInt(split[1]);

        int[][] grids = new int[h][w];
        for (int i = 0; i < h; i++) {
            String[] row = in.nextLine().trim().split(" ");
            for (int j = 0; j < w; j++) {
                grids[i][j] = Integer.parseInt(row[j]);
            }
        }

        int[][] prevX = new int[h][w];
        int[][] prevY = new int[h][w];
        boolean[][] visited = new boolean[h][w];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        visited[0][0] = true;

        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];

            if (curX == h -1 && curY == w - 1){
                break;
            }

            for (int i = 0 ; i < 4 ; i++){
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= h || nextY >= w){
                    continue;
                }
                if (visited[nextX][nextY]){
                    continue;
                }
                if (grids[nextX][nextY] == 1){
                    continue;
                }

                visited[nextX][nextY] = true;
                prevX[nextX][nextY] = curX;
                prevY[nextX][nextY] = curY;
                queue.offer(new int[]{nextX,nextY});

            }
        }

        LinkedList<int[]> path = new LinkedList<>();
        int x = h - 1;
        int y = w - 1;
        while (x != 0 || y != 0){
            path.addFirst(new int[]{x,y});
            int px = prevX[x][y];
            int py = prevY[x][y];
            x = px;
            y = py;
        }
        path.addFirst(new int[]{0,0});

        for (int[] pos : path){
            System.out.println("(" + pos[0] + "," + pos[1] + ")");
        }
    }

}
