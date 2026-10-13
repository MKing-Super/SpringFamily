package pers.mk.algorithm.goal04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz05 {
    public static void main(String[] args) {
        // 如果压缩的字符串展开后长度为 1亿，那 jvm 会抛出 oom ！！！
//        mk();

        // 不用展开计算的方法
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String beforeStr = in.nextLine().trim();
        String afterStr = in.nextLine().trim();

        beforeStr = convertMk(beforeStr);
        afterStr = convertMk(afterStr);

        int totalLen = beforeStr.length();
        int errorNum = 0;
        for (int i = 0 ; i < beforeStr.length() ; i++){
            if (beforeStr.charAt(i) != afterStr.charAt(i) ){
                errorNum++;
            }
        }
        System.out.println(errorNum + "/" + totalLen);
    }

    private static String convertMk(String beforeStr ){
        int left = 0;
        List<String[]> list = new ArrayList<>();
        int index = 0;
        while (true){
            list.add(new String[2]);
            if (beforeStr.charAt(left) >= '0' && beforeStr.charAt(left) <= '9'  ){
                String temp = "";
                while (beforeStr.charAt(left) >= '0' && beforeStr.charAt(left) <= '9'){
                    temp += beforeStr.charAt(left);
                    left++;
                }
                list.get(index)[0] = temp;
            }
            if (Character.toLowerCase(beforeStr.charAt(left)) >= 'a' && Character.toLowerCase(beforeStr.charAt(left)) <= 'z' ){
                list.get(index)[1] = String.valueOf(beforeStr.charAt(left));
                left++;
            }
            index++;
            if (beforeStr.length() <= left){
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < list.size() ; i++){
            int len = Integer.parseInt(list.get(i)[0]);
            String word = list.get(i)[1];
            for (int j = 0 ; j < len ; j++){
                sb.append(word);
            }
        }
        return sb.toString();
    }




    private static void m1(){
        Scanner in = new Scanner(System.in);
        String beforeStr = in.nextLine().trim();
        String afterStr = in.nextLine().trim();

        // 不实际展开字符串，而是逐个字符比较
        long totalLen = 0;
        long errorNum = 0;

        // 解析两个字符串并同时比较
        int i = 0, j = 0; // 分别指向两个字符串的当前位置
        long remainI = 0, remainJ = 0; // 当前重复次数剩余
        char charI = 0, charJ = 0; // 当前字符

        while (i < beforeStr.length() || j < afterStr.length()) {
            // 获取before的下一个字符
            if (remainI == 0 && i < beforeStr.length()) {
                // 读取数字
                int num = 0;
                while (i < beforeStr.length() && Character.isDigit(beforeStr.charAt(i))) {
                    num = num * 10 + (beforeStr.charAt(i) - '0');
                    i++;
                }
                remainI = num;
                charI = beforeStr.charAt(i);
                i++;
            }

            // 获取after的下一个字符
            if (remainJ == 0 && j < afterStr.length()) {
                int num = 0;
                while (j < afterStr.length() && Character.isDigit(afterStr.charAt(j))) {
                    num = num * 10 + (afterStr.charAt(j) - '0');
                    j++;
                }
                remainJ = num;
                charJ = afterStr.charAt(j);
                j++;
            }

            // 比较当前字符
            long minRemain = Math.min(remainI, remainJ);
            totalLen += minRemain;

            if (charI != charJ) {
                errorNum += minRemain;
            }

            remainI -= minRemain;
            remainJ -= minRemain;
        }

        System.out.println(errorNum + "/" + totalLen);
    }


}
