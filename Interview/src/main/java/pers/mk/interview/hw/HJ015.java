package pers.mk.interview.hw;

import java.util.Scanner;

/*

描述
对于给定的 int 型的十进制整数 nn，统计其在内存中存储时 11 的个数。换句话说，即统计其二进制表示中 11 的个数。
输入描述：
在一行上输入一个整数 n(0≦n<231)n(0≦n<231)，代表给定的数字。
输出描述：
在一行上输出一个整数，代表 nn 的二进制表示中 11 的个数。
示例1
输入：

10

复制
输出：

2

复制
说明：

十进制 1
1 到 10
10 的二进制表示如下：
∙ 
∙十进制 (1)10
(1)10​ 等于二进制 (1)2
(1)2​；
∙ 
∙十进制 (2)10
(2)10​ 等于二进制 (10)2
(10)2​；
∙ 
∙十进制 (3)10
(3)10​ 等于二进制 (11)2
(11)2​；
∙ 
∙十进制 (4)10
(4)10​ 等于二进制 (100)2
(100)2​；
∙ 
∙十进制 (5)10
(5)10​ 等于二进制 (101)2
(101)2​；
∙ 
∙十进制 (6)10
(6)10​ 等于二进制 (110)2
(110)2​；
∙ 
∙十进制 (7)10
(7)10​ 等于二进制 (111)2
(111)2​；
∙ 
∙十进制 (8)10
(8)10​ 等于二进制 (1000)2
(1000)2​；
∙ 
∙十进制 (9)10
(9)10​ 等于二进制 (1001)2
(1001)2​；
∙ 
∙十进制 (10)10
(10)10​ 等于二进制 (1010)2
(1010)2​。

示例2
输入：

0

复制
输出：

0

 */
public class HJ015 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int total = 0;
        while(true){
            int surplus = n%2;
            n = n/2;

            if(surplus == 1){
                total += 1;
            }
            if(n == 0){
                break;
            }
        }
        System.out.print(total);
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
