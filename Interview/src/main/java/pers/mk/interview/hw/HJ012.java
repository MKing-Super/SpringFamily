package pers.mk.interview.hw;

import java.util.Scanner;

/*

描述
对于给定的仅由小写字母构成的字符串 ss，将其倒过来输出。
输入描述：
在一行上输入一个长度 1≦length(s)≦1031≦length(s)≦103，仅由小写字母构成的字符串 ss。
输出描述：
在一行上输出一个字符串，代表颠倒后的字符串。
示例1
输入：

redocwonolleh

复制
输出：

hellonowcoder

复制
示例2
输入：

a

复制
输出：

a

 */
public class HJ012 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String s=in.nextLine();
        for(int i=s.length()-1;i>=0;i--){
            System.out.print(s.charAt(i));
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
