package pers.mk.interview.hw;

import java.util.Scanner;
import java.util.TreeMap;

public class HJ008 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TreeMap<Integer,Integer> map = new TreeMap<>();

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            int tI = in.nextInt();
            int tV = in.nextInt();
            if(map.get(tI) != null){
                tV = tV + map.get(tI);
                map.put(tI,tV);
            }else{
                map.put(tI,tV);
            }
        }

        for(Integer key : map.keySet()){
            int v = map.get(key);
            System.out.println(key + " " + v);
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
