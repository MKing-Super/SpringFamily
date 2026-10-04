package pers.mk.algorithm.goal10;

import java.util.*;


public class Quiz02 {
    public static void main(String[] args) {
        // dfs 深度搜索 思路不对！但是能算出来
//        mk();

        // 二分查询，先找出所有的可靠值，让后逐个查那个可靠值能用
        m1();
    }

    static int S = 0;
    static int N = 0;
    static List<List<int[]>> list;

    static int maxResult = -1;
    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().split(" ");
        S = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        int total = Integer.parseInt(in.nextLine().trim());
        list = new ArrayList<>();

        for (int i = 0 ; i < N ; i++){
            list.add(new ArrayList<>());
        }

        for (int i = 0 ; i < total ; i++){
            String[] split3 = in.nextLine().split(" ");
            int type = Integer.parseInt(split3[0]);
            int reliability = Integer.parseInt(split3[1]);
            int price = Integer.parseInt(split3[2]);
            list.get(type).add(new int[]{reliability,price});
        }


        for (int i = 0 ; i < N ; i++){
            list.get(i).sort((a,b) -> {
                return Integer.compare(a[1],b[1]);
            });
        }

        int money = 0 ;
        for (int i = 0 ; i < N ;i++){
            if (list.get(i).size() == 0){
                System.out.println(-1);
                return;
            }
            money += list.get(i).get(0)[1];
        }
        if (money > S){
            System.out.println(-1);
            return;
        }

        maxResult = -1;
        dfsMk(0,Integer.MAX_VALUE,0);

        System.out.println(maxResult);
    }

    private static void dfsMk(int index,int currentMin,int money){
        if (money > S){
            return;
        }
        if (index >= N){
            maxResult = Math.max(maxResult,currentMin);
            return;
        }

        for (int i = 0 ; i < list.get(index).size() ; i++){
            int reliability = list.get(index).get(i)[0];
            int price = list.get(index).get(i)[1];
            dfsMk(index + 1,
                    Math.min(currentMin,reliability),
                    money + price);
        }
        return;
    }





    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().split(" ");
        S = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        int total = Integer.parseInt(in.nextLine().trim());
        list = new ArrayList<>();

        for (int i = 0 ; i < N ; i++){
            list.add(new ArrayList<>());
        }

        for (int i = 0 ; i < total ; i++){
            String[] split3 = in.nextLine().split(" ");
            int type = Integer.parseInt(split3[0]);
            int reliability = Integer.parseInt(split3[1]);
            int price = Integer.parseInt(split3[2]);
            list.get(type).add(new int[]{reliability,price});
        }

        // 检查是否有类型缺失
        for (int i = 0 ; i < N ; i++){
            if (list.get(i).size() == 0){
                System.out.println(-1);
                return;
            }
        }

        // 收集所有可能的可靠性值用户二分法查找
        HashSet<Integer> reliabilitySet = new HashSet<>();
        for (int i = 0 ; i < N ; i++){
            for (int[] part : list.get(i)){
                reliabilitySet.add(part[0]);
            }
        }

        List<Integer> reliabilities = new ArrayList<>(reliabilitySet);
        Collections.sort(reliabilities);

        // 二分查找最大可行的可能性
        int left = 0;
        int right = reliabilities.size() - 1;
        int result = -1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            int targetReliability = reliabilities.get(mid);

            if (canAchieve(reliabilities.get(mid))){
                result = targetReliability;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }

    static boolean canAchieve(int targetReliability){
        int totalPrice = 0;

        for (int i = 0 ; i < N ; i++){
            int minPrice = Integer.MAX_VALUE;

            for (int[] part : list.get(i)){
                if (part[0] >= targetReliability){
                    minPrice = Math.min(minPrice,part[1]);
                }
            }

            if (minPrice == Integer.MAX_VALUE){
                return false;
            }

            totalPrice += minPrice;
            if (totalPrice > S){
                return false;
            }
        }

        return true;
    }


}
