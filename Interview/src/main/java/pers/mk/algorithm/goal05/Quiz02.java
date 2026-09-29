package pers.mk.algorithm.goal05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
//        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().split(" ");
        String str1 = strArr[0];
        String str2 = strArr[1];
        String[] split = str1.split("");
        HashMap<String, Integer> map = new HashMap<>();
        for (String t : split){
            if (map.get(t) == null){
                map.put(t,1);
            }else {
                map.put(t,map.get(t) + 1);
            }
        }

        int left = -1;

        boolean is = false;
        for (int right = 0 ; right < str2.length() ; right++){
            String rightItem = String.valueOf(str2.charAt(right));

            if (map.get(rightItem) == null){
                left = -1;
                map.clear();
                for (String t : split){
                    if (map.get(t) == null){
                        map.put(t,1);
                    }else {
                        map.put(t,map.get(t) + 1);
                    }
                }
                continue;
            }
            while ( map.get(rightItem) == 0 ){
                left++;
                map.put(rightItem,map.get(rightItem) + 1);
            }

            if (map.get(rightItem) != null && map.get(rightItem) > 0 && left == -1){
                left = right;
            }

            if (map.get(rightItem) != null && map.get(rightItem) > 0 ){
                map.put(rightItem,map.get(rightItem) - 1);
            }

            boolean pass = true;
            for (String key : map.keySet()){
                if (map.get(key) != 0){
                    pass = false;
                    break;
                }
            }
            if (pass){
                is = true;
                System.out.println(left);
            }

        }

        if (!is){
            System.out.println(-1);
        }
    }




    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] strArr = in.nextLine().split(" ");
        String str1 = strArr[0];
        String str2 = strArr[1];

        if (str1.length() > str2.length()){
            System.out.println(-1);
            return;
        }

        HashMap<Character, Integer> needMap = new HashMap<>();
        for (char c : str1.toCharArray()){
            needMap.put(c,needMap.getOrDefault(c,0) + 1);
        }

        HashMap<Character, Integer> windowMap = new HashMap<>();
        int left = 0;

        for (int right = 0 ; right < str2.length() ; right++){
            char c = str2.charAt(right);
            windowMap.put(c,windowMap.getOrDefault(c,0) + 1);

            if (right - left + 1 > str1.length()){
                char d = str2.charAt(left);
                windowMap.put(d,windowMap.get(d) - 1);
                if (windowMap.get(d) == 0){
                    windowMap.remove(d);
                }
                left++;
            }

            if (right - left + 1 == str1.length() && windowMap.equals(needMap)){
                System.out.println(left);
                return;
            }
        }
        System.out.println(-1);

    }
}
