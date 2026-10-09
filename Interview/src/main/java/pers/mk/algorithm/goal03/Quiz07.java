package pers.mk.algorithm.goal03;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz07 {
    public static void main(String[] args) {
        // 各种错误：1. 去除非字母字符的逻辑错误 3. 非连续字符计数逻辑错误
//        mk();


        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim().toLowerCase();

        StringBuilder line = new StringBuilder();
        for (int i = 0 ; i < str.length() ; i++){
            if (str.charAt(i) >= 'a' || str.charAt(i) <= 'z'){
                line.append(str.charAt(i));
            }
        }
        if (line.length() == 0){
            System.out.println();
            return;
        }

        List<String[]> list = new ArrayList<>();

        for (int i = 0 ; i < line.length() ; i++){
            if (list.isEmpty()){
                list.add(new String[]{String.valueOf(line.charAt(i)), String.valueOf(0)});
            }else {
                String key = list.get(list.size() - 1)[0];
                int num = Integer.parseInt(list.get(list.size() - 1)[1]);
                String curWord = String.valueOf(line.charAt(i));

                if (key.equals(curWord) && num == 0 ){
                    list.get(list.size() - 1)[1] = String.valueOf(num + 2);
                }else if (key.equals(curWord) && num > 0 ){
                    list.get(list.size() - 1)[1] = String.valueOf(num + 1);
                } else {
                    list.add(new String[]{curWord, String.valueOf(0)} );
                }

                for (int j = 0 ; j < list.size() - 1 ; j++){
                    String historyWord = list.get(j)[0];
                    if (historyWord.equals(curWord)){
                        int historyNum = Integer.parseInt(list.get(j)[1]);
                        list.get(j)[1] = String.valueOf(historyNum + 1);
                    }
                }

            }
        }

        list.sort((a,b) -> {
            if (a[1].equals(b[1])){
                return a[0].compareTo(b[0]);
            }
            return b[1].compareTo(a[1]);
        });

        for (int i = 0 ; i < list.size() ; i++){
            System.out.print(list.get(i)[0] + list.get(i)[1]);
        }
        System.out.println();
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine().trim().toLowerCase();

        StringBuilder line = new StringBuilder();
        for (int i = 0 ; i < str.length() ; i++){
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z'){
                line.append(c);
            }
        }
        if (line.length() == 0){
            System.out.println();
            return;
        }

        String s = line.toString();
        ArrayList<String[]> list = new ArrayList<>();

        int i = 0 ;
        while (i < s.length()){
            char curChar = s.charAt(i);

            // 是连续的字母
            if (i + 1 < s.length() && s.charAt(i + 1) == curChar){
                int count = 1;
                while (i + 1 < s.length() && s.charAt(i + 1) == curChar){
                    count++;
                    i++;
                }
                list.add(new String[]{String.valueOf(curChar), String.valueOf(count)});
            }else {// 非连续的字母
                int count = 0;
                for (int j = i + 1 ; j < s.length() ; j++){
                    if (s.charAt(j) == curChar){
                        count++;
                    }
                }
                list.add(new String[]{String.valueOf(curChar), String.valueOf(count)});
            }
            i++;
        }

        // 排序
        list.sort((a,b) -> {
            int aNum = Integer.parseInt(a[1]);
            int bNum = Integer.parseInt(b[1]);
            if (aNum != bNum){
                return Integer.compare(bNum,aNum);
            }
            return a[0].compareTo(b[0]);
        });

        StringBuilder result = new StringBuilder();
        for (String[] part : list){
            result.append(part[0]).append(part[1]);
        }
        System.out.println(result);
    }

}
