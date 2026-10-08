package pers.mk.algorithm.goal02;

import java.util.Scanner;

public class Quiz10 {
    public static void main(String[] args) {
        // Math 方法使用错误
        // Math 中 ceil 是向上取整，floor 是向下取整，round 是四舍五入
//        mk();

        // 修正后
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int[][] table = new int[3][3];
        int centerNum = 0;
        int arroundTotal8 = 0;
        for (int i = 0 ;i < 3 ; i++){
            String[] split = in.nextLine().trim().split(" ");
            for (int j = 0 ; j < 3 ; j++){
                table[i][j] = Integer.parseInt(split[j]);
                if (i == 1 && j == 1){
                    centerNum = table[i][j];
                }else {
                    arroundTotal8 += table[i][j];
                }
            }
        }
        //
        int arroundAvg8 = (int)Math.ceil(arroundTotal8 / 8.0);
        int diff = Math.abs(centerNum  - arroundAvg8);
        if (diff > 50 ){
            centerNum = arroundAvg8;
        }else if (diff >= 30 && diff <= 50){
            centerNum = (int) Math.round((arroundTotal8 + centerNum) / 9.0);
        }else {
            centerNum = centerNum;
        }
        table[1][1] = centerNum;
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static void m1(){
        Scanner in = new Scanner(System.in);
        int[][] table = new int[3][3];
        int centerNum = 0;
        int arroundTotal8 = 0;

        for (int i = 0; i < 3; i++){
            String[] split = in.nextLine().trim().split(" ");
            for (int j = 0; j < 3; j++){
                table[i][j] = Integer.parseInt(split[j]);
                if (i == 1 && j == 1){
                    centerNum = table[i][j];
                } else {
                    arroundTotal8 += table[i][j];
                }
            }
        }

        // 周围8个元素的均值，四舍五入
        int arroundAvg8 = (int) Math.round(arroundTotal8 / 8.0);
        int diff = Math.abs(centerNum - arroundAvg8);

        if (diff > 50){
            centerNum = arroundAvg8;
        } else if (diff >= 30 && diff <= 50){
            // 整体均值，四舍五入
            centerNum = (int) Math.round((arroundTotal8 + centerNum) / 9.0);
        }
        // diff < 30 保持不变

        table[1][1] = centerNum;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

}
