package pers.mk.interview.jzcw.algorithm;

// 70. 爬楼梯
// 动态规划 DP
// 找规律
public class Quiz12 {


    public static void main(String[] args) {
        // 深度搜索 dfs 会超时
//        System.out.println(mk(45));

        // 动态规划 DP
        // 关于数学题的 要多列举，找规律
        System.out.println(m1(45));
    }


    public static int mk(int n) {
        return dfsMk(n,0,0);
    }

    private static int dfsMk(int n,int total,int m){
        if (total > n){
            return 0;
        }
        if (total == n){
            return 1;
        }
        int m1 = dfsMk(n,total + 1,m);
        int m2 = dfsMk(n,total + 2,m);
        return m1 + m2;
    }


    private static int m1(int n){
        // 找规律
        if (n == 1){
            return 1;
        }else if (n == 2){
            return 2;
        }else if (n == 3){
            return 3;
        }else if (n == 4){
            return 5;
        }else {
            int[] arr = new int[n + 1];
            arr[1] = 1;
            arr[2] = 2;
            for (int i = 3 ; i <= n ; i++){
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            return arr[n];
        }

    }

}
