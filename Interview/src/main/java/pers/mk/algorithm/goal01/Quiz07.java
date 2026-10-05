package pers.mk.algorithm.goal01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Quiz07 {
    public static void main(String[] args) {
        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0 ; i < n ; i++){
            String[] split = in.nextLine().trim().split(" ");
            int idx = Integer.parseInt(split[0]);
            int value = Integer.parseInt(split[1]);
            map.putIfAbsent(idx,0);
            map.put(idx,map.get(idx) + value);
        }

        for (int key : map.keySet()){
            System.out.println(key + " " + map.get(key));
        }
    }

    private static void m1(){

    }
}
