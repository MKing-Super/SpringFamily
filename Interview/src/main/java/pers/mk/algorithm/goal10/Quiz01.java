package pers.mk.algorithm.goal10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args) {
        // 思路错误
//        mk();
        // 这是一道贪心算法类型的题目，具体来说是资源分配类贪心问题。
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().split(" ");
        int caiyangNum = Integer.parseInt(split[0]);
        int zhiyuanNum = Integer.parseInt(split[1]);
        String[] split1 = in.nextLine().split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < caiyangNum ; i++){
            list.add(Integer.parseInt(split1[i]));
        }

        if (caiyangNum == 0){
            System.out.println(0);
            return;
        }
        // 配置拉满
        if (zhiyuanNum / caiyangNum == 4 && zhiyuanNum % caiyangNum == 0){
            int max = 0;
            for (int i = 0 ; i < list.size() ; i++){
                max += Math.min(600,list.get(i) + list.get(i) * 0.1 * 3);
            }
            System.out.println(max);
            return;
        }
        // 无志愿者
        if (zhiyuanNum == 0){
            int max = 0;
            for (int i = 0 ; i < list.size() ; i++){
                max += Math.max(60,list.get(i) - list.get(i) * 0.1 * 2);
            }
            System.out.println(max);
            return;
        }


    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().split(" ");
        int caiyangNum = Integer.parseInt(split[0]);
        int zhiyuangNum = Integer.parseInt(split[1]);
        String[] split1 = in.nextLine().split(" ");

        ArrayList<Integer> efficiencies = new ArrayList<>();
        for (int i = 0 ; i < caiyangNum ; i++){
            efficiencies.add(Integer.parseInt(split1[i]));
        }

        if (caiyangNum == 0){
            System.out.println(0);
            return;
        }

        // 效率高的采集员优先
        Collections.sort(efficiencies,(a,b) -> {
            return Integer.compare(b,a);
        });

        int[] volunteerCount = new int[caiyangNum];
        int assignedVolunteers = 0;

        // 第一阶段：给每个采集员配一个志愿者（恢复正常效率）
        for (int i = 0 ; i < caiyangNum && assignedVolunteers < zhiyuangNum ; i++){
            volunteerCount[i] = 1;
            assignedVolunteers++;
        }

        // 第二阶段：剩余志愿者优先分配给“效率高”的采样员
        while (assignedVolunteers < zhiyuangNum){
            int bestIndex = -1;
            double bestGain = 0;

            for (int i = 0 ; i < caiyangNum ; i++){
                double baseEff = efficiencies.get(i);
                double m = baseEff * 0.1;

                // 如果已经达到最多4个志愿者，跳过
                if (volunteerCount[i] >= 4){
                    continue;
                }

                // 计算当前效率和增加1个志愿者后的效率
                double currentEff = getEfficiency(baseEff,volunteerCount[i]);
                double nextEff = getEfficiency(baseEff,volunteerCount[i] + 1);
                double gain = nextEff - currentEff;

                if (gain > bestGain){
                    bestGain = gain;
                    bestIndex = i;
                }
            }

            if (bestIndex == -1 || bestGain <= 0){
                break;
            }

            volunteerCount[bestIndex]++;
            assignedVolunteers++;
        }

        double totalEfficiency = 0;
        for (int i = 0 ; i < caiyangNum ; i++){
            totalEfficiency += getEfficiency(efficiencies.get(i),volunteerCount[i]);
        }

        System.out.println((int)totalEfficiency);
    }

    private static double getEfficiency(double baseEff,int volunteers){
        double m = baseEff * 0.1;

        if (volunteers == 0){
            return Math.max(60,baseEff - 2 * m);
        }else if (volunteers == 1){
            return Math.min(600,baseEff);
        }else {
            int boost = Math.min(volunteers - 1,3);
            return Math.min(600,baseEff + boost * m);
        }
    }


}
