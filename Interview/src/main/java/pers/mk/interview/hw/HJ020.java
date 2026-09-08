package pers.mk.interview.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HJ020 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        while (in.hasNext()) {
            String str = in.nextLine();
            list.add(str);
        }
        for (String str : list) {
            if (str.length() < 8) {
                System.out.println("NG");
                continue;
            }
            boolean c1 = false;
            boolean c2 = false;
            boolean c3 = false;
            boolean c4 = false;
            for (int i = 0 ; i < str.length() ; i++) {
                int ascii = (int)str.charAt(i);
                if ( ascii >= 65 && ascii <= 90) {
                    c1 = true;
                } else if ( ascii >= 97 && ascii <= 122) {
                    c2 = true;
                } else if (ascii >= 48 && ascii <= 57) {
                    c3 = true;
                } else {
                    c4 = true;
                }
            }
            if (c1 && c2 && c3 || c1 && c2 && c4 || c1 && c3 && c4 || c2 && c3 && c4 ) {

            } else {
                System.out.println("NG");
                continue;
            }

            boolean c5 = false;
            for (int len = 3 ; len <= str.length() / 2 ; len++) {
                for (int i = 0 ; i < str.length() - len ; i++) {
                    int start1 = i;
                    int end1 = i + len;
                    String str1 = str.substring(start1, end1);
                    for (int j = i + len ; j < str.length() - len ; j++) {
                        int start2 = j;
                        int end2 = j + len;
                        String str2 = str.substring(start2, end2);
                        if (str1.equals(str2)) {
                            c5 = true;
                            break;
                        }
                    }
                    if (c5) {
                        break;
                    }
                }
                if (c5) {
                    break;
                }
            }
            if(c5){
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
