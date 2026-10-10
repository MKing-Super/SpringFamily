package pers.mk.algorithm.goal03;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz10 {
    public static void main(String[] args) {
        // list 排序 编写不当，可能会导致错误！实际没啥事
//        mk();

        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();

        boolean[] isWordArr = new boolean[str.length()];
        Arrays.fill(isWordArr,false);
        ArrayList<Character> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0 ; i < chars.length ; i++){
            if (chars[i] >= 'a' && chars[i] <= 'z' ||
                    chars[i] >= 'A' && chars[i] <= 'Z'){
                list.add(chars[i]);
                isWordArr[i] = true;
            }
        }

        list.sort((a,b) -> {
            String s1 = String.valueOf(a).toLowerCase();
            String s2 = String.valueOf(b).toLowerCase();
            if (!s1.equals(s2)){
                return s1.compareTo(s2);
            }
            return s1.compareTo(s2);
        });

        int index = 0;
        for (int i = 0 ; i < isWordArr.length ; i++){
            if (isWordArr[i]){
                chars[i] = list.get(index);
                index++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < chars.length ; i++){
            sb.append(chars[i]);
        }
        System.out.println(sb.toString());
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();

        boolean[] isWordArr = new boolean[str.length()];
        Arrays.fill(isWordArr,false);
        ArrayList<Character> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0 ; i < chars.length ; i++){
            if (chars[i] >= 'a' && chars[i] <= 'z' ||
                    chars[i] >= 'A' && chars[i] <= 'Z'){
                list.add(chars[i]);
                isWordArr[i] = true;
            }
        }

        // 纠正点：
        list.sort((a,b) -> {
            char lowerA = Character.toLowerCase(a);
            char lowerB = Character.toLowerCase(b);
            if (lowerA != lowerB){
                return lowerA - lowerB;
            }
            return 0;
        });

        int index = 0;
        for (int i = 0 ; i < isWordArr.length ; i++){
            if (isWordArr[i]){
                chars[i] = list.get(index);
                index++;
            }
        }

        System.out.println(new String(chars));
    }

}
