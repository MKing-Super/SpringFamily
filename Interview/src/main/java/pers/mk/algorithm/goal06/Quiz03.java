package pers.mk.algorithm.goal06;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Quiz03 {
    public static void main(String[] args) {
        mk();
        m1();
    }

    private static void mk(){
//        "ABCABBA";
//        "CBABAC";

//
//        "AABCABBA0";
//        "0CB0AB0AC";

        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(" ");
        String[] mArr = str[0].split("");
        String[] nArr = str[1].split("");
        List<String> mList = new LinkedList<>();
        List<String> nList = new LinkedList<>();
        mList.addAll(Arrays.asList(mArr));
        nList.addAll(Arrays.asList(nArr));

        int index = 0;
        while (true){
            System.out.println(JSON.toJSONString(mList));
            System.out.println(JSON.toJSONString(nList));
            System.out.println("---------------------------------");
            if (mList.get(index).equals("0") && nList.get(index).equals("0") ){
                break;
            }
            if (index < mList.size() && index < nList.size()){
                if (!mList.get(index).equals(nList.get(index)) && index == 0 ){
                    mList.add(index,mList.get(index));
                    nList.add(index,"0");
                    index += 2;
                    continue;
                }

                if (mList.get(index).equals(nList.get(index)) ){
                    index ++;

                }

                if (!mList.get(index).equals(nList.get(index)) ){
                    if (mList.size() > nList.size()){
                        nList.add(index,"0");
                    }else if (mList.size() < nList.size()){
                        mList.add(index,"0");
                    }

                    index ++;

                }
            }else if (index == mList.size() && index < nList.size()){
                mList.add("0");
                if (mList.get(index).equals(nList.get(index)) ){
                    index ++;

                }

                if (!mList.get(index).equals(nList.get(index)) ){
                    if (mList.size() > nList.size()){
                        nList.add(index,"0");
                    }else if (mList.size() < nList.size()){
                        mList.add(index,"0");
                    }
                    index ++;

                }
            }else if (index < mList.size() && index == nList.size()){
                nList.add("0");
                if (mList.get(index).equals(nList.get(index)) ){
                    index ++;

                }

                if (!mList.get(index).equals(nList.get(index)) ){
                    if (mList.size() > nList.size()){
                        nList.add(index,"0");
                    }else if (mList.size() < nList.size()){
                        mList.add(index,"0");
                    }
                    index ++;

                }
            }else {
                break;
            }


        }

        System.out.println(mList.size());
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(" ");
        String A = str[0];
        String B = str[1];

        int m = A.length();
        int n = B.length();

        // 递推计算LCS长度
        int lcsLength = computeLCS(A, B, m, n);

        // 最短距离 = m + n - LCS长度
        int shortestDistance = m + n - lcsLength;
        System.out.println(shortestDistance);
    }

    // 纯递推方式计算LCS
    private static int computeLCS(String A, String B, int m, int n) {
        // dp[i][j] 表示 A的前i个字符 和 B的前j个字符 的LCS长度
        int[][] dp = new int[m + 1][n + 1];

        // 初始化：dp[0][*] = 0, dp[*][0] = 0
        // Java中int数组默认就是0，所以这一步可以省略

        // 递推填充整个表格
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    // 最后一个字符相同：左上角 + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 最后一个字符不同：取左边和上边的较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }



    static String A, B;
    static int m, n;
    static int[][] memo;
    private static void m2(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(" ");
        A = str[0];
        B = str[1];
        m = A.length();
        n = B.length();

        // 记忆化数组，-1表示未计算
        memo = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = -1;
            }
        }

        int result = dfs(0, 0);
        System.out.println(result);
    }

    // DFS：从 (i,j) 到 (m,n) 的最短距离
    private static int dfs(int i, int j) {
        // 到达终点
        if (i == m && j == n) {
            return 0;
        }

        // 超出边界
        if (i > m || j > n) {
            return Integer.MAX_VALUE;
        }

        // 记忆化
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int minDist = Integer.MAX_VALUE;

        // 1. 向右走（跳过A的一个字符）
        int right = 1 + dfs(i + 1, j);
        minDist = Math.min(minDist, right);

        // 2. 向下走（跳过B的一个字符）
        int down = 1 + dfs(i, j + 1);
        minDist = Math.min(minDist, down);

        // 3. 斜着走（如果字符匹配）
        if (i < m && j < n && A.charAt(i) == B.charAt(j)) {
            int diagonal = 1 + dfs(i + 1, j + 1);
            minDist = Math.min(minDist, diagonal);
        }

        memo[i][j] = minDist;
        return minDist;
    }


}
