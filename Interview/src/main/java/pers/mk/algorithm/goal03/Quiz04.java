package pers.mk.algorithm.goal03;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Quiz04 {
    public static void main(String[] args) {
        // 处理过程复杂，且未考虑到 “” 的情况
//        mk();

        // 正确使用了 indexOf 减少了复杂度
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int K = Integer.parseInt(in.nextLine().trim());
        String S = in.nextLine().trim();
        Map<Integer, String> treeMap = new TreeMap<>();
        int index = 1;
        for (int i = 0 ; i < S.length() ; i++){
            if (S.charAt(i) == '"'){
                int left = i;
                for (int j = i + 1 ; j < S.length() ;j++){
                    if (S.charAt(j) == '"'){
                        treeMap.putIfAbsent(index,S.substring(left,j + 1));
                        i = j;
                        index++;
                        break;
                    }
                }
            }
        }

        for (Integer key : treeMap.keySet()){
            S = S.replaceAll(treeMap.get(key),"\""+key+"\"");
        }

        while (S.contains("__")){
            S = S.replaceAll("__","_");
        }
        if (S.charAt(S.length() - 1) == '_'){
            S = S.substring(0,S.length() - 1);
        }

        String[] split = S.split("_");
        System.out.println(JSON.toJSONString(split));
        System.out.println(JSON.toJSONString(treeMap));
        if (K >= split.length){
            System.out.println("ERROR");
            return;
        }else {
            split[K] = "******";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < split.length ; i++){
            if (i != 0){
                sb.append("_");
            }
            if (split[i].contains("\"") && treeMap.containsKey(Integer.parseInt(split[i].split("\"")[1]) )){
                sb.append(treeMap.get(Integer.parseInt(split[i].split("\"")[1]) ));
            }else {
                sb.append(split[i]);
            }
        }
        System.out.println(sb);
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        int K = Integer.parseInt(in.nextLine().trim());
        String S = in.nextLine().trim();

        List<String> commands = new ArrayList<>();
        int i = 0;

        while (i < S.length() ){
            // 处理双引号
            if (S.charAt(i) == '"'){
                int end = S.indexOf('"',i + 1);
                if (end != -1){
                    commands.add(S.substring(i,end + 1));
                    i = end + 1;
                }else {
                    // 输入格式错误
                    break;
                }
            }
            // 处理 除下划线 以外的普通字符
            else if (S.charAt(i) != '_'){
                int start = i;
                while (i < S.length() && S.charAt(i) != '_' && S.charAt(i) != '"'){
                    i++;
                }
                commands.add(S.substring(start,i));
            }
            // 若为 下划线 则跳过
            else {
                i++;
            }
        }

        // 检查索引有效性
        if (K >= commands.size()){
            System.out.println("ERROR");
            return;
        }
        System.out.println(JSON.toJSONString(commands));
        // 替换目标命令字
        commands.set(K,"******");

        StringBuilder sb = new StringBuilder();
        for (int j = 0 ; j < commands.size() ; j++ ){
            if (j > 0){
                sb.append("_");
            }
            sb.append(commands.get(j));
        }
        System.out.println(sb);
    }

}
