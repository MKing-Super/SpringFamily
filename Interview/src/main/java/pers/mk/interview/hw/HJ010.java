package pers.mk.interview.hw;

import java.util.HashSet;
import java.util.Scanner;

/*

描述
对于给定的字符串，统计其中的 ASCII 码在 00 到 127127 范围内的不同字符的个数。

备注：受限于输入，本题实际输入字符集为 ASCII 码在 3333 到 126126 范围内的可见字符。您可以参阅下表获得其详细信息（您可能关注的内容是，这其中不包含空格、换行）。



输入描述：
输入一个长度 1≦length(s)≦5001≦length(s)≦500，仅由图片中的可见字符构成的字符串 ss。
输出描述：
在一行上输出一个整数，代表给定字符串中 ASCII 码在 00 到 127127 范围内的不同字符的个数。
示例1
输入：

[@A8aA].0

复制
输出：

8

 */
public class HJ010 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String nStr = in.nextLine();
        HashSet<Integer> set = new HashSet();
        for(int i = 0 ; i < nStr.length() ; i++){
            set.add((int)nStr.charAt(i));
        }
        System.out.println(set.size());
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
