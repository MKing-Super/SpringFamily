package pers.mk.algorithm.goal01;

import java.util.*;

/*

绘图机器的绘图笔初始位置在原点(0,0)机器启动后按照以下规则来进行绘制直线。

1. 尝试沿着横线坐标正向绘制直线直到给定的终点E

2. 期间可以通过指令在纵坐标轴方向进行偏移，offsetY为正数表示正向偏移,为负数表示负向偏移

给定的横坐标终点值E 以及若干条绘制指令，

请计算绘制的直线和横坐标轴以及x=E的直线组成的图形面积。

输入描述

首行为两个整数 N 和 E
表示有N条指令,机器运行的横坐标终点值E
接下来N行 每行两个整数表示一条绘制指令x offsetY
用例保证横坐标x以递增排序的方式出现
且不会出现相同横坐标x
取值范围

0<N<=10000
0<=x<=E<=20000
-10000<=offsetY<=10000
输出描述

一个整数表示计算得到的面积 用例保证结果范围在0到4294967295之内。

示例1   输入输出示例仅供调试，后台判题数据一般不包含示例
输入
4 10
1 1
2 1
3 1
4 -2

输出
12

示例2   输入输出示例仅供调试，后台判题数据一般不包含示例
输入
2 4
0 1
2 -2

输出
4


 */
public class Quiz02 {
    public static void main(String[] args) {
//        mk();
//        https://www.nowcoder.com/discuss/595648285843402752
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().trim().split(" ");
        int n = Integer.parseInt(str[0]);
        int e = Integer.parseInt(str[1]);
        List<int[]> list = new ArrayList<>();
        int tY = 0;
        for (int i = 0 ; i < n ; i++){
            String[] tStr = in.nextLine().split(" ");
            int tX = Integer.parseInt(tStr[0]);
            int tOffsetY = Integer.parseInt(tStr[1]);
            tY = tY + tOffsetY;
            list.add(new int[]{tX,tY});
        }
        int lastX = e;
        int lastY = list.get(list.size() - 1)[1];
        list.add(new int[]{lastX,lastY});

        Integer area = 0;
        for (int i = 0 ; i < list.size() - 1 ; i++){
            int curX = list.get(i)[0];
            int curY = list.get(i)[1];
            int nextX = list.get(i + 1)[0];
            int nextY = list.get(i + 1)[1];
            if (nextX > curX){
                int width = Math.abs(nextX - curX);
                int height = Math.abs(curY );
                area += width * height;
            }
        }
        System.out.println(area);

    }

    private static void m1(){
        Scanner in = new Scanner(System.in);

        // 读取输入的 N 和 E
        int N = in.nextInt(), E = in.nextInt();

        // 初始化图形总面积
        long totalArea = 0;
        // 绘图笔位置 (x, y)
        int x = 0, y = 0;

        // 读取命令并存储在数组中
        for (int i = 0; i < N; i++) {
            int curX = in.nextInt(), offsetY = in.nextInt();
            // 累计当前边和前一条边组成的面积
            totalArea += 1L * (curX - x) * Math.abs(y);

            // 更新绘图笔位置
            x = curX;
            y += offsetY;
        }

        // 计算最后一条边组成的面积
        totalArea += (E - x) * Math.abs(y);

        // 输出结果
        System.out.println(totalArea);
    }


}
