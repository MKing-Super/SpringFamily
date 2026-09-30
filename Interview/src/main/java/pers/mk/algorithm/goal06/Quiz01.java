package pers.mk.algorithm.goal06;

import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {
//        mk();
        //
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] str1Arr  = in.nextLine().split(" ");
        String[] str2Arr  = in.nextLine().split(" ");
        int n = Integer.parseInt(str1Arr[0]);
        int t = Integer.parseInt(str1Arr[1]);
        int k = Integer.parseInt(str1Arr[2]);
        int[] nArr = new int[n];
        for (int i = 0 ; i < n ; i++){
            nArr[i] = Integer.parseInt(str2Arr[i]);
        }

        int total = 0;
        for (int i = 0 ; i < n - 1 ; i++){
            int lay1Count = 1;
            for (int j = i + 1  ; j < n ; j++){
                int lay2Count = 1;
                if (nArr[i] + nArr[j] == t && lay1Count + lay2Count == k){
                    total ++;
                }
            }
        }

        System.out.println(total);
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] str1Arr  = in.nextLine().split(" ");
        String[] str2Arr  = in.nextLine().split(" ");
        int n = Integer.parseInt(str1Arr[0]);
        int t = Integer.parseInt(str1Arr[1]);
        int k = Integer.parseInt(str1Arr[2]);
        int[] nArr = new int[n];
        for (int i = 0 ; i < n ; i++){
            nArr[i] = Integer.parseInt(str2Arr[i]);
        }
        System.out.println(loop(nArr,n,t,k,0,0,0));
    }

    private static int loop(int[] nArr,int n,int t,int k,int left,int count,int sum){
        if (sum > t || count > k){
            return 0;
        }

        if (sum == t && count == k){
            return 1;
        }

        if (left >= n){
            return 0;
        }

        int ways = 0;

        ways += loop(nArr,n,t,k,left + 1,count + 1,sum + nArr[left]);
        ways += loop(nArr,n,t,k,left + 1,count,sum);

        return ways;
    }


}
