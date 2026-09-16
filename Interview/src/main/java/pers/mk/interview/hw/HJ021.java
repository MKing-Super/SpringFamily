package pers.mk.interview.hw;

import java.util.Scanner;

/*

描述
规定这样一种密码的变换方法：
∙ ∙对于密码中的小写字母，参考九键手机键盘，将它们映射为对应的数字，具体地，abcabc 对应数字 22 、defdef 对应数字 33 、ghighi 对应数字 44 、jkljkl 对应数字 55 、mnomno 对应数字 66 、pqrspqrs 对应数字 77 、tuvtuv 对应数字 88 、wxyzwxyz 对应数字 99 ；
∙ ∙对于密码中的大写字母，先将其转换为小写，然后向后移动一位，即 ZZ 转换为 aa ，AA 转换为 bb ，BB 转换为 cc ，⋯⋯ ，YY 转换为 zz ，ZZ 转换为 aa 。
∙ ∙对于密码中的数字，保持不变。

现在，请你将给定的密码按照上述规则进行变换。
输入描述：
在一行上输入一个长度为 1≦length(s)≦1001≦length(s)≦100 的字符串 ss ，代表给定的密码。
输出描述：
在一行上输出一个字符串，代表变换后的密码。

 */
public class HJ021 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String password = in.nextLine();
        for (int i = 0 ; i < password.length() ; i++) {
            char t = password.charAt(i);
            if (t >= 'a' && t <= 'z') {
                if ("abc".contains(String.valueOf(t))) {
                    System.out.print(2);
                } else if ("def".contains(String.valueOf(t))) {
                    System.out.print(3);
                } else if ("ghi".contains(String.valueOf(t))) {
                    System.out.print(4);
                } else if ("jkl".contains(String.valueOf(t))) {
                    System.out.print(5);
                } else if ("mno".contains(String.valueOf(t))) {
                    System.out.print(6);
                } else if ("pqrs".contains(String.valueOf(t))) {
                    System.out.print(7);
                } else if ("tuv".contains(String.valueOf(t))) {
                    System.out.print(8);
                } else if ("wxyz".contains(String.valueOf(t))) {
                    System.out.print(9);
                }
                continue;
            }
            if(t >= 'A' && t <= 'Z'){
                if(t == 'Z'){
                    System.out.print("a");
                }else{
                    char[] resarr = String.valueOf(t).toLowerCase().toCharArray();

                    char res = (char)((int)resarr[0] + 1);
                    System.out.print(res);
                }
                continue;
            }
            System.out.print(t);
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
