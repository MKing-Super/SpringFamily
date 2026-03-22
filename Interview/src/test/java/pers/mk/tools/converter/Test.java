package pers.mk.tools.converter;

public class Test {
    public static void main(String[] args) {
        // 例子：从5个人中选3个人排队，有多少种排法？
        System.out.println("P(5, 3) = " + permutation(5, 3));  // 60
        System.out.println("---------------------");

        // 例子：从3个人中选3个人排队
        System.out.println("P(3, 3) = " + permutation(3, 3));  // 6
    }

    /**
     * 计算排列数 P(n, k) = n! / (n-k)!
     * 从n个不同元素中取出k个元素的所有排列的个数
     */
    public static long permutation(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            return 0;
        }

        long result = 1;
        for (int i = 0; i < k; i++) {
            System.out.println("result -> "+result+"; (n - i) -> "+(n-i));
            result = result * (n - i);
        }
        return result;
    }
}
