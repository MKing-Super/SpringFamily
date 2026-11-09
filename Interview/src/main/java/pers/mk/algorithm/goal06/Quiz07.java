package pers.mk.algorithm.goal06;

import java.util.*;

public class Quiz07 {
    public static void main(String[] args) {
        // 思路、代码 都正确
//        mk();

        // 优化版
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine().trim());
        String[] split = in.nextLine().trim().split(",");
        HashSet<Integer> illedSet = new HashSet<>();
        for (int i = 0 ; i < split.length ; i++){
            illedSet.add(Integer.parseInt(split[i]));
        }
        int[][] arr = new int[N][N];
        for (int i = 0 ; i < N ; i++){
            String[] split1 = in.nextLine().trim().split(",");
            for (int j = 0 ; j < N ; j++){
                arr[i][j] = Integer.parseInt(split1[j]);
            }
        }
        boolean[] touchedArr = new boolean[N];

        for (Integer illed : illedSet ){
            touchedArr[illed] = true;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{illed,illed});

            while (!queue.isEmpty()){

                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];

                for (int py = 0 ; py < N ; py++){
                    if (x != py && arr[x][py] == 1 && !touchedArr[py] ){
                        touchedArr[py] = true;
                        queue.offer(new int[]{py,py});
                    }
                }

            }
        }

        int total = 0;
        for (int i = 0 ; i < N ; i++){
            if (!illedSet.contains(i) && touchedArr[i]){
                total++;
            }
        }
        System.out.println(total);
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine().trim());

        String[] split = in.nextLine().trim().split(",");
        Set<Integer> illedSet = new HashSet<>();
        for (String s : split){
            illedSet.add(Integer.parseInt(s));
        }

        int[][] contact = new int[N][N];
        for (int i = 0 ; i < N ; i++){
            String[] row = in.nextLine().trim().split(",");
            for (int j = 0 ; j < N ; j++){
                contact[i][j] = Integer.parseInt(row[j]);
            }
        }

        boolean[] needTest = new boolean[N];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int illed : illedSet){
            needTest[illed] = true;
            queue.offer(illed);
        }

        while (!queue.isEmpty()){
            int cur = queue.poll();

            for (int other = 0 ; other < N ;other++){
                if (contact[cur][other] == 1 && !needTest[other]){
                    needTest[other] = true;
                    queue.offer(other);
                }
            }
        }

        int total = 0 ;
        for (int i = 0 ; i < N ; i++){
            if (needTest[i] && !illedSet.contains(i)){
                total++;
            }
        }
        System.out.println(total);

    }

}
