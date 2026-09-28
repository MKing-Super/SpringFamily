package pers.mk.algorithm.goal02;

import java.util.Arrays;
import java.util.Scanner;


/*

模拟商场优惠打折，有三种优惠券可以用，满减券、打折券和无门槛券。

满减券：满100减10，满200减20，满300减30，满400减40，以此类推不限制使用；
打折券：固定折扣92折，且打折之后向下取整，每次购物只能用1次；
无门槛券：一张券减5元，没有使用限制；

每个人结账使用优惠券时有以下限制：每人每次只能用两种优惠券，并且同一种优惠券必须一次用完，不能跟别的穿插使用（比如用一张满减，再用一张打折，再用一张满减，这种顺序不行）。
求不同使用顺序下每个人用完券之后得到的最低价格和对应使用优惠券的总数；如果两种顺序得到的价格一样低，就取使用优惠券数量较少的那个。

输入描述：
第一行三个数字m,n,k，分别表示每个人可以使用的满减券、打折券和无门槛券的数量
第二行一个数字x, 表示有几个人购物
后面x行数字，依次表示是这几个人打折之前的商品总价

输出描述：
输出每个人使用券之后的最低价格和对应使用优惠券的数量

示例：
输入：
3 2 5
3
100
200
400

输出：
65 6
135 8
275 8

说明:
第一个人使用 1 张满减券和5张无门槛券价格最低。
第二个人使用 3 张满减券和5张无门槛券价格最低。
第二个人使用 3 张满减券和5张无门槛券价格最低。

 */
public class Quiz08 {
    public static void main(String[] args) {
//        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] param1 = in.nextLine().split(" ");
        int m = Integer.parseInt(param1[0]);
        int n = Integer.parseInt(param1[1]);
        int k = Integer.parseInt(param1[2]);
        int x = Integer.parseInt(in.nextLine());
        int[] priceArr = new int[x];
        for (int i = 0 ; i < x ; i++){
            priceArr[i] = Integer.parseInt(in.nextLine());
        }

        for (int i = 0 ; i < x ; i++){
            int tPrice = priceArr[i];
            int tMinPrice = Integer.MAX_VALUE;
            int tMinNumber = 0;
            for (int t1 = 0 ; t1 < 3 ; t1++){
                int[] test = {};
                if (t1 == 0){
                    test = test(m, n, null, tPrice);
                }else if (t1 == 1){
                    test = test(m, null, k, tPrice);
                }else if (t1 == 2){
                    test = test(null, n, k, tPrice);
                }
                int t1Used = test[0];
                int t1Price = test[1];
                if (t1Price < tMinPrice){
                    tMinPrice = t1Price;
                    tMinNumber = t1Used;
                }else if (t1Price == tMinPrice){
                    if (t1Used < tMinNumber){
                        tMinPrice = t1Price;
                        tMinNumber = t1Used;
                    }
                }

            }
            System.out.println(tMinPrice + " " + tMinNumber);
        }

    }


