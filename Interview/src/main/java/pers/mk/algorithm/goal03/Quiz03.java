package pers.mk.algorithm.goal03;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*

给定a-z，26个英文字母小写字符串组成的字符串A和B，
其中A可能存在重复字母，B不会存在重复字母，现从字符串A中按规则挑选一些字母可以组成字符串B挑选规则如下：
	1：同一个位置的字母只能挑选一次，
	2：被挑选字母的相对先后顺序不能被改变，
求最多可以同时从A中挑选多少组能组成B的字符串

输入描述
输入为2行，
第一行输入字符串a,第二行输入字符串b，行首行尾没有多余空格

输出描述
输出一行
包含一个数字表示最多可以同时从a中挑选多少组能组成b的字符串，行末没有多余空格

示例一
输入
badc
bac

输出
1

示例二
输入
badc
abc

输出
0

示例三
输入
bbadcac
bac

输出
2


 */
public class Quiz03 {
    public static void main(String[] args) {
        // 审题完全错误！
//        mk();
        // 经典问题：从 A 中最多能找出多少个不重叠的 B 作为子序列
        // 贪心地一组一组匹配：每次从头扫 A，按顺序找 B 的每个字符，找到就"跳过"，找完一组就计数 + 再来一轮。
        m1();
    }

    private static void mk(){
        Scanner in  = new Scanner(System.in);
        String par1 = in.nextLine();
        String par2 = in.nextLine();
        Map<String, Integer> map = new TreeMap<>();
        String[] strArr = new String[par2.length()];
        int[] intArr = new int[par2.length()];
        for (int i = 0 ; i < par2.length() ; i++){
            strArr[i] = String.valueOf(par2.charAt(i));
            intArr[i] = 0;
        }

        int jIndex = 0;
        for (int i = 0 ; i < par2.length() ; i++){
            String t1 = strArr[i];
            for (int j = jIndex; j < par1.length() ; j++){
                String t2 = String.valueOf(par1.charAt(j));
                if (t1.equals(t2)){
                    intArr[i] += 1;
                }
                if (j + 1 == par1.length()) break;
                String t3 = String.valueOf(par1.charAt(j + 1));
                if (intArr[i] > 0 && !t1.equals(t3) ){
                    jIndex = j + 1;
                    break;
                }
            }
        }

        int total = 1 ;
        for (int t : intArr){
            total *= t;
        }
        System.out.println(total);
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String A = in.nextLine();
        String B = in.nextLine();

        int count = 0;
        while (true){

            int j = 0;
            String remains = "";
            for (int i = 0 ; i < A.length() ; i++){
                if (j < B.length() && A.charAt(i) == B.charAt(j)){
                    j++;
                }else {
                    remains += A.charAt(i);
                }
            }
            if (j == B.length()){
                count++;
                A = remains;
            }else {
                break;
            }

        }

        System.out.println(count);


    }
}
