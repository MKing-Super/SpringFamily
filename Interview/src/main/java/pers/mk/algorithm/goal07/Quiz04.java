package pers.mk.algorithm.goal07;

import java.util.ArrayList;
import java.util.Scanner;

public class Quiz04 {
    public static void main(String[] args) {
        // 写对了，但逻辑混乱！！！！
//        mk();


        m1();

    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] netConfigArr = in.nextLine().trim().split(",");
        String targetCode = in.nextLine().trim();
        ArrayList<String> resList = new ArrayList<>();
        for (String config : netConfigArr){
            if (config.contains("*") && config.contains("?")){
                int idx = config.indexOf("*");
                String prev = config.substring(0,idx);
                String after = idx >= config.length() - 1 ? "" : config.substring(idx + 1);
                boolean pass = true;

                if (!"".equals(prev)){
                    for (int i = 0 ; i < prev.length() ; i++){
                        if (prev.charAt(i) != '?' && prev.charAt(i) != targetCode.charAt(i) ){
                            pass = false;
                            break;
                        }
                        if (prev.charAt(i) == '?' && i % 2 == 0){
                            pass = false;
                            break;
                        }
                    }
                    if (!pass){
                        continue;
                    }
                }
                if (!"".equals(after)){
                    for (int i = 0 ; i < after.length() ; i++){
                        if (after.charAt(i) != '?' && after.charAt(i) != targetCode.charAt(i) ){
                            pass = false;
                            break;
                        }
                        if (after.charAt(i) == '?' && i % 2 == 0){
                            pass = false;
                            break;
                        }
                    }
                    if (!pass){
                        continue;
                    }
                }
                resList.add(config);
                continue;
            }
            // 匹配 单个*
            if (config.contains("*")){
                if (config.indexOf("*") == 0){
                    int startIdx = targetCode.length() - config.length() - 1;
                    if (config.substring(1).equals(targetCode.substring(startIdx)) ||
                            config.substring(1).equals(targetCode)){
                        resList.add(config);
                        continue;
                    }
                }else if (config.indexOf("*") == config.length() - 1){
                    int endIdx = config.length() - 1;
                    if (config.substring(0,endIdx).equals(targetCode.substring(0,endIdx)) ||
                            config.substring(0,endIdx).equals(targetCode) ){
                        resList.add(config);
                        continue;
                    }
                }else {
                    int endIdx = config.indexOf("*");
                    if (config.substring(0,endIdx).equals(targetCode.substring(0,endIdx)) &&
                            config.substring(endIdx + 1).equals(targetCode.substring(endIdx + 1)) ||
                            config.substring(0,endIdx).equals(targetCode.substring(0,endIdx)) &&
                                    config.substring(endIdx + 1).equals(targetCode.substring(endIdx))){
                        resList.add(config);
                        continue;
                    }
                }
                continue;
            }
            // 匹配 ?
            if (config.contains("?") ){
                if (config.length() == targetCode.length() ){
                    boolean pass = true;
                    for (int i = 0 ; i < config.length() ; i++){
                        if (config.charAt(i) != '?' && config.charAt(i) != targetCode.charAt(i) ){
                            pass = false;
                            break;
                        }
                        if (config.charAt(i) == '?' && i % 2 == 0){
                            pass = false;
                            break;
                        }
                    }
                    if (!pass){
                        continue;
                    }
                    resList.add(config);
                }
                continue;
            }
            if (config.equals(targetCode)){
                resList.add(config);
            }
        }

        resList.sort((a,b) -> {
            return a.compareTo(b);
        });

        if (resList.isEmpty()){
            System.out.println("null");
        }
        StringBuilder sb = new StringBuilder();
        for (String res : resList){
            sb.append(res);
        }
        System.out.println(sb);
    }


    private static void m1() {
        Scanner in = new Scanner(System.in);
        String[] netConfigArr = in.nextLine().trim().split(",");
        String targetCode = in.nextLine().trim();
        ArrayList<String> resList = new ArrayList<>();

        for (String config : netConfigArr) {
            if (match(config, targetCode)) {
                resList.add(config);
            }
        }

        resList.sort(String::compareTo);

        if (resList.isEmpty()) {
            System.out.println("null");
        } else {
            System.out.println(String.join(",", resList));
        }
    }

    private static boolean match(String pattern, String target) {
        int starIdx = pattern.indexOf('*');

        if (starIdx == -1) {
            // 没有 *，纯 ? 和数字
            if (pattern.length() != target.length()) return false;
            for (int i = 0; i < pattern.length(); i++) {
                char pc = pattern.charAt(i);
                char tc = target.charAt(i);
                if (pc == '?') {
                    if (i % 2 == 0) return false;  // ? 在偶数位置不匹配
                } else if (pc != tc) {
                    return false;
                }
            }
            return true;
        }

        // 有 *
        String prefix = pattern.substring(0, starIdx);
        String suffix = pattern.substring(starIdx + 1);

        // 匹配前缀
        for (int i = 0; i < prefix.length(); i++) {
            char pc = prefix.charAt(i);
            char tc = target.charAt(i);
            if (pc == '?') {
                if (i % 2 == 0) return false;
            } else if (pc != tc) {
                return false;
            }
        }

        // 匹配后缀（从target末尾向前匹配）
        int suffixLen = suffix.length();
        int targetLen = target.length();
        for (int i = 0; i < suffixLen; i++) {
            char pc = suffix.charAt(suffixLen - 1 - i);
            char tc = target.charAt(targetLen - 1 - i);
            int posInTarget = targetLen - suffixLen + i;
            if (pc == '?') {
                if (posInTarget % 2 == 0) return false;
            } else if (pc != tc) {
                return false;
            }
        }

        // 检查前缀和后缀是否重叠
        if (prefix.length() + suffixLen > targetLen) return false;

        return true;
    }

}
