package pers.mk.interview.algorithm.a;

import org.hamcrest.core.Is;

/*
4. 字符串相加

    题目描述：给定两个以字符串形式表示的非负整数 num1和 num2，返回它们的和，以字符串形式表示。你不能使用任何内建的大整数库，也不能直接将输入的字符串转换为整数形式。
    示例：
        输入：num1 = "456", num2 = "77"
        输出："533"

 */
public class a1_4 {
    public static void main(String[] args) {
        String num1 = "456";
        String num2 = "77";

        // method 1:
        String res1 = "";
        int jw = 0;
        int sy = 0;
        int i = 0;
        while (true){
            int n1 = 0;
            int n2 = 0;
            if (num1.length() - i - 1 >= 0){
                String s1 = num1.substring(num1.length() - i - 1,num1.length() - i);
                n1 = Integer.parseInt(s1);
            }
            if (num2.length() - i - 1 >= 0){
                String s2 = num2.substring(num2.length() - i - 1,num2.length() - i);
                n2 = Integer.parseInt(s2);
            }
            if (n1 + n2 + jw > 10){
                sy = n1 + n2 + jw - 10;
                jw = 1;
            }else {
                sy = n1 + n2 + jw;
                jw = 0;
            }

            if (n1 == 0 && n2 == 0){
                break;
            }
            res1 += sy;
            sy = 0;
            i++;
            System.out.println(res1);
        }
        String res11 = "";
        for (int b = res1.length()-1 ; b >= 0  ; b--){
            res11 += res1.charAt(b);
        }
        System.out.println(res11);





    }
}
