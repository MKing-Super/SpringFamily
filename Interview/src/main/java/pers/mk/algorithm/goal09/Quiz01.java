package pers.mk.algorithm.goal09;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {
        // 存储数据的类型不对！！！
//        mk();

        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        List<int[]> list3 = new ArrayList<>();
        List<int[]> list4 = new ArrayList<>();
        List<int[]> list5 = new ArrayList<>();

        List<int[]> list = new ArrayList<>();
        int order = 0;
        for (int i = 0 ; i < n ; i++){
            String[] s = in.nextLine().split(" ");
            int l1;
            int l2;
            int l3;
            if ("IN".equals(s[0])){
                l1 = 1;
            }else {
                l1 = 0;
            }
            l2 = Integer.parseInt(s[1]);
            l3 = Integer.parseInt(s[2]);
            if (l1 == 1){
                order++;
                list.add(new int[]{l1,l2,l3,order});
            }else {
                list.add(new int[]{l1,l2,l3,0});
            }
        }

        int left = 0;
        for (int i = 0 ; i < n ; i++){
            while (list.get(i)[3] == 0){

            }
        }

    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        PriorityQueue<int[]>[] printers = new PriorityQueue[6];
        for (int i = 1 ; i <= 5 ; i++){
            printers[i] = new PriorityQueue<>((a,b) -> {
                if (a[0] != b[0]){
                    return b[0] - a[0];
                }
                return a[2] - b[2];
            });
        }

        int filedId = 0;

        for (int i = 0 ; i < N ; i++){
            String[] parts = in.nextLine().split(" ");
            String event = parts[0];
            int printer = Integer.parseInt(parts[1]);

            if (event.equals("IN")){
                int priority = Integer.parseInt(parts[2]);
                filedId++;
                printers[printer].offer(new int[]{priority,filedId,filedId});
            }else if (event.equals("OUT")){
                if (printers[printer].isEmpty()){
                    System.out.println("NULL");
                }else {
                    int[] job = printers[printer].poll();
                    System.out.println(job[1]);
                }
            }
        }

    }
}
