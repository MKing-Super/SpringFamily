package pers.mk.algorithm.goal02;

import java.math.BigDecimal;
import java.util.*;

/*

在一个采购系统中，采购申请(PR)需要经过审批后才能生成采购订单(PO)。每个PR包含商品的单价(假设相同商品的单价一定是一样的)及数量信息。系统要求对商品进行分类处理:单价高于100元的商品需要单独处理，单价低于或等于100元的相同商品可以合并到同一采购订单PO中。针对单价低于100的小额订单，如果量大可以打折购买。
具体规则如下:
如果PR状态为"审批通过"，则将其商品加入到PO中。如果PR的状态为"审批拒绝"或"待审批"，则忽略改PR,对于单价高于100元的商品、每个商品单独生成一条PO记录。对于单价低于100元的商品，将相同商品的数量合并四到一条PO记录中。如果商品单价<100且商品数量>=100，则单价打9折。
输入描述
第一行包含整数N，表示PR的数量。
接下来N行，每行包含四个用空格分割的整数，按顺序表示:商品ID,数量，单价，PR状态(0表示审批通过，1表示审批拒绝，2表示待审批)
输出描述
输出若干行，每行表示一条PO记录，按以下格式输出:
对于单价高于100元的商品:商品ID 数量 单价
对于单价低于或等于100元的商品:商品ID 总数量 打折后的单价(向上取整)输出的PO记录按商品ID升序升序排列，相同商品按照数量降序排列
补充
2<=n<= 1000
1<= 商品价格 <= 200
1 <= 商品数量 <= 1000
2<= 商品编号 <= 1000

示例1：
输入
2
1 200 90 0
2 30 101 0

输出
1 200 81
2 30 101

说明：
商品1的原始单价为90，审批通过，生成一条PO，满足打折条件，打折后单价为81。商品2的单价为101，审批通过，生成一条PO

示例2：
输入
3
1 10 90 0
1 5 90 0
2 8 120 0

输出
1 15 90
2 8 120

说明：
PR1和PR2均为商品1，单价90，审批通过，单价低于100元，合并数量为150.PR3为商品2，单价120元，审批通过，单价高于100元，单独生成一条PO记录

示例3：
输入
4
1 5 80 0
2 3 120 0
3 2 90 1
4 10 150 2

输出
1 5 80
2 3 120

说明：
PR1:商品1，单价80元，审批通过，单价低于100元，合并到PO中。
PR2:商品2，单价120元，审批通过，单价高于100元，单独生成一条PO记录。PR3:审批拒绝，忽略。PR4待审批忽略。


 */
public class Quiz01 {
    public static void main(String[] args) {
        // 原算法不正确。最主要的错误是把单价高于 100 元的商品也按 ID 合并了数量并只生成一条记录，违背了“高价商品单独生成 PO”的规则，同时也导致无法满足“同商品按数量降序”的排序要求
        mk();
        // https://jishuzhan.net/article/1987378440350269442
//        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[][] arr = new int[1001][3];

        for(int i = 1 ; i <= n ; i++){
            String[] s = in.nextLine().split(" ");
            int orderId = Integer.parseInt(s[0]);
            int number = Integer.parseInt(s[1]);
            int price = Integer.parseInt(s[2]);
            int status = Integer.parseInt(s[3]);
            if (status == 0){
                arr[orderId][0] = orderId;
                arr[orderId][1] = number == 0 ? number : arr[orderId][1] + number;
                arr[orderId][2] = price;
            }
        }

        for (int i = 0 ; i < arr.length ; i++){
            if (arr[i][0] == 0){
                continue;
            }
            if (arr[i][1] >= 100 && arr[i][2] < 100){
                double v = arr[i][2] * 0.9;
                int v2 = (int)(arr[i][2] * 0.9);
                if (v > (double) v2){
                    arr[i][2] = v2 + 1;
                }else {
                    arr[i][2] = v2;
                }
            }
            System.out.println(arr[i][0] + " " + arr[i][1] + " " + arr[i][2]);
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());

        List<PO> list = new ArrayList<>();
        Map<Integer,Integer> lowNumber = new HashMap<>();
        Map<Integer,Integer> lowPrice = new HashMap<>();
        for (int i = 0 ; i < n ; i++){
            String[] str = in.nextLine().trim().split(" ");
            int id = Integer.parseInt(str[0]);
            int number = Integer.parseInt(str[1]);
            int price = Integer.parseInt(str[2]);
            int status = Integer.parseInt(str[3]);

            if (status != 0){
                continue;
            }

            if (price > 100){
                list.add(new PO(id,number,price));
            }else {
                lowNumber.putIfAbsent(id, 0);
                lowNumber.put(id,lowNumber.get(id) + number);
                lowPrice.put(id,price);
            }
        }

        for (Integer id : lowNumber.keySet()){
            int number = lowNumber.get(id);
            int price = lowPrice.get(id);
            if (number > 100 && price < 100){
                price = (int)Math.ceil(price * 0.9);
            }
            list.add(new PO(id,number,price));
        }

        list.sort((a,b) -> {
            if (a.id != b.id){
                return Integer.compare(a.id,b.id);
            }
            return Integer.compare(b.number,a.number);
        });

        for (PO t : list){
            System.out.println(t.id + " " + t.number + " " + t.price);
        }

    }

}

class PO {
    int id;
    int number;
    int price;

    public PO(int id, int number, int price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }
}
