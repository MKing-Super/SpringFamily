package pers.mk.algorithm.goal09;

import java.lang.reflect.Array;
import java.util.*;

public class Quiz02 {

    public static void main(String[] args) {
        // 思路错误，不能以为使用基本数据去计算，那样会很麻烦，编写一个实体类更方便
//        mk();

        // 编写实体表，统计为list，使用sort排序
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().split(",");
        int M = Integer.parseInt(strArr[0]);
        int N = Integer.parseInt(strArr[1]);
        int[][] intArr = new int[M][N];
        for (int i = 0 ; i < M ; i++){
            String[] split = in.nextLine().split(",");
            for (int j = 0 ; j < N ; j++){
                intArr[i][j] = Integer.parseInt(split[j]);
            }
        }

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        for (int i = 0 ; i < N ; i++){
            list.add(new ArrayList<>());
            countList.add(0);
            for (int j = 0 ; j < M ; j++){
                list.get(i).add(intArr[i][j]);
                countList.set(i,countList.get(i) + intArr[i][j]);
            }
            list.get(i).sort((a,b) -> {
                return b.compareTo(a);
            });
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] params = in.nextLine().split(",");
        if (params.length != 2){
            System.out.println(-1);
            return;
        }

        int M = Integer.parseInt(params[0].trim());
        int N = Integer.parseInt(params[1].trim());

        if (M < 3 || M > 10 || N < 3 || N > 100){
            System.out.println(-1);
            return;
        }

        Player[] players = new Player[N];
        for (int i = 0 ; i < N ; i++){
            players[i] = new Player(i + 1);
        }

        for (int i = 0 ; i < M ; i++){
            String[] scores = in.nextLine().trim().split(",");
            if (scores.length != N){
                System.out.println(-1);
                return;
            }
            for (int j = 0 ; j < N ; j++){
                int score = Integer.parseInt(scores[j].trim());
                if (score < 1 || score > 10){
                    System.out.println(-1);
                    return;
                }
                players[j].addScore(score);
            }
        }

        Arrays.sort(players, (a,b) -> {
            if (a.totalScore != b.totalScore){
                return Integer.compare(b.totalScore,a.totalScore);
            }

            for (int t = 10 ; t >= 1 ; t++){
                if (b.scoreCount[t] != a.scoreCount[t]){
                    return Integer.compare(b.scoreCount[t],a.scoreCount[t]);
                }
            }
            return 0;
        });

        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < 3 ; i++){
            if (i > 0){
                res.append(",");
            }
            res.append(players[i].id);
        }
        System.out.println(res.toString());

    }

    static class Player {
        int id;
        int totalScore;
        int[] scoreCount;

        public Player(int id) {
            this.id = id;
            this.totalScore = 0;
            this.scoreCount = new int[11];
        }

        public void addScore(int score){
            this.totalScore += score;
            this.scoreCount[score]++;
        }
    }


}