    private static int[] test(Integer m,Integer n,Integer k,Integer price){
        if (m != null && n != null){
            int a = m;
            int b = n;
            // a -> b
            int mUsed1 = 0;
            int mPrice1 = 0;
            int nUsed1 = 0;
            int nPrice1 = 0;
            int[] mDeal1 = mDeal(a, price);
            mUsed1 = mDeal1[0];
            mPrice1 = mDeal1[1];
            int[] nDeal1 = nDeal(b, mPrice1);
            nUsed1 = nDeal1[0];
            nPrice1 = nDeal1[1];
            int used1 = mUsed1 + nUsed1;
            int price1 = nPrice1;

            // b -> a
            int mUsed2 = 0;
            int mPrice2 = 0;
            int nUsed2 = 0;
            int nPrice2 = 0;
            int[] nDeal2 = nDeal(b, price);
            nUsed2 = nDeal2[0];
            nPrice2 = nDeal2[1];
            int[] mDeal2 = mDeal(a, nPrice2);
            mUsed2 = mDeal2[0];
            mPrice2 = mDeal2[1];
            int used2 = mUsed2 + nUsed2;
            int price2 = mPrice2;

            if (price1 < price2){
                return new int[]{used1,price1};
            }else if (price1 > price2){
                return new int[]{used2,price2};
            }else {
                if (used1 < used2){
                    return new int[]{used1,price1};
                }else if (used1 > used2){
                    return new int[]{used2,price2};
                }else {
                    return new int[]{used1,price1};
                }
            }
        }

        if (m != null && k != null ){
            // m -> n
            int mUsed1 = 0;
            int mPrice1 = 0;
            int nUsed1 = 0;
            int nPrice1 = 0;
            int[] mDeal1 = mDeal(m, price);
            mUsed1 = mDeal1[0];
            mPrice1 = mDeal1[1];
            int[] nDeal1 = kDeal(k, mPrice1);
            nUsed1 = nDeal1[0];
            nPrice1 = nDeal1[1];
            int used1 = mUsed1 + nUsed1;
            int price1 = nPrice1;

            // n -> m
            int mUsed2 = 0;
            int mPrice2 = 0;
            int nUsed2 = 0;
            int nPrice2 = 0;
            int[] nDeal2 = kDeal(k, price);
            nUsed2 = nDeal2[0];
            nPrice2 = nDeal2[1];
            int[] mDeal2 = mDeal(m, nPrice2);
            mUsed2 = mDeal2[0];
            mPrice2 = mDeal2[1];
            int used2 = mUsed2 + nUsed2;
            int price2 = mPrice2;

            if (price1 < price2){
                return new int[]{used1,price1};
            }else if (price1 > price2){
                return new int[]{used2,price2};
            }else {
                if (used1 < used2){
                    return new int[]{used1,price1};
                }else if (used1 > used2){
                    return new int[]{used2,price2};
                }else {
                    return new int[]{used1,price1};
                }
            }
        }

        if (n != null && k != null){
            // m -> n
            int mUsed1 = 0;
            int mPrice1 = 0;
            int nUsed1 = 0;
            int nPrice1 = 0;
            int[] mDeal1 = nDeal(n, price);
            mUsed1 = mDeal1[0];
            mPrice1 = mDeal1[1];
            int[] nDeal1 = kDeal(k, mPrice1);
            nUsed1 = nDeal1[0];
            nPrice1 = nDeal1[1];
            int used1 = mUsed1 + nUsed1;
            int price1 = nPrice1;

            // n -> m
            int mUsed2 = 0;
            int mPrice2 = 0;
            int nUsed2 = 0;
            int nPrice2 = 0;
            int[] nDeal2 = kDeal(k, price);
            nUsed2 = nDeal2[0];
            nPrice2 = nDeal2[1];
            int[] mDeal2 = nDeal(n, nPrice2);
            mUsed2 = mDeal2[0];
            mPrice2 = mDeal2[1];
            int used2 = mUsed2 + nUsed2;
            int price2 = mPrice2;

            if (price1 < price2){
                return new int[]{used1,price1};
            }else if (price1 > price2){
                return new int[]{used2,price2};
            }else {
                if (used1 < used2){
                    return new int[]{used1,price1};
                }else if (used1 > used2){
                    return new int[]{used2,price2};
                }else {
                    return new int[]{used1,price1};
                }
            }
        }
        return new int[]{};
    }

    private static int[] mDeal(Integer m,Integer price){
        int mUsed = 0;
        while (m > 0){
            if (price >= 400){
                m--;
                price -= 40;
                mUsed++;
            }else if (price >= 300){
                m--;
                price -= 30;
                mUsed++;
            }else if (price >= 200){
                m--;
                price -= 20;
                mUsed++;
            }else if (price >= 100){
                m--;
                price -= 10;
                mUsed++;
            }else {
                break;
            }
        }
        return new int[]{mUsed,price};
    }

    private static int[] nDeal(Integer n,Integer price){
        if (n > 0){
            int priceSY = (int)(price * 0.92);
            return new int[]{1,priceSY};
        }
        return new int[]{0,price};
    }

