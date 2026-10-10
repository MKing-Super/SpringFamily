package pers.mk.algorithm.goal03;

import java.util.Scanner;

public class Quiz12 {
    public static void main(String[] args) {
        // 完全正确
//        mk();

        // 优化：使用 Character 类的静态方法
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int word = 0;
        int blank = 0;
        int num = 0;
        int other = 0;
        for (int i = 0 ; i < str.length() ; i++){
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'){
                word++;
            }else if (c == ' '){
                blank++;
            }else if (c >= '0' && c <= '9'){
                num++;
            }else {
                other++;
            }
        }

        System.out.println(word);
        System.out.println(blank);
        System.out.println(num);
        System.out.println(other);
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int word = 0, blank = 0, num = 0, other = 0;

        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                word++;
            } else if (Character.isSpaceChar(c)) {
                blank++;
            } else if (Character.isDigit(c)) {
                num++;
            } else {
                other++;
            }
        }

        System.out.printf("%d\n%d\n%d\n%d", word, blank, num, other);
    }

}
