package pers.mk.algorithm.goal04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz09 {
    public static void main(String[] args) {
        // 思路正确，但层级问题要注意！
//        mk();

        // 完善后
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        String pointFile = in.nextLine().trim();

        boolean isFound = false;
        int pointSign_ = 0;

        List<String[]> list = new ArrayList<>();
        for (int i = 0 ; i < n ; i++){
            String[] split = in.nextLine().trim().split(" ");
            if (pointFile.equals(split[0])){
                isFound = true;
                for (int k = 0 ; k < split[0].length() ; k++ ){
                    if (split[0].charAt(k) == '-'){
                        pointSign_ += 1;
                    }
                }
            }
            if (isFound){
                int curSign_ = 0;
                String curFile = "";
                for (int k = 0 ; k < split[0].length() ; k++ ){
                    if (split[0].charAt(k) == '-'){
                        curSign_ += 1;
                    }else {
                        curFile = split[0].substring(k);
                        break;
                    }
                }
                if (curSign_ > pointSign_  && !"-1".equals(split[1]) ){
                    list.add(new String[]{curFile,split[1]});
                }
                if (curSign_ <= pointSign_){
                    break;
                }
            }
        }

        list.sort((a,b) -> {
            return Integer.parseInt(a[1]) - Integer.parseInt(b[1]);
        });

        if (list.isEmpty()){
            System.out.println("No file");
        }
        for (int i = 0 ; i < list.size() ; i++){
            System.out.println(list.get(i)[0] + " " + list.get(i)[1]);
        }
    }





    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        String targetFolder = in.nextLine().trim();

        // 存储所有行的原始数据
        String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            lines[i] = in.nextLine();
        }

        // 找到目标文件夹的位置和层级
        int targetIndex = -1;
        int targetLevel = -1;
        for (int i = 0; i < n; i++) {
            String line = lines[i];
            int level = countLeadingDashes(line);
            String name = extractName(line, level);

            if (name.equals(targetFolder)) {
                targetIndex = i;
                targetLevel = level;
                break;
            }
        }

        // 如果没找到目标文件夹
        if (targetIndex == -1) {
            System.out.println("No file");
            return;
        }

        // 收集目标文件夹下的所有文件
        List<String[]> files = new ArrayList<>();
        for (int i = targetIndex + 1; i < n; i++) {
            String line = lines[i];
            int currentLevel = countLeadingDashes(line);

            // 如果当前层级小于等于目标层级，说明已经离开目标文件夹的范围
            if (currentLevel <= targetLevel) {
                break;
            }

            String[] parts = line.trim().split(" ");
            String fileName = parts[0];
            String timestamp = parts[1];

            // 只添加文件（不是文件夹）
            if (!timestamp.equals("-1")) {
                files.add(new String[]{fileName, timestamp});
            }
        }

        // 按时间戳排序
        files.sort((a, b) -> Integer.parseInt(a[1]) - Integer.parseInt(b[1]));

        // 输出结果
        if (files.isEmpty()) {
            System.out.println("No file");
        } else {
            for (String[] file : files) {
                System.out.println(file[0] + " " + file[1]);
            }
        }
    }

    // 计算前导短横线的数量（缩进层级）
    private static int countLeadingDashes(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == '-') {
            count++;
        }
        return count / 4;  // 每4个短横线代表一级缩进
    }

    // 提取去除缩进后的文件名/文件夹名
    private static String extractName(String line, int level) {
        return line.substring(level * 4).trim().split(" ")[0];
    }

    

}
