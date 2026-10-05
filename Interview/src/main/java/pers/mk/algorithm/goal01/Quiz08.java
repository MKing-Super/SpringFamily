package pers.mk.algorithm.goal01;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Quiz08 {
    public static void main(String[] args) {
        // 使用了 LinkedHashMap
        mk();
        // 使用了 LinkedHashSet
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = str.length() - 1 ; i >= 0 ; i--){
            char c = str.charAt(i);
            map.put(c,1);
        }
        for (Character c : map.keySet()){
            System.out.print(c);
        }
        System.out.println();
    }




    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        LinkedHashSet<Character> set = new LinkedHashSet<>();

        // 从右向左遍历，LinkedHashSet 会自动去重并保持插入顺序
        for (int i = str.length() - 1; i >= 0; i--) {
            set.add(str.charAt(i));
        }

        // 输出结果
        StringBuilder sb = new StringBuilder();
        for (char c : set) {
            sb.append(c);
        }
        System.out.println(sb.toString());
    }

}
