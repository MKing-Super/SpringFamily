package pers.mk.algorithm.goal02;

import java.util.*;

/*

题目描述
考勤记录是分析和考核职工工作时间利用情况的原始依据，也是计算职工工资的原始依据，为了正确地计算职工工资和监督工资基金使用情况，公司决定对员工的手机打卡记录进行异常排查。

如果出现以下两种情况，则认为打卡异常
1.实际设备号与注册设备号不一样
2.或者，同一个员工的两个打卡记录的时间小于60分钟并且打卡距离超过5km。

给定打卡记录的字符串数组clockRecords (每个打卡记录组成为: 工号:时间 (分钟);打距离(km);实际设备号;注册设备号)，返回其中异常的打卡记录(按输入顺序输出)。

输入描述
第一行输入一个数字N，为打卡记录的条数。
后续N行记录分别表示打卡记录：工号:时间 (分钟);打距离(km);实际设备号;注册设备号，以逗号间隔。

输出描述
输出异常的打卡记录，以分号间隔。若无异常打卡记录，则输出字符串null

示例1：
输入
2
100000,10,1,ABCD,ABCD
100000,50,10,ABCD,ABCD

输出
100000,10,1,ABCD,ABCD;100000,50,10,ABCD,ABCD

说明
无

示例2：
输入
2
100000,10,1,ABCD,ABCD
100001,80,10,ABCE,ABCE

输出
null

说明
无异常打卡记录，所以返回null

 */
public class Quiz07 {
    public static void main(String[] args) {
//        注意题干内容
        // 规则1：设备号不同
        // 规则2：同员工 2次打卡的时间差，2次打卡的距离差。
//        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        Integer n = Integer.parseInt(in.nextLine());
        List<String[]> list = new ArrayList<>();
        List<String[]> errorList = new ArrayList<>();
        HashMap<String, List<String[]>> map = new HashMap<>();
        for (int i = 0 ; i < n ; i++){
            String[] split = in.nextLine().split(",");
            String no = split[0];
            String time = split[1];
            String distance = split[2];
            String realNo = split[3];
            String applyNo = split[4];
            String indexStr = String.valueOf(i);
            list.add(new String[]{no,time,distance,realNo,applyNo,indexStr});
            errorList.add(new String[]{});
            map.putIfAbsent(no,new ArrayList<>());
            map.get(no).add(new String[]{no,time,distance,realNo,applyNo,indexStr});
        }

        for (String key : map.keySet()){
            List<String[]> sameNoList = map.get(key);
            for (int i = 0 ; i < sameNoList.size() - 1 ; i++){
                String curTime = sameNoList.get(i)[1];
                String curDistence = sameNoList.get(i)[2];
                Integer curIndex = Integer.parseInt(sameNoList.get(i)[5]);
                String nextTime = sameNoList.get(i + 1)[1];
                String nextDistence = sameNoList.get(i + 1)[2];
                Integer nextIndex = Integer.parseInt(sameNoList.get(i + 1)[5]);
                if (Integer.parseInt(curTime) < 60 && Integer.parseInt(nextTime) < 60 ){
                    if (Integer.parseInt(curDistence) > 5 || Integer.parseInt(nextDistence) > 5 ) {
                        errorList.set(curIndex,sameNoList.get(i));
                        errorList.set(nextIndex,sameNoList.get(i + 1));
                    }
                }
            }
        }

        for (int i = 0 ; i < n ; i++){
            String realNo = list.get(i)[3];
            String applyNo = list.get(i)[4];
            Integer curIndex = Integer.parseInt(list.get(i)[5]);
            if (!realNo.equals(applyNo)){
                errorList.set(curIndex,list.get(i));
            }
        }

        String res = "";
        for (String[] t : errorList){
            if (t != null && t.length != 0){
                String no = t[0];
                String time = t[1];
                String distance = t[2];
                String realNo = t[3];
                String applyNo = t[4];
                res += no + "," + time + "," + distance + "," + realNo + "," + applyNo + ";";
            }
        }
        if (res.length() != 0){
            System.out.println(res.substring(0,res.length() - 1));
        }else {
            System.out.println("null");
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        Integer n = Integer.parseInt(in.nextLine());
        List<String[]> list = new ArrayList<>();
        boolean[] errorFlag = new boolean[n];
        Arrays.fill(errorFlag,false);

        HashMap<String, List<Integer>> map = new HashMap<>();
        for (int i = 0 ; i < n ; i++){
            String[] split = in.nextLine().split(",");
            String no = split[0];
            String time = split[1];
            String distance = split[2];
            String realNo = split[3];
            String applyNo = split[4];
            String indexStr = String.valueOf(i);
            list.add(new String[]{no,time,distance,realNo,applyNo,indexStr});

            map.putIfAbsent(no,new ArrayList<>());
            map.get(no).add(i);
        }

        // 规则1：设备号不同
        for (int i = 0 ; i < n ; i++){
            String realNo = list.get(i)[3];
            String applyNo = list.get(i)[4];
            Integer curIndex = Integer.parseInt(list.get(i)[5]);
            if (!realNo.equals(applyNo)){
                errorFlag[i] = true;
            }
        }

        // 规则2：同员工时间差<60 且 distance>5（按示例，两条都标）
        for (String key : map.keySet()){
            List<Integer> indexList = map.get(key);
            for (int i = 0 ; i < indexList.size() ; i++){
                for (int j = i + 1 ; j < indexList.size() ; j++){
                    Integer iIndex = indexList.get(i);
                    Integer jINdex = indexList.get(j);
                    Integer iTime = Integer.parseInt(list.get(iIndex)[1]);
                    Integer iDistance = Integer.parseInt(list.get(iIndex)[2]);
                    Integer jTime = Integer.parseInt(list.get(jINdex)[1]);
                    Integer jDistance = Integer.parseInt(list.get(jINdex)[2]);
                    if (Math.abs(iTime - jTime) < 60 && Math.abs(iDistance - jDistance) > 5){
                        errorFlag[iIndex] = true;
                        errorFlag[jINdex] = true;
                    }
                }
            }
        }


        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < list.size() ; i++){
            if (errorFlag[i]){
                String[] t = list.get(i);
                String no = t[0];
                String time = t[1];
                String distance = t[2];
                String realNo = t[3];
                String applyNo = t[4];
                res.append(no).append(",")
                        .append(time).append(",")
                        .append(distance).append(",")
                        .append(realNo).append(",")
                        .append(applyNo).append(";");
            }
        }

        if (res.length() != 0){
            System.out.println(res.substring(0,res.length() - 1));
        }else {
            System.out.println("null");
        }
    }
}
