package pers.mk.algorithm.goal05;

import java.util.HashMap;
import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        // 理解题目的含义啊！！！
        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int total = str.length();
        int average = str.length() / 4;
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('W',0);
        map.put('A',0);
        map.put('S',0);
        map.put('D',0);
        for (char c : str.toCharArray()){
            map.put(c,map.getOrDefault(c,0) + 1);
        }
        for (Character c : map.keySet()){
            map.put(c,  map.get(c) - average);
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;

        int wCount = 0;
        int sCount = 0;
        int aCount = 0;
        int dCount = 0;

        for (int right = 0 ; right < total ; right++){
            char c = str.charAt(right);

            if (c == 'W') wCount++;
            else if (c == 'S') sCount++;
            else if (c == 'A') aCount++;
            else if (c == 'D') dCount++;

            while (left <= right){
                if (wCount >= map.get('W')
                        && sCount >= map.get('S')
                        && aCount >= map.get('A')
                        && dCount >= map.get('D')
                ){
                    minLen = Math.min(minLen,right - left + 1);

                    char leftChar = str.charAt(right);
                    if (leftChar == 'W') wCount--;
                    else if (leftChar == 'S') sCount--;
                    else if (leftChar == 'A') aCount--;
                    else if (leftChar == 'D') dCount--;

                    left++;
                }else {
                    break;
                }
            }
        }

        System.out.println(minLen);
    }

    private static void m1(){

    }
}
