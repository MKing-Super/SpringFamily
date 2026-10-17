package pers.mk.algorithm.goal04;

import java.util.*;

public class Quiz12 {
    public static void main(String[] args) {
        // 思路没问题
//        mk();

        // 使用map实现
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        List<String[]> list = new ArrayList<>();
        List<String> resList = new ArrayList<>();

        while (in.hasNextLine()){
            String str = in.nextLine().trim();
            if ("".equals(str)){
                break;
            }
            try {
                int num = Integer.parseInt(str);
                list.sort((a, b) -> {
                    if (Integer.parseInt(a[1]) != Integer.parseInt(b[1])) {
                        return Integer.parseInt(b[1]) - Integer.parseInt(a[1]);
                    } else {
                        return a[0].compareTo(b[0]);
                    }
                });
                StringBuilder sb = new StringBuilder();
                for (int i = 0 ; i < num ; i++){
                    if (i > 0){
                        sb.append(",");
                    }
                    sb.append(list.get(i)[0]);
                }
                resList.add(sb.toString());
            }catch (Exception e){
                boolean isFound = false;
                for (int i = 0 ; i < list.size() ; i++){
                    if (str.equals(list.get(i)[0])){
                        int t = Integer.parseInt(list.get(i)[1]) + 1;
                        list.get(i)[1] = String.valueOf(t);
                        isFound = true;
                        break;
                    }
                }
                if (!isFound){
                    list.add(new String[]{str,"1"});
                }
            }

        }

        for (int i = 0 ; i < resList.size() ; i++){
            System.out.println(resList.get(i));
        }
    }

    private static void m1() {
        Scanner in = new Scanner(System.in);
        Map<String, Integer> countMap = new HashMap<>();
        List<String> outputs = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) continue; // 跳过空行，但不作为终止条件

            try {
                int n = Integer.parseInt(line);
                // 获取所有条目并按规则排序
                List<Map.Entry<String, Integer>> entries = new ArrayList<>(countMap.entrySet());
                entries.sort((a, b) -> {
                    int cmp = b.getValue().compareTo(a.getValue());
                    if (cmp != 0) return cmp;
                    return a.getKey().compareTo(b.getKey());
                });
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n && i < entries.size(); i++) {
                    if (i > 0) sb.append(",");
                    sb.append(entries.get(i).getKey());
                }
                outputs.add(sb.toString());
            } catch (NumberFormatException e) {
                // 是 URL
                countMap.merge(line, 1, Integer::sum);
            }
        }

        for (String out : outputs) {
            System.out.println(out);
        }
    }
}
