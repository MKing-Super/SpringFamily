package pers.mk.algorithm.goal04;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Quiz02 {
    public static void main(String[] args) {
        // 指针过多难以控制
//        mk();
        // 贪心算法 + 数组实现
        m1();

        // 贪心算法 + TreeMap
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String str = in.nextLine();
        String[] split = str.substring(1, str.length() - 1).split(",");
        int[] intArr = new int[split.length];

        for (int i = 0 ; i < split.length ; i++){
            intArr[i] = Integer.parseInt(split[i]);
        }

        boolean[] sitArr = new boolean[n];
        Arrays.fill(sitArr,false);

        int minLen = -1;
        int minIndex = Integer.MAX_VALUE;
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0 ; i < intArr.length ; i++){
            if (intArr[i] < 0){
                int abs = Math.abs(intArr[i]);
                sitArr[abs] = false;
                continue;
            }
            if (i == 0){
                sitArr[0] = true;
                startIndex = 0;
            }else if (i == 1 || !sitArr[sitArr.length - 1]){
                sitArr[sitArr.length - 1] = true;
                endIndex = sitArr.length - 1;
                minIndex = sitArr.length - 1;
            }else {
//                System.out.println(minIndex);
                for (int t = 0 ; t < sitArr.length ; t++){
                    if (sitArr[t] == true && startIndex == -1){
                        startIndex = t;
                    }else if (sitArr[t] == true && endIndex == -1){
                        endIndex = t;
                    }
                    if (startIndex != -1 && endIndex != -1 ){
                        int tLen = (endIndex - startIndex) / 2;
                        int tIndex = tLen + startIndex;
                        if (tLen > minLen){
                            minLen = tLen;
                            minIndex = tIndex;
                        }else if (tLen == minLen){
                            if (tIndex < minIndex){
                                minLen = tLen;
                                minIndex = tIndex;
                            }
                        }
                        startIndex = -1;
                        endIndex = -1;
                        t--;
                    }
                }
                sitArr[minIndex] = true;
                minLen = - 1;
                startIndex = -1;
                endIndex = -1;
            }

        }

        System.out.println(minIndex);

    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String str = in.nextLine();
        String[] split = str.substring(1, str.length() - 1).split(",");
        int[] intArr = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            intArr[i] = Integer.parseInt(split[i]);
        }

        boolean[] sitArr = new boolean[n];
        Arrays.fill(sitArr, false);

        int lastSeat = -1;
        for (int i = 0 ; i < intArr.length ; i++){
            // ========== 离场 ==========
            if (intArr[i] < 0){
                int abs = Math.abs(intArr[i]);
                sitArr[abs] = false;
                continue;
            }

            // ========== 进场 ==========
            int bestSeat = -1;
            int maxDist = -1;

            // ---- 第一个人：强制坐0 ----
            if (!sitArr[0]){
                boolean allEmpty = true;
                for (int j = 1 ; j < n ;j++){
                    if (sitArr[j]){
                        allEmpty = false;
                        break;
                    }
                }
                if (allEmpty){
                    sitArr[0] = true;
                    lastSeat = 0;
                    continue;
                }
            }

            // ---- 情况2：中间区间 ----
            int prev = -1;
            for (int j = 0 ; j < n ; j++){
                if (sitArr[j]){
                    if (prev != -1){
                        int mid = (prev + j) / 2;
                        int dist = (j - prev) /2;
                        if (dist > maxDist || (dist == maxDist && mid < bestSeat)  ){
                            maxDist = dist;
                            bestSeat = mid;
                        }
                    }
                    prev = j;
                }
            }

            // ---- 情况3：最右侧区间 ----
            if (!sitArr[n - 1]){
                int dist = n;
                for (int j = n - 2 ; j >= 0 ; j--){
                    if (sitArr[j]){
                        dist = n - 1 - j;
                        break;
                    }
                }
                if (dist > maxDist || (dist == maxDist && n - 1 < bestSeat)){
                    bestSeat = n - 1;
                }
            }

            // ---- 安排座位 ----
            if (bestSeat == -1){
                lastSeat = -1;
            }else {
                sitArr[bestSeat] = true;
                lastSeat = bestSeat;
            }

        }

        System.out.println(lastSeat);


    }



    private static void m2(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());

        String line = in.nextLine().trim();
        line = line.substring(1, line.length() - 1);
        String[] parts = line.split(",");
        int[] ops = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            ops[i] = Integer.parseInt(parts[i].trim());
        }

        System.out.println(solve(n, ops));
    }

    public static int solve(int n, int[] ops) {
        // TreeSet 维护已坐座位，自动升序排列
        TreeSet<Integer> occupied = new TreeSet<>();
        int lastSeat = -1;

        for (int op : ops) {

            // ========== 离场 ==========
            if (op < 0) {
                int leaveSeat = -op;
                occupied.remove(leaveSeat);
                continue;
            }

            // ========== 进场 ==========
            int bestSeat = -1;
            int maxDist = -1;

            // ---- 第一个人：坐0 ----
            if (occupied.isEmpty()) {
                bestSeat = 0;

            } else {
                // ---- 最左侧区间 [0, first-1] ----
                int first = occupied.first();
                int leftDist = first;  // 坐0号位到第一个人的距离
                if (leftDist > maxDist) {
                    maxDist = leftDist;
                    bestSeat = 0;
                }

                // ---- 中间区间 ----
                // 遍历所有相邻已坐座位对
                Integer prev = null;
                for (int cur : occupied) {
                    if (prev != null) {
                        int mid = (prev + cur) / 2;
                        int dist = (cur - prev) / 2;

                        if (dist > maxDist) {
                            maxDist = dist;
                            bestSeat = mid;
                        } else if (dist == maxDist && mid < bestSeat) {
                            bestSeat = mid;
                        }
                    }
                    prev = cur;
                }

                // ---- 最右侧区间 [last+1, n-1] ----
                int last = occupied.last();
                int rightDist = (n - 1) - last;  // 最后一个座位到最后一人的距离
                if (rightDist > maxDist) {
                    bestSeat = n - 1;
                }
                // 距离相等时不需要比较索引，因为 n-1 是最大索引
            }

            // ---- 安排座位 ----
            if (bestSeat == -1) {
                lastSeat = -1;  // 满员
            } else {
                occupied.add(bestSeat);
                lastSeat = bestSeat;
            }
        }

        return lastSeat;
    }

}
