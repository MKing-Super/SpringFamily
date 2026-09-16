package pers.mk.interview.hw;

import java.util.*;

public class HJ023 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0 ; i < str.length() ; i++){
            char key = str.charAt(i);
            if(map.get(key) == null){
                map.put(key,1);
            }else{
                map.put(key,map.get(key) + 1);
            }
        }
        int min = 21;
        List<Character> minChar = new ArrayList<>();
        for(char key : map.keySet()){
            int v = map.get(key);
            if(v < min){
                min = v;
                minChar.clear();
                minChar.add(key);
            }else if(v == min){
                minChar.add(key);
            }
        }
        for(Character c : minChar){
            str = str.replaceAll(String.valueOf(c),"");
        }
        System.out.print(str);
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String s = in.nextLine();
            char[] charArray = s.toCharArray();
            int length = s.length();
            //使用HashMap记录每个字符出现的次数
            HashMap<Character,Integer> map = new HashMap<>();
            for(int i=0;i<length;i++){
                map.put(charArray[i], Integer.sum(Optional.ofNullable(map.get(charArray[i])).orElse(0),1));
            }
            //找到最小的次数
            int min = Collections.min(map.values());
            //遍历输入的字符串，从map中取出该字符串对应的次数，如果不等于最小次数就输出
            for(int i=0;i<length;i++){
                Integer i1 = map.get(charArray[i]);
                if(i1!=min){
                    System.out.print(charArray[i]);
                }
            }
        }
    }

    private static void m2(){

    }

}
