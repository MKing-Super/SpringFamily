package pers.mk.algorithm.goal07;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz05 {
    public static void main(String[] args) {
        // 不熟悉 01背包问题！！！
//        mk();


        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        int n = Integer.parseInt(split[0]) / 10;
        int m = Integer.parseInt(split[1]);


        List<BigThing> bigThings = new ArrayList<>();
        List<SmallThing> smallThings = new ArrayList<>();

        for (int i = 1 ; i <= m ; i++){
            String[] thing = in.nextLine().trim().split(" ");
            int v = Integer.parseInt(thing[0]) / 10;
            int w = Integer.parseInt(thing[1]);
            int q = Integer.parseInt(thing[2]);
            if (q == 0){
                bigThings.add(new BigThing(i,v,w));
            }else {
                smallThings.add(new SmallThing(i,v,w,q));
            }
        }

        int[] dp = new int[n + 1];

        for (int money = n ; money >= 0 ; money--){
            int totalPrice = 0;
            int totalImportant = 0;
            for (int idx = 0 ; idx < bigThings.size() ; idx++){
                BigThing bigThing = bigThings.get(idx);
                totalPrice += bigThing.price;
                totalImportant += bigThing.important;
                if (n - totalPrice >= 0){
                    int tempImportant = dp[n - totalPrice] + totalImportant;
                    dp[money] = Math.max(dp[money],tempImportant);
                }

            }
        }

        System.out.println(JSON.toJSONString(dp));
        System.out.println(dp[n]);
    }

    private static class BigThing {
        public BigThing(int code, int price, int important) {
            this.code = code;
            this.price = price;
            this.important = important;
        }

        int code;
        int price;
        int important;
        List<SmallThing> smallThingList;
    }

    private static class SmallThing {
        public SmallThing(int code, int price, int important, int bigCode) {
            this.code = code;
            this.price = price;
            this.important = important;
            this.bigCode = bigCode;
        }

        int code;
        int price;
        int important;
        int bigCode;

    }



    private static class Group {
        // 主件价格、重要度
        int mainV;
        int mainW;

        // 附件1价格、重要度（0表示无）
        int attV1;
        int attW1;

        // 附件2价格、重要度（0表示无）
        int attV2;
        int attW2;

        Group(int v, int w) {
            this.mainV = v; this.mainW = w;
        }

        // 添加附件
        void addAtt(int v, int w) {
            if (attV1 == 0) {
                attV1 = v;
                attW1 = w;
            } else {
                attV2 = v;
                attW2 = w;
            }
        }

    }

    private static void m1() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        // 保持原始价格，不要除以10
        int[] prices = new int[m + 1];
        int[] weights = new int[m + 1];
        int[] parent = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            prices[i] = in.nextInt();    // 保持原始价格
            weights[i] = in.nextInt();
            parent[i] = in.nextInt();
        }

        ArrayList<Group> groups = new ArrayList<>();
        int[] mainIndex = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            if (parent[i] == 0) {
                mainIndex[i] = groups.size();
                groups.add(new Group(prices[i], weights[i]));
            }
        }

        for (int i = 1; i <= m; i++) {
            if (parent[i] != 0) {
                int idx = mainIndex[parent[i]];
                groups.get(idx).addAtt(prices[i], weights[i]);
            }
        }

        // DP时再用除以10的价格作为状态
        int maxMoney = n / 10;
        int[] dp = new int[maxMoney + 1];

        for (Group g : groups) {
            for (int money = maxMoney; money >= 0; money--) {
                int cur = dp[money];
                int v, w;

                // 方案1：仅主件
                v = g.mainV / 10;           // 状态用除以10的价格
                w = g.mainV * g.mainW;      // 满意度用原始价格×重要度
                if (money >= v) {
                    cur = Math.max(cur, dp[money - v] + w);
                }

                // 方案2：主件 + 附件1
                if (g.attV1 != 0) {
                    v = (g.mainV + g.attV1) / 10;
                    w = g.mainV * g.mainW + g.attV1 * g.attW1;
                    if (money >= v) {
                        cur = Math.max(cur, dp[money - v] + w);
                    }
                }

                // 方案3：主件 + 附件2
                if (g.attV2 != 0) {
                    v = (g.mainV + g.attV2) / 10;
                    w = g.mainV * g.mainW + g.attV2 * g.attW2;
                    if (money >= v) {
                        cur = Math.max(cur, dp[money - v] + w);
                    }
                }

                // 方案4：主件 + 附件1 + 附件2
                if (g.attV1 != 0 && g.attV2 != 0) {
                    v = (g.mainV + g.attV1 + g.attV2) / 10;
                    w = g.mainV * g.mainW + g.attV1 * g.attW1 + g.attV2 * g.attW2;
                    if (money >= v) {
                        cur = Math.max(cur, dp[money - v] + w);
                    }
                }

                dp[money] = cur;
            }
        }

        System.out.println(dp[maxMoney]);
    }


}
