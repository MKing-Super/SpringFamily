package pers.mk.algorithm.goal02;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*

模拟AP安装，将AP的位置投影到二维坐标系中，给出每个AP的WIFI信号强度，信号强度会随着距离的增加而减弱。给定: 第一行是2个整数N,D(N<=100,D<=100)，其中N表示AP数量，D表示AP能够的信号能够覆盖的最大距离。接下来的N行里，每行包含3个整数x,y,s，表示这个AP在坐标系的位置为(x,y)，x,y>0，信号强度为s。所有坐标点是在X-Y坐标系内的整数坐标。为了简化计算，两个坐标之间的距离用切比雪夫距离表示(在二维空间内，两个点之间的切比雪夫距离为它们横坐标之差的绝对值与纵坐标之差的绝对值的最大值)
需要你计算WIFI信号最好的坐标，
信号衰减计算方式: 如果第i个AP能到达(x,y)，那么该AP在此处的信号为 [s/(1+d)」(向下取整)，d表示这个AP跟此坐标的切比雪夫距离。一个坐标的信号强度是所有能到达该坐标的AP的信号强度之和。

输入描述
第一行是2个整数N,D(N<=100,D<=100)，其中N表示AP数量，D表示AP能够的信号能够覆盖的最大距离 接下来N行，每行包含3个整数x,y,s，表示这个AP在坐标系的位置为(x,y)，x,y>0，信号强度为s

输出描述
WIFI信号最好的坐标，如果存在多个信号一致的坐标，输出字典序最小的非负坐标 坐标(x1,y1)字典序比另一个坐标(x2,y2)小，需满足以下条件之一:
要么 x1<x2, 要么x1==x2 目y1<y2

示例1：
输入：
3 2
1 2 3
2 1 3
3 1 3

输出：
1 2

说明：
坐标[1,2]处的wifi信号最好


 */
public class Quiz06 {
    public static void main(String[] args) {
//        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int d = Integer.parseInt(str[1]);

        int top = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        List<int[]> list = new ArrayList<>();
        for (int i = 0 ;i < n ; i++ ){
            String[] split = in.nextLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int s = Integer.parseInt(split[2]);
            list.add(new int[]{x,y,s});

            left = Math.min(left,x);
            right = Math.max(right,x);
            bottom = Math.min(bottom,y);
            top = Math.max(top,y);
        }
        // 范围
        left = Math.max(0,left - d);
        bottom = Math.max(0,bottom - d);
        right += d;
        top += d;

        int maxStrength = Integer.MIN_VALUE;
        List<int[]> resList = new ArrayList<>();
        for (int q = left ; q <= right ; q++){
            for (int w = bottom ; w <= top ; w++){
                int curX = q;
                int curY = w;
                int curStrength = 0;
                for (int e = 0 ; e < n ; e++){
                    int s = list.get(e)[2];
                    int apX = list.get(e)[0];
                    int apY = list.get(e)[1];
                    int strength = signalStrengthMk(apX, apY, s, curX, curY);
                    curStrength += strength;
                }
                if (curStrength > maxStrength){
                    maxStrength = curStrength;
                    resList.clear();
                    resList.add(new int[]{curX,curY});
                }else if (curStrength == maxStrength){
                    resList.add(new int[]{curX,curY});
                }
            }
        }

        resList.sort((a,b) -> {
            if (a[0] != b[0]){
                return Integer.compare(a[0],b[0]);
            }
            return Integer.compare(a[1],b[1]);
        });

//        System.out.println(JSON.toJSONString(resList));
        System.out.println(resList.get(0)[0] + " " + resList.get(0)[1]);
    }

    private static int signalStrengthMk(int apX,int apY,int s,int curX,int curY){
        int xB = Math.abs(apX - curX);
        int yB = Math.abs(apY - curY);
        int d = Math.max(xB, yB);
        return s / (1 + d);
    }

    private static int signalStrength(int apX, int apY, int s, int curX, int curY, int D) {
        int d = Math.max(Math.abs(apX - curX), Math.abs(apY - curY));
        if (d > D) return 0;
        return s / (1 + d);
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int D = Integer.parseInt(str[1]);

        int top = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] split = in.nextLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int s = Integer.parseInt(split[2]);
            list.add(new int[]{x, y, s});

            left = Math.min(left, x);
            right = Math.max(right, x);
            bottom = Math.min(bottom, y);
            top = Math.max(top, y);
        }

        left = Math.max(0, left - D);
        bottom = Math.max(0, bottom - D);
        right += D;
        top += D;

        int maxStrength = Integer.MIN_VALUE;
        int bestX = 0, bestY = 0;

        for (int q = left; q <= right; q++) {
            for (int w = bottom; w <= top; w++) {
                int curStrength = 0;
                for (int e = 0; e < n; e++) {
                    int[] ap = list.get(e);
                    curStrength += signalStrength(ap[0], ap[1], ap[2], q, w, D);
                }

                if (curStrength > maxStrength) {
                    maxStrength = curStrength;
                    bestX = q;
                    bestY = w;
                } else if (curStrength == maxStrength) {
                    if (q < bestX || (q == bestX && w < bestY)) {
                        bestX = q;
                        bestY = w;
                    }
                }
            }
        }

        System.out.println(bestX + " " + bestY);
    }
}
