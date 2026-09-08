package pers.mk.interview.hw;

import java.util.Scanner;

/*
描述
对于给定的由小写字母和数字混合构成的字符串 ss，你需要按每 88 个字符换一行的方式书写它，具体地：
∙ ∙书写前 88 个字符，换行；
∙ ∙书写接下来的 88 个字符，换行；
∙ ∙……
∙ ∙重复上述过程，直到字符串被完全书写。
特别地，如果最后一行不满 88 个字符，则需要在字符串末尾补充 00，直到长度为 88。
输入描述：
在一行上输入一个长度 1≦length(s)≦1001≦length(s)≦100，由小写字母和数字构成的字符串 ss。
输出描述：
输出若干行，每行输出 88 个字符，代表按题意书写的结果。
 */
public class HJ004 {
    public static void main(String[] args) {
//        mk();
//        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] arr = s.split("");
        int tmp = 0;
        int surplus = 8 - arr.length%8;
        for (int i = 0 ; i < arr.length ; i++) {
            tmp++;
            if (tmp < 8) {
                System.out.print(arr[i]);
            } else {
                System.out.println(arr[i]);
                tmp = 0;
            }

        }
        if(surplus > 0 && surplus != 8){
            for(int i = 0 ; i < surplus ; i++){
                System.out.print(0);
            }
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String inputStr = in.nextLine();
        int index = inputStr.length();
        if(inputStr.length() % 8 != 0){
            index = inputStr.length() + (8 - inputStr.length() % 8);
        }
        String temp = "";
        for(int i = 0 ; i < index;i++ ){
            if(i < inputStr.length()){
                temp += inputStr.charAt(i);
            }else{
                temp += '0';
            }
            if(temp.length() == 8){
                System.out.println(temp);
                temp = "";
            }
        }
    }

    private static void m2(){
        Scanner in = new Scanner(System.in);
        if (!in.hasNextLine()) {
            return ;
        }
        String s = in.nextLine().trim();
        StringBuilder sb = new StringBuilder(s);

        while (sb.length() % 8 != 0) {
            sb.append('0');
        }
        for (int i = 0; i < sb.length() / 8; i++) {
            System.out.println(sb.substring(8 * i, 8*(i+1)));
        }
    }

}
