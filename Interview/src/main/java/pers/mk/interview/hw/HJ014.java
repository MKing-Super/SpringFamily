package pers.mk.interview.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HJ014 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            list.add(in.next());
        }
        Collections.sort(list);
        for(int i = 0 ; i < list.size() ; i++){
            System.out.println(list.get(i));
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
