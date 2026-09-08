package pers.mk.interview.hw;

import com.alibaba.fastjson.JSON;

import java.util.Scanner;

public class HJ016 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();          // 总预算
        int m = in.nextInt();          // 物品总数

        int[] price = new int[m];
        int[] importance = new int[m];
        int[] master = new int[m];

        // 记录每个主件的附件索引，最多两个，-1 表示不存在
        int[][] attachments = new int[m + 1][2]; // 下标使用主件编号（1~m）
        for (int i = 1; i <= m; i++) {
            attachments[i][0] = -1;
            attachments[i][1] = -1;
        }

        // 读取所有物品
        for (int i = 0; i < m; i++) {
            price[i] = in.nextInt();
            importance[i] = in.nextInt();
            master[i] = in.nextInt();
            if (master[i] != 0) {
                // 当前物品是附件，加入对应主件的附件列表
                int mainId = master[i];
                if (attachments[mainId][0] == -1) {
                    attachments[mainId][0] = i;
                } else {
                    attachments[mainId][1] = i;
                }
            }
        }

        int[] dp = new int[n + 1];

        // 只处理主件
        for (int i = 0; i < m; i++) {
            if (master[i] != 0) continue; // 跳过附件

            int mainPrice = price[i];
            int mainValue = price[i] * importance[i];

            // 获取该主件的附件索引
            int attIdx1 = attachments[i + 1][0]; // i+1 即主件编号
            int attIdx2 = attachments[i + 1][1];

            // 附件的价格和价值，若不存在则设为 0（不会影响背包更新）
            int price1 = (attIdx1 != -1) ? price[attIdx1] : 0;
            int value1 = (attIdx1 != -1) ? price[attIdx1] * importance[attIdx1] : 0;
            int price2 = (attIdx2 != -1) ? price[attIdx2] : 0;
            int value2 = (attIdx2 != -1) ? price[attIdx2] * importance[attIdx2] : 0;

            // 分组背包逆序更新
            for (int j = n; j >= 0; j--) {
                // 只买主件
                if (j >= mainPrice) {
                    dp[j] = Math.max(dp[j], dp[j - mainPrice] + mainValue);
                }
                // 主件 + 附件1
                if (attIdx1 != -1 && j >= mainPrice + price1) {
                    dp[j] = Math.max(dp[j], dp[j - mainPrice - price1] + mainValue + value1);
                }
                // 主件 + 附件2
                if (attIdx2 != -1 && j >= mainPrice + price2) {
                    dp[j] = Math.max(dp[j], dp[j - mainPrice - price2] + mainValue + value2);
                }
                // 主件 + 附件1 + 附件2
                if (attIdx1 != -1 && attIdx2 != -1 && j >= mainPrice + price1 + price2) {
                    dp[j] = Math.max(dp[j], dp[j - mainPrice - price1 - price2] + mainValue + value1
                            + value2);
                }
            }
            System.out.println("----- i = " + i);
            System.out.println(JSON.toJSONString(dp));

        }

        System.out.println(dp[n]);
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
