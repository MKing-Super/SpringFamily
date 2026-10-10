package pers.mk.algorithm.goal03;

import java.util.*;

public class Quiz09 {
    public static void main(String[] args) {
        // 完全正确！逻辑清晰，实现完整
        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0 ; i < str.length() ; i++){
            char c = str.charAt(i);
            map.putIfAbsent(c,0);
            map.put(c,map.get(c) + 1);
        }

        ArrayList<Character> minNumList = new ArrayList<>();
        int minNum = Integer.MAX_VALUE;
        for (Character key : map.keySet()){
            if (map.get(key) < minNum){
                minNumList.clear();
                minNum = map.get(key);
                minNumList.add(key);
            }else if (map.get(key) == minNum){
                minNumList.add(key);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < str.length() ; i++){
            if (!minNumList.contains(str.charAt(i))){
                sb.append(str.charAt(i));
            }
        }
        System.out.println(sb.toString());
    }

    private static void m1(){

    }
}
