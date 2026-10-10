package pers.mk.algorithm.goal03;

import java.util.Scanner;

public class Quiz08 {
    public static void main(String[] args) {
        // 思路对，当可能有瑕疵！
//        mk();


        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int K = Integer.parseInt(in.nextLine().trim());
        String[] strArr = in.nextLine().trim().split("-");

        String str = strArr[0] + "-";
        int left = str.length();
        for (int i = 1 ; i < strArr.length ; i++){
            str += strArr[i];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0,left));
        while (left < str.length()){
            int diff = str.length() - left;
            if (diff > K){
                sb.append(str.substring(left,left + K).toUpperCase() ).append("-");
                left = left + K;
            }else {
                sb.append(str.substring(left,left + diff).toUpperCase() );
                left = left + diff;
            }
        }
        System.out.println(sb);
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int K = Integer.parseInt(in.nextLine().trim());
        String[] strArr = in.nextLine().trim().split("-");

        // 第一个子串
        String first = strArr[0];

        // 其余子串拼接
        StringBuilder rest = new StringBuilder();
        for (int i = 1 ; i < strArr.length ; i++){
            rest.append(strArr[i]);
        }
        String resStr = rest.toString();

        // 构建结果
        StringBuilder sb = new StringBuilder();
        sb.append(first);

        int index = 0;
        while (index < resStr.length()){
            sb.append("-");
            int end = Math.min(index + K , resStr.length());
            sb.append(resStr.substring(index,end).toUpperCase() );
            index = end;
        }
        System.out.println(sb.toString());
    }

}
