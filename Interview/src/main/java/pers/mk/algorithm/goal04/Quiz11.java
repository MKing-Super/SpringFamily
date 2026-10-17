package pers.mk.algorithm.goal04;

import java.util.*;

public class Quiz11 {
    public static void main(String[] args) {
        // 注意审题啊！是排名全部输出，不是只输出前3个！
        mk();
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine().trim());
        String[] idArr = in.nextLine().trim().split(",");
        String[] gradeArr = in.nextLine().trim().split(",");
        if (N != idArr.length || N != gradeArr.length){
            return;
        }
        Map<Integer, List<Integer>> gradeOrderMap = new HashMap<>();
        for (int i = 0 ; i < N ;i++){
            int id = Integer.parseInt(idArr[i]);
            int grade = Integer.parseInt(gradeArr[i]);

            gradeOrderMap.putIfAbsent(id,new ArrayList<Integer>());
            gradeOrderMap.get(id).add(grade);
        }

        Map<Integer, Integer> valueGradeMap = new HashMap<>();
        for (Integer key : gradeOrderMap.keySet() ){
            List<Integer> list = gradeOrderMap.get(key);
            if (list.size() >= 3){
                list.sort((a,b) -> {
                    return b - a;
                });
                int total = 0;
                for (int i = 0 ; i < 3 ; i++){
                    total += list.get(i);
                }
                valueGradeMap.put(key,total);
            }
        }

        List<int[]> list = new ArrayList<>();
        for (Integer key : valueGradeMap.keySet()){
            Integer grade = valueGradeMap.get(key);
            list.add(new int[]{key,grade});
        }

        list.sort((a,b) -> {
            if (a[1] != b[1]){
                return b[1] - a[1];
            }else {
                return b[0] - a[0];
            }
        });


        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < list.size() ; i++){
            if (i > 0 ){
                sb.append(",");
            }
            sb.append(list.get(i)[0]);
        }
        System.out.println(sb);
    }



    private static void m1(){

    }

}
