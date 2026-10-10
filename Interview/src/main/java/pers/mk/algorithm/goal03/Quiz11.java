package pers.mk.algorithm.goal03;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Scanner;

public class Quiz11 {
    public static void main(String[] args) {
        // 错误点：当有连续多个非字母字符时，会被替换为连续多个空格
//        mk();

        // 修正：过滤空字符串
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < str.length()  ;i++){
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z' ||
                    str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                sb.append(str.charAt(i));
            }else {
                sb.append(" ");
            }
        }

        String[] split = sb.toString().split(" ");
        System.out.println(JSON.toJSONString(split));
        int left = 0 ;
        int right = split.length - 1;
        while (left < right){
            String temp = split[left];
            split[left] = split[right];
            split[right] = temp;
            left++;
            right--;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < split.length ; i++){
            if (i > 0){
                result.append(" ");
            }
            result.append(split[i]);
        }
        System.out.println(result);
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < str.length()  ;i++){
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z' ||
                    str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                sb.append(str.charAt(i));
            }else {
                sb.append(" ");
            }
        }

        String[] split = sb.toString().split(" ");
        System.out.println(JSON.toJSONString(split));

        ArrayList<String> wordList = new ArrayList<>();
        for (String s : split){
            if (!s.isEmpty()){
                wordList.add(s);
            }
        }
        String[] words = wordList.toArray(new String[0]);

        System.out.println(JSON.toJSONString(words));
        int left = 0 ;
        int right = words.length - 1;
        while (left < right){
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < words.length ; i++){
            if (i > 0){
                result.append(" ");
            }
            result.append(words[i]);
        }
        System.out.println(result);
    }

}
