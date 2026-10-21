package pers.mk.algorithm.goal05;

import java.util.Scanner;

public class Quiz06 {
    public static void main(String[] args) {
        // DFS解法会超时（指数级复杂度），应该用前缀和 + 滑动窗口的优化方法。
//        mk();


        // 思路：无论怎么选，最终一定是左边取k个，右边取N-k个，中间的连续一段没有被取到。
        // 我们只需要枚举左边取的个数即可。
        m1();
    }



    private static int[] numbers;
    private static int maxNum = 0;
    private static void mk(){
        Scanner in = new Scanner(System.in);
        int len = Integer.parseInt(in.nextLine().trim());
        String[] strArr = in.nextLine().trim().split(" ");
        int N = Integer.parseInt(in.nextLine().trim());
        if (N == 0){
            System.out.println(0);
            return;
        }
        if (len != strArr.length){
            return;
        }
        numbers = new int[len];
        int total = 0;
        for (int i = 0 ; i < len ; i++){
            int t = Integer.parseInt(strArr[i]);
            numbers[i] = t;
            total += t;
        }
        if (N >= len){
            System.out.println(total);
            return;
        }
        dfsMk(0,len - 1,N,0);
        System.out.println(maxNum);
    }

    private static void dfsMk(int left,int right,int n,int total){
        if (n <= 0){
            if (total > maxNum){
                maxNum = total;
            }
            return;
        }
        dfsMk(left + 1 , right,n - 1,total + numbers[left]);
        dfsMk(left , right - 1,n - 1,total + numbers[right]);
    }





    private static void m1(){
        Scanner in = new Scanner(System.in);
        int len = Integer.parseInt(in.nextLine().trim());
        String[] strArr = in.nextLine().trim().split(" ");
        int N = Integer.parseInt(in.nextLine().trim());

        if (N == 0){
            System.out.println(0);
            return;
        }

        int[] numbers = new int[len];
        for (int i = 0 ; i < len ; i++){
            numbers[i] = Integer.parseInt(strArr[i]);
        }

        if (N >= len){
            int total = 0 ;
            for (int num : numbers){
                total += num;
            }
            System.out.println(total);
            return;
        }

        int[] prefixSum = new int[len + 1];
        StringBuilder sb = new StringBuilder();
        sb.append(prefixSum[0]).append(" ");
        for (int i = 0 ; i < len ; i++){
            prefixSum[i + 1] = prefixSum[i] + numbers[i];
            sb.append(prefixSum[i + 1]).append(" ");
        }
//        System.out.println(sb);

        int totalBananas = prefixSum[len];
        int maxGet = 0;

        for (int leftCount = 0 ; leftCount <= N ; leftCount++){
            int rightCount = N - leftCount;

            int midStart = leftCount;
            int midEnd = len - rightCount - 1;

            if (midStart > midEnd){
                maxGet = totalBananas;
                break;
            }

            int midSum = prefixSum[midEnd + 1] - prefixSum[midStart];
            int get = totalBananas - midSum;
            if (get > maxGet){
                maxGet = get;
            }
        }

        System.out.println(maxGet);
    }

}
