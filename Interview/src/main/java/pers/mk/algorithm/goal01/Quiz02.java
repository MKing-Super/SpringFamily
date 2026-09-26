package pers.mk.algorithm.goal01;

import java.util.*;

/*

实现一个简单的绘图模块，绘图模块仅支持矩形的绘制和擦除。

当新绘制的矩形与已有图形重叠时，对图形取并集；
当新擦除的矩形与已有图形重叠时，对图形取差集。
给定一系列绘制和擦除操作，计算最终图形的面积。
输入描述
第一行为操作数N，接下来的N行格式为：

d x1 y1 x2 y2：d表示绘制操作，(x1,y1)为矩形左上角坐标，(x2,y2)为右下角坐标；
e x1 y1 x2 y2：e表示擦除操作，坐标含义同上。
坐标均为整数且范围在[-100, 100]内，用例保证坐标有效性。
输出描述
输出最终图形的面积。

示例1
输入：
2
d 0 2 2 0
d -1 1 1 -1
输出：
7


示例2
输入：
2
d 0 2 2 0
e -1 1 1 -1
输出：
3


 */
public class Quiz02 {
    public static void main(String[] args) {
        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int ln = Integer.parseInt(in.nextLine());
        HashSet<String> areaSet = new HashSet<>();
        for (int i = 0 ; i < ln ; i++){
            String str = in.nextLine();
            String[] tstr = str.split(" ");
            String operation = tstr[0];
            int x1 = Integer.parseInt(tstr[1]);
            int y1 = Integer.parseInt(tstr[2]);
            int x2 = Integer.parseInt(tstr[3]);
            int y2 = Integer.parseInt(tstr[4]);
            for(int m = x1 ; m < x2 ; m++){
                for (int n = y1 ; n > y2 ; n--){
                    if ("d".equals(operation)){
                        areaSet.add(m + "," + n);
                    }else {
                        areaSet.remove(m + "," + n);
                    }
                }
            }
        }
        System.out.println(areaSet.size());
    }

    private static void m1(){

    }
}
