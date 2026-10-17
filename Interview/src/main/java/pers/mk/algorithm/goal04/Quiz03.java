package pers.mk.algorithm.goal04;

import java.util.*;

public class Quiz03 {
    public static void main(String[] args) {
        // 思路对，错误点：
        // mkdir 命令没有检查目录是否已存在
        // cd 命令没有正确处理目录不存在的情况
        // pwd 输出格式与题目要求不符（示例输出是/abc/而不是/abc）
        // Set 集合中的Files对象没有重写equals和hashCode方法
//        mk();

        // 修正后
        m1();
    }

    static String mkdir = "mkdir";
    static String cd = "cd";
    static String pwd = "pwd";

    private static void mk(){
        Scanner in = new Scanner(System.in);
        ArrayList<String> orderList = new ArrayList<>();
        for (int i = 0 ; i < 100 ; i++){
            String str = in.nextLine().trim();
            if ("".equals(str)){
                break;
            }
            orderList.add(str);
        }

        if (orderList.size() == 0){
            System.out.println();
            return;
        }

        if (!orderList.get(orderList.size() - 1).equals(pwd)){
            System.out.println();
            return;
        }

        Files curFile = new Files(null,"/","/");
        for (int i = 0 ; i < orderList.size() ; i++){
            String[] tempArr = orderList.get(i).split(" ");

            if (tempArr.length == 2){
                String order = tempArr[0];
                String content = tempArr[1];
                if (mkdir.equals(order)){
                    curFile.sonFiles.add(new Files(curFile,content,curFile.fileName + content + "/"));
                }else if (cd.equals(order)){
                    if (!"..".equals(content)){
                        // 进入下一级
                        if (curFile.sonFiles.size() == 0){
                            // 未找到
                        }else {
                            for (Files f : curFile.sonFiles){
                                if (f.fileName.equals(content)){
                                    curFile = f;
                                    continue;
                                }
                            }
                            // 未找到
                        }

                    }else {
                        // 去上一级
                        if (curFile.father != null){
                            curFile = curFile.father;
                        }else {
                            // 未找到
                        }
                    }
                }

            }else {
                // 不显示
            }

        }

        System.out.println(curFile.pwd);
    }

    private static class Files {
        // 父级
        Files father;
        // 文件夹名称
        String fileName;
        // 当前绝对地址
        String pwd;
        // 儿子集
        List<Files> sonFiles = new ArrayList<>();

        public Files(Files father, String fileName, String pwd) {
            this.father = father;
            this.fileName = fileName;
            this.pwd = pwd;
        }
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        ArrayList<String> orderList = new ArrayList<>();
        for (int i = 0 ; i < 100 ; i++){
            String str = in.nextLine().trim();
            if ("".equals(str)){
                break;
            }
            orderList.add(str);
        }

        if (orderList.size() == 0){
            System.out.println();
            return;
        }

        // 只有最后一条命令是pwd时才输出
        String lastCommand = orderList.get(orderList.size() - 1);
        boolean needOutPut = lastCommand.equals(pwd);

        Files curFile = new Files(null,"/","/");

        for (int i = 0 ; i < orderList.size() ; i++){
            String[] tempArr = orderList.get(i).split(" ");

            if (tempArr.length == 2){
                String order = tempArr[0];
                String content = tempArr[1];

                if (mkdir.equals(order)){
                    // 检查是否已存在同名目录
                    boolean exists = false;
                    for (Files f : curFile.sonFiles){
                        if (f.fileName.equals(content)){
                            exists = true;
                            break;
                        }
                    }
                    if (!exists){
                        curFile.sonFiles.add(new Files(curFile,content,curFile.pwd + content + "/"));
                    }
                }else if (cd.equals(order)){
                    if (!"..".equals(content)){
                        // 进入下一级
                        for (Files f : curFile.sonFiles){
                            if (f.fileName.equals(content)){
                                curFile = f;
                                break;
                            }
                        }
                        // 如果没找到，不做任何操作
                    }else {
                        // 返回上级目录
                        if (curFile.father != null){
                            curFile = curFile.father;
                        }
                        // 如果在根目录，不做任何操作
                    }
                }
            }
        }

        if (needOutPut){
            System.out.println(curFile.pwd);
        }

    }


}
