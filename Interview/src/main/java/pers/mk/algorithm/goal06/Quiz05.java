package pers.mk.algorithm.goal06;

import java.util.*;

public class Quiz05 {
    public static void main(String[] args) {
        // 思路、代码 都正确
//        mk();

        // 优化版
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0 ; i < n ; i++){
            String[] split = in.nextLine().trim().split(" ");
            if (split.length == 1 && "*".equals(split[0])){
                list.add(new int[0]);
                continue;
            }
            int[] ints = new int[split.length];
            for (int j = 0 ; j < split.length ; j++){
                ints[j] = Integer.parseInt(split[j]);
            }
            list.add(ints);
        }

        int[] influenceScores = new int[n];

        for (int id = 0 ; id < n ; id++){
            if (list.get(id).length == 0){
                influenceScores[id] = 0;
                continue;
            }

            HashSet<Integer> set = new HashSet<>();
            int[] ints = list.get(id);
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0 ; j < ints.length ; j++){
                queue.offer(ints[j]);
            }
            while (!queue.isEmpty()){
                Integer poll = queue.poll();
                if (set.contains(poll)){
                    continue;
                }
                if (poll == id){
                    continue;
                }
                set.add(poll);
                int[] nextArr = list.get(poll);
                if (nextArr.length > 0){
                    for (int j = 0 ; j < nextArr.length ; j++){
                        queue.offer(nextArr[j]);
                    }
                }
            }
            influenceScores[id] = set.size();
        }

        for (int i = 0 ; i < influenceScores.length ; i++){
            if (i > 0) System.out.print(" ");
            System.out.print(influenceScores[i]);
        }
        System.out.println();
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());

        // 直接用List<List<Integer>>代替ArrayList<int[]>
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] split = in.nextLine().trim().split(" ");
            List<Integer> adj = new ArrayList<>();
            if (!(split.length == 1 && "*".equals(split[0]))) {
                for (String s : split) {
                    adj.add(Integer.parseInt(s));
                }
            }
            graph.add(adj);
        }

        int[] influenceScores = new int[n];

        for (int id = 0; id < n; id++) {
            if (graph.get(id).isEmpty()) {
                influenceScores[id] = 0;
                continue;
            }

            boolean[] visited = new boolean[n];  // 用boolean数组替代HashSet，效率更高
            Queue<Integer> queue = new LinkedList<>();

            for (int neighbor : graph.get(id)) {
                queue.offer(neighbor);
            }

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (visited[curr] || curr == id) continue;
                visited[curr] = true;

                for (int neighbor : graph.get(curr)) {
                    if (!visited[neighbor]) {
                        queue.offer(neighbor);
                    }
                }
            }

            // 统计访问过的员工数（排除自身）
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) count++;
            }
            influenceScores[id] = count;
        }

        // 输出
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(influenceScores[i]);
        }
        System.out.println();
    }

}
