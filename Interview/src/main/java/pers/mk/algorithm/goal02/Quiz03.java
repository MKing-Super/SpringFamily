package pers.mk.algorithm.goal02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*

设定一组四码的数字作为谜底，另一方猜每猜一个数，出数者就要根据这个数字给出提示，提示以XAYB形式呈现，直到猜中位置。

其中X表示位置正确的数的个数(数字正确且位置正确)，而Y表示数字正确而位置不对的数的个数例如，当谜底为8123，而猜谜者猜1052时，出题者必须提示0A2B。例如，当谜底为5637，而猜谜者猜4931时，出题者必须提示1A0B。当前已知N组猜谜者猜的数字与提示，如果答案确定，请输出答案，不确定则输出NA。

输入描述
第一行输入一个正整数，0< N < 100.
接下来N行，每一行包含一猜测的数字与提示结果

输出描述
输出最后的答案，答案不确定则输出NA。

示例1：
输入
6
4815 1A1B
5716 0A1B
7842 0A1B
4901 0A0B
8585 3A0B
8555 2A1B

输出
3585

 */
public class Quiz03 {
    public static void main(String[] args) {
        // 思路完全错误，要使用枚举法对4位数数字进行比对破解
//        mk();
        // 算法解析
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        Integer n = Integer.parseInt(in.nextLine().trim());
        List<Content> list = new ArrayList<>();
        for (int i = 0 ; i < n ; i++){
            String[] str = in.nextLine().trim().split(" ");
            int ntpt_0 = Integer.parseInt(str[1].trim().substring(0,1));
            int ntpt_3 = Integer.parseInt(str[1].trim().substring(2,3));
            list.add(new Content(str[0].trim(),ntpt_0,ntpt_3));
        }

        List<String> candidates = new ArrayList<>();
        for (Integer i = 1000 ; i < 10000 ; i++){
            candidates.add(i.toString());
        }

        ArrayList<String> newCandidates = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++){
            Content cur = list.get(i);



            for (int t1 = 0 ; t1 < candidates.size() ; t1++){

                for (int t2 = 0 ; t2 < 4 ; t2++){
                    if (cur.number.charAt(t2) == candidates.get(t1).charAt(t2)){
                        newCandidates.add(candidates.get(t1));
                    }
                }



            }

        }




    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        List<String> gussList = new ArrayList<>();
        List<int[]> gussInfoList = new ArrayList<>();
        for (int i = 0 ; i < n ; i++){
            String[] str = in.nextLine().split(" ");
            int x = Integer.parseInt(str[1].substring(0,1));
            int y = Integer.parseInt(str[1].substring(2,3));
            gussList.add(str[0]);
            gussInfoList.add(new int[]{x,y});
        }

        List<String> candidateList = new ArrayList<>();
        for (int i = 1000 ; i <= 9999 ; i++){
            String cand = String.valueOf(i);
            boolean pass = true;
            for (int j = 0 ; j < gussList.size() ; j++ ){
                if (!checkMethod(cand,gussList.get(j),gussInfoList.get(j)[0],gussInfoList.get(j)[1]) ){
                    pass = false;
                    break;
                }
            }
            if (pass){
                candidateList.add(cand);
            }
        }

        if (candidateList.size() == 1){
            System.out.println(candidateList.get(0));
        }else {
            System.out.println("NA");
        }


    }


    private static boolean checkMethod(String cand,String guss,int x,int y){
        int[] cArr = new int[10];
        int[] gArr = new int[10];

        int A = 0;
        int B = 0;

        for(int i = 0 ; i < 4 ; i++){
            if (cand.charAt(i) == guss.charAt(i)){
                A++;
            }
            cArr[Integer.parseInt(String.valueOf(cand.charAt(i)))]++;
            gArr[Integer.parseInt(String.valueOf(guss.charAt(i)))]++;
        }
        int total = 0;
        for (int i = 0 ; i < 10 ; i++){
            total += Math.min(cArr[i],gArr[i]);
        }
        B = total - A;
        return x == A && y == B;
    }

}

class Content {
    String number;
    int ntpt_0;
    int ntpf_2;

    public Content(String number, int ntpt_0, int ntpf_2) {
        this.number = number;
        this.ntpt_0 = ntpt_0;
        this.ntpf_2 = ntpf_2;
    }
}
