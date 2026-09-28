package pers.mk.algorithm.goal03;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/*

题目描述
在软件版本管理中，版本号由点（.）分割的数字组成，例如 1.2.3 和 2.0。现在需要编写一个函数，计算两个版本号之间的可用版本号个数。这里的可用版本号指的是所有满足 version1 < x < version2 的版本号 x 的个数。若 version1 >= version2，则返回 0。

输入描述
输入两个字符串version1和version2，均遵循以下规定：
1.版本号由数字和点组成，且至少包含一个数字。
2.点不会作为版本号的开头或结尾，也不会连续出现。
3.每个数字部分的数值忽略前导零(例如1.01和1.001视为相同)
4.两个字符串中间以英文逗号分割

输出描述
输出一个整数，表示可用版本号的个数。具体规则如下：
- 如果version1 >= version2，返回0
- 否则，找到第一个不同的版本号段，假设在位置i，version1的该段值为v1，version2的该段值为v2。可用版本号的个数为v2-v1-1。
- 如果第一个不同段之后的版本段在version2中不全为0，则返回0


示例1：
输入：
1.2,1.4

输出：
1

说明：
不同段中version1为2，version2 为4，4-2-1=1

输入：1.2,1.3
输出：0（因为 1.2 和 1.3 之间无其他版本号）

输入：1.0,2.0
输出：1（假设 1.5 是唯一介于两者之间的版本号）

 */
public class Quiz01 {
    public static void main(String[] args) {
        // . 转义为  \\.
        // 思路错误，应先把短的版本号补齐
//        mk();

        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] strArr = in.next().split(",");
        String[] v1StrArr = strArr[0].split(".");
        String[] v2StrArr = strArr[1].split(".");

        int[] v1Arr = new int[v1StrArr.length];
        for (int i = 0 ; i < v1StrArr.length ; i++){
            v1Arr[i] = Integer.parseInt(v1StrArr[i]);
        }
        int[] v2Arr = new int[v2StrArr.length];
        for (int i = 0 ; i < v2StrArr.length ; i++){
            v2Arr[i] = Integer.parseInt(v2StrArr[i]);
        }

        if (v1Arr.length == v2Arr.length){
            //
            boolean c1 = true;
            int x = 0;
            for (int i = 0 ; i < v1StrArr.length ; i++){
                if (v1Arr[i] >= v2Arr[i]){
                    c1 = false;
                    break;
                }else {
                    if (x == 0){
                        x = v2Arr[i] - v1Arr[i];
                    }
                }
                if (x != 0){
                    if (v1Arr[i] != 0 || v2Arr[i] != 0){
                        x = 0;
                    }
                }
            }
            if (c1){
                System.out.println(0);
            }else {
                System.out.println(x);
            }
        }
    }

    private static void m1() {
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().split("\\,");
        String[] v1StrArr = strArr[0].split("\\.");
        String[] v2StrArr = strArr[1].split("\\.");

        int maxLen = Math.max(v1StrArr.length, v2StrArr.length);

        int[] v1Arr = new int[maxLen];
        int[] v2Arr = new int[maxLen];

        for (int i = 0; i < v1StrArr.length; i++) {
            v1Arr[i] = Integer.parseInt(v1StrArr[i]);
        }
        for (int i = 0; i < v2StrArr.length; i++) {
            v2Arr[i] = Integer.parseInt(v2StrArr[i]);
        }

        int xIndex = -1;

        for (int i = 0; i < maxLen; i++) {
            if (v1Arr[i] > v2Arr[i]) {
                System.out.println(0);
                return;
            } else if (v1Arr[i] < v2Arr[i]) {
                xIndex = i;
                break;
            }
        }

        // 完全相等
        if (xIndex == -1) {
            System.out.println(0);
            return;
        }

        // version2 后续段必须全为 0
        for (int i = xIndex + 1; i < maxLen; i++) {
            if (v2Arr[i] != 0) {
                System.out.println(0);
                return;
            }
        }

        int res = v2Arr[xIndex] - v1Arr[xIndex] - 1;
        if (res == 0 ){
            System.out.println(v2Arr[xIndex] - v1Arr[xIndex]);
            return;
        }
        System.out.println(res);
    }


}
