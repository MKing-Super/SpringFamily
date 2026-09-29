package pers.mk.algorithm.goal04;

import pers.mk.interview.algorithm.base.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {
        // 思路正确，当数字下表控制混乱
//        mk();
        // 使用list实现的答案
        m1();
        // 使用数组实现的答案
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        int K = Integer.parseInt(in.nextLine());

        List<int[]> list = new ArrayList<>();
        while (in.hasNextLine()){
            String s1 = in.nextLine();
            if (s1.isEmpty()) break;
            String[] s = s1.split(" ");
            int[] ints = new int[s.length];
            for (int i = 0 ; i < s.length ; i++){
                ints[i] = Integer.parseInt(s[i]);
            }
            list.add(ints);
        }

        int[][] table = new int[K][N];
        int M = list.size();
        int[] mIndexArr = new int[M];

        int kIndex = 0;
        int nIndex = 0;

        int total = N * K;
        int taken = 0;

        while (taken < total){

            for (int i = 0 ; i < M ; i++){
                int[] tongdaoInts = list.get(i);
                int mIndex = mIndexArr[i];
                for (int j = mIndex ; j < tongdaoInts.length  ; j++){

                    if (nIndex < N && kIndex < K){
                        table[kIndex][nIndex] = list.get(i)[j];
                        taken++;
                        nIndex++;
                        mIndexArr[i]++;
                    }else {
                        nIndex = 0;
                        if (kIndex < K){
                            kIndex++;
                        }
                        break;
                    }

                }
                if (mIndex == tongdaoInts.length - 1){
                    break;
                }
            }

        }

        for (int i = 0 ; i < N ; i++){
            for (int j = 0 ; j < K ; j++){
                System.out.print(table[j][i] + " ");
            }
        }
        System.out.println();

    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        int K = Integer.parseInt(in.nextLine());

        List<List<Integer>> lists = new ArrayList<>();
        while (in.hasNextLine()){
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            String[] elements = line.split(" ");
            List<Integer> inList = new ArrayList<>();
            for (String e : elements){
                inList.add(Integer.parseInt(e));
            }
            lists.add(inList);
        }

        int M = lists.size();
        // 每个列表的下标index
        int[] listPointer = new int[M];

        // N 个窗口，每个窗口最多 K 个元素
        List<List<Integer>> windows = new ArrayList<>();
        for (int i = 0 ; i < N ; i++){
            windows.add(new ArrayList<>());
        }
        // 每个窗口当前填了多少个
        int[] windowFiled = new int[N];

        int totalNeeded = N * K;
        int produced = 0;

        while (produced < totalNeeded){
            // M(3)个列表循环
            for (int listIndex1 = 0 ; listIndex1 < M && produced < totalNeeded ; listIndex1++){
                // 当前列表
                List<Integer> curList = lists.get(listIndex1);

                int w = 0;
                // 1个当前列表对 N(4)屛依次填充
                while (w < N && listPointer[listIndex1] < curList.size() && produced < totalNeeded ){
                    while (w < N && windowFiled[w] >= K){
                        w++;
                    }

                    if (w >= N){
                        break;
                    }

                    int val = curList.get(listPointer[listIndex1]);
                    listPointer[listIndex1]++;
                    windows.get(w).add(val);
                    windowFiled[w]++;
                    produced++;
                    w++;
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0 ; i < N; i++){
            for (int val : windows.get(i) ){
                if (!first) {
                    sb.append(" ");
                }
                sb.append(val);
                first = false;
            }
        }
        System.out.println(sb.toString());

    }


    private static void m2(){


    }









}
