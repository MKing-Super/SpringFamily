package pers.mk.algorithm.goal05;

import java.util.*;

public class Quiz08 {
    public static void main(String[] args) {
        // 逻辑混乱，当时结果对了！
//        mk();

        // 思路：滑动窗口 + 列频率统计
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        int[][] table = new int[N][M];
        for (int i = 0 ; i < N ;i++){
            String[] s = in.nextLine().trim().split(" ");
            for (int j = 0 ; j < M ;j++){
                table[i][j] = Integer.parseInt(s[j]);
            }
        }

        int K = Integer.parseInt(in.nextLine().trim());
        String[] strArr = in.nextLine().trim().split(" ");

        Map<Integer, Integer> kFreqMap = new TreeMap<>();
        for (String s : strArr){
            int t = Integer.parseInt(s);
            kFreqMap.putIfAbsent(t,0);
            kFreqMap.put(t,kFreqMap.get(t) + 1);
        }



        List<HashMap<Integer, Integer>> colsList = new ArrayList<>();
        for (int i = 0 ; i < M ; i++){
            HashMap<Integer, Integer> map = new HashMap<>();
            if (i > 0){
                HashMap<Integer, Integer> lastMap = colsList.get(i - 1);
                for (Integer key : lastMap.keySet()){
                    map.putIfAbsent(key,lastMap.get(key));
                }
            }
            for (int j = 0 ; j < N ; j++){
                int t = table[j][i];
                map.putIfAbsent(t,0);
                map.put(t,map.get(t) + 1);
            }

            colsList.add(map);
        }

        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        for (left = 0 ; left < M ; left++){
            if (left > 0){
                int delIndex = left - 1;
                HashMap<Integer, Integer> delMap = colsList.get(delIndex);
                for (int i = left ; i < colsList.size() ; i++){
                    HashMap<Integer, Integer> iMap = colsList.get(i);
                    for (int key : iMap.keySet()){
                        if (delMap.get(key) != null){
                            iMap.put(key,iMap.get(key) - delMap.get(key));
                        }
                    }
                }
            }

            while (left <= right){
                if (right >= M){
                    break;
                }
                HashMap<Integer, Integer> curFreqMap = colsList.get(right);
                boolean pass = true;
                for (Integer key : kFreqMap.keySet()){
                    Integer num = kFreqMap.get(key);
                    if (!curFreqMap.containsKey(key)){
                        pass = false;
                        break;
                    }
                    if (curFreqMap.get(key) < num){
                        pass = false;
                        break;
                    }
                }
                if (!pass){
                    right++;
                }else {
                    min = Math.min(min,right - left + 1);
                    break;
                }
            }
        }
        System.out.println(min);
    }

    private static void m1() {
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        int[][] table = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] s = in.nextLine().trim().split(" ");
            for (int j = 0; j < M; j++) {
                table[i][j] = Integer.parseInt(s[j]);
            }
        }

        int K = Integer.parseInt(in.nextLine().trim());
        String[] strArr = in.nextLine().trim().split(" ");

        // 统计目标数组中每个数字需要的次数
        Map<Integer, Integer> need = new HashMap<>();
        for (String s : strArr) {
            int t = Integer.parseInt(s);
            need.put(t, need.getOrDefault(t, 0) + 1);
        }

        int minWidth = Integer.MAX_VALUE;
        int left = 0;

        // 当前窗口中各列合并后的频率统计
        Map<Integer, Integer> windowFreq = new HashMap<>();
        // 记录还需要多少个数字才能满足条件
        int needCount = K;

        // 滑动窗口
        for (int right = 0; right < M; right++) {
            // 将第right列的所有数字加入窗口
            for (int row = 0; row < N; row++) {
                int val = table[row][right];
                int oldFreq = windowFreq.getOrDefault(val, 0);
                windowFreq.put(val, oldFreq + 1);

                // 如果这个数字还没达到需求，needCount减少
                if (need.containsKey(val) && oldFreq < need.get(val) ) {
                    needCount--;
                }
            }

            // 当窗口满足条件时，尝试收缩左边界
            while (needCount == 0 && left <= right) {
                minWidth = Math.min(minWidth, right - left + 1);

                // 移除第left列的所有数字
                for (int row = 0; row < N; row++) {
                    int val = table[row][left];
                    int oldFreq = windowFreq.get(val);
                    windowFreq.put(val, oldFreq - 1);

                    // 如果移除后这个数字不够了，needCount增加
                    if (need.containsKey(val) && oldFreq == need.get(val)) {
                        needCount++;
                    }
                }
                left++;
            }
        }

        System.out.println(minWidth == Integer.MAX_VALUE ? -1 : minWidth);
    }

}
