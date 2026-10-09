package pers.mk.algorithm.goal03;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Quiz05 {
    public static void main(String[] args) {
        //1. 算法思路错误
        //代码尝试用DFS逐层构建密码链，但忽略了重要条件：只需要验证每个密码的所有前缀是否存在，而不是构建完整的链式关系。
        //2. 字典序比较缺失
        //题目要求"返回字典序最大的密码"，但代码中只是简单地取最长密码，没有处理同长度时的字典序比较。
//        mk();

        // 正确思路：对于每个密码，需要验证它的所有前缀都在密码本
        // 注意：字典序是指 字符串的大小  如：abc 的字典序大于 abb
        m1();
    }

    static List<String> keys = new ArrayList<>();
    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        Map<Integer, List<String>> map = new TreeMap<>();
        int maxLen = 0;
        for (int i = 0 ; i < split.length ; i++){
            map.putIfAbsent(split[i].length(),new ArrayList<>());
            map.get(split[i].length()).add(split[i]);
            if (split[i].length() > maxLen){
                maxLen = split[i].length();
            }
        }

        dfsMk(maxLen,map,1,"");

        String result = "";
        for (int i = 0 ; i < keys.size() ; i++){
            if (keys.get(i).length() >= result.length()){
                result = keys.get(i);
            }
        }

        System.out.println(result);
    }

    static void dfsMk(int maxLen,Map<Integer, List<String>> map,int len,String beforeKey){
        if (len > maxLen){
            return;
        }
        if (map.get(len) == null){
            return;
        }

        for (int i = 0 ;!map.get(len).isEmpty() && i < map.get(len).size() ; i++){
            if ( map.get(len ).get(i).substring(0,len - 1) .equals(beforeKey)){
                String curPassword = map.get(len).get(i);
                keys.add(curPassword);
                dfsMk(maxLen,map,len + 1,map.get(len ).get(i));
            }
        }
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        HashSet<String> passwordSet = new HashSet<>(Arrays.asList(split));

        String result = "";

        for (String password : split){
            boolean allPrefixExist = true;

            for (int i = 1 ; i < password.length() ; i++){
                String prefix = password.substring(0, i);
                if (!passwordSet.contains(prefix)){
                    allPrefixExist = false;
                    break;
                }
            }

            if (allPrefixExist){
                if (password.length() > result.length()){
                    result = password;
                }else if (password.length() == result.length() && password.compareTo(result) > 0){
                    result = password;
                }
            }
        }

        System.out.println(result);

    }

}