    private static int[] kDeal(Integer k,Integer price){
        int kUsed = 0;
        while (k > 0){
            k--;
            price -= 5;
            kUsed++;
            if (price <= 0){
                price = 0;
                break;
            }
        }
        return new int[]{kUsed,price};
    }






    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] param = in.nextLine().split(" ");
        int m = Integer.parseInt(param[0]);
        int n = Math.min(Integer.parseInt(param[1]), 1); // 打折券每次只用1次
        int k = Integer.parseInt(param[2]);

        int x = Integer.parseInt(in.nextLine());
        for (int i = 0; i < x; i++) {
            int price = Integer.parseInt(in.nextLine());

            int bestPrice = Integer.MAX_VALUE;
            int bestUsed = 0;

            // 三种两券组合
            int[][] candidates = {
                    twoTypeCombo(m, 0, n, price),   // 满减+打折
                    twoTypeCombo(m, k, 0, price),   // 满减+无门槛
                    twoTypeCombo(0, k, n, price)    // 无门槛+打折
            };

            for (int[] c : candidates) {
                int used = c[0];
                int finalPrice = c[1];
                if (finalPrice < bestPrice) {
                    bestPrice = finalPrice;
                    bestUsed = used;
                } else if (finalPrice == bestPrice && used < bestUsed) {
                    bestUsed = used;
                }
            }

            System.out.println(bestPrice + " " + bestUsed);
        }
    }

    /**
     * 只使用两种券：fullCount 满减张数，noThresholdCount 无门槛张数，discountCount 打折张数(0或1)
     * 其中恰好两类 >0，一类 ==0
     */
    private static int[] twoTypeCombo(int fullCount, int noThresholdCount, int discountCount, int price) {
        int bestPrice = Integer.MAX_VALUE;
        int bestUsed = 0;

        // 满减 + 打折
        if (fullCount > 0 && discountCount > 0) {
            int[] a1 = applyFull(fullCount, price);
            int[] a2 = applyDiscount(discountCount, a1[1]);
            bestPrice = a2[1]; bestUsed = a1[0] + a2[0];

            int[] b1 = applyDiscount(discountCount, price);
            int[] b2 = applyFull(fullCount, b1[1]);
            if (b2[1] < bestPrice || (b2[1] == bestPrice && b2[0] + b1[0] < bestUsed)) {
                bestPrice = b2[1]; bestUsed = b2[0] + b1[0];
            }
        }

        // 满减 + 无门槛
        if (fullCount > 0 && noThresholdCount > 0) {
            int[] a1 = applyFull(fullCount, price);
            int[] a2 = applyNoThreshold(noThresholdCount, a1[1]);
            if (a2[1] < bestPrice || (a2[1] == bestPrice && a1[0] + a2[0] < bestUsed)) {
                bestPrice = a2[1]; bestUsed = a1[0] + a2[0];
            }
            int[] b1 = applyNoThreshold(noThresholdCount, price);
            int[] b2 = applyFull(fullCount, b1[1]);
            if (b2[1] < bestPrice || (b2[1] == bestPrice && b2[0] + b1[0] < bestUsed)) {
                bestPrice = b2[1]; bestUsed = b2[0] + b1[0];
            }
        }

        // 无门槛 + 打折
        if (noThresholdCount > 0 && discountCount > 0) {
            int[] a1 = applyNoThreshold(noThresholdCount, price);
            int[] a2 = applyDiscount(discountCount, a1[1]);
            if (a2[1] < bestPrice || (a2[1] == bestPrice && a1[0] + a2[0] < bestUsed)) {
                bestPrice = a2[1]; bestUsed = a1[0] + a2[0];
            }
            int[] b1 = applyDiscount(discountCount, price);
            int[] b2 = applyNoThreshold(noThresholdCount, b1[1]);
            if (b2[1] < bestPrice || (b2[1] == bestPrice && b2[0] + b1[0] < bestUsed)) {
                bestPrice = b2[1]; bestUsed = b2[0] + b1[0];
            }
        }

        return new int[] { bestUsed, bestPrice };
    }

    private static int[] applyFull(int count, int price) {
        int used = 0;
        while (count > 0) {
            int cut;
            if (price >= 400) cut = 40;
            else if (price >= 300) cut = 30;
            else if (price >= 200) cut = 20;
            else if (price >= 100) cut = 10;
            else break;
            price -= cut;
            used++;
            count--;
        }
        return new int[] { used, price };
    }

    private static int[] applyDiscount(int count, int price) {
        if (count > 0) {
            int after = (int) (price * 0.92);
            return new int[] { 1, after };
        }
        return new int[] { 0, price };
    }

    private static int[] applyNoThreshold(int count, int price) {
        int used = 0;
        while (count > 0 && price > 0) {
            price -= 5;
            if (price < 0) price = 0;
            used++;
            count--;
        }
        return new int[] { used, price };
    }






}
