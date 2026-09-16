package pers.mk.interview.hw;

import java.util.Scanner;

public class HJ022 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int res = 0;
            int drinked = 0;
            if (n == 0) {
                continue;
            }
            while (true) {
                drinked = n / 3;
                n = n % 3 + drinked;
                res += drinked;
                if (n < 3) {
                    if (n == 2) {
                        n = 3;
                    } else {
                        System.out.println(res);
                        break;
                    }
                }

            }
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
