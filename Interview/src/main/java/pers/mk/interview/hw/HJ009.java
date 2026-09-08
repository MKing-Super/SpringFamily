package pers.mk.interview.hw;

import java.util.Scanner;

/*
描述
对于给定的正整数 nn ，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。具体地，如果遇到相同数字，保留在最右侧出现的第一个数字。
输入描述：
在一行上输入一个整数 n(1≦n≦108)n(1≦n≦108) 代表给定的整数。保证 nn 的最后一位不为 00 。
输出描述：
在一行上输出一个整数，代表处理后的数字。
示例1
输入：

9876673

复制
输出：

37689

复制
说明：

在这个样例中，先将数字倒序，得到 3766789
3766789，然后去除重复数字，得到 37689
37689。

示例2
输入：

12345678

复制
输出：

87654321
 */
public class HJ009 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String nStr = in.nextLine();
        String res = "";
        for(int i = nStr.length() - 1 ; i >= 0 ; i--){
            String t =  String.valueOf(nStr.charAt(i));

            if(!res.contains(t)){
                res += t;
            }
        }
        System.out.println(res);
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
