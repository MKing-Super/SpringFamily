package pers.mk.algorithm.goal02;

import java.util.Arrays;
import java.util.Scanner;

/*

疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法:
给出数字个数n和行数m(1 < n,m < 999)，从左上角的1开始，按照顺时针螺旋向内写方式，依次写出2,3...n,最终形成个一m行矩阵。
小明对这个矩阵有些要求
1.每行数字的个数一样多
2.列的数量尽可能少
3.填充数字时优先填充外部
4.数字不够时，使用单个*号占位
输入描述
输入一行，两个整数，空格隔开，依次表示n、m
输出描述
符合要求的唯一矩阵


示例1：
输入
9 4

输出
1 2 3
* * 4
9 * 5
8 7 6

示例2：
输入
3 5

输出
1
2
3
*
*


 */
public class Quiz02 {
    public static void main(String[] args) {
        // 范围缩减错误
//        mk();
//        https://www.nowcoder.com/discuss/595557098189049856
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().trim().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int xLong = 0;
        if (n <= m){
            xLong = 1;
        }else {
            int t = n/m;
            int surplus = n%m;
            xLong = surplus == 0 ? t : t + 1;
        }
        String[][] arrStr = new String[m][xLong];
        for (int i = 0 ; i < m ; i++){
            Arrays.fill(arrStr[i],"*");
        }

        int tn = 1;
        int xstart = 0;
        int xend = xLong -1;
        int ystart = 0;
        int yend = m - 1;
        while (tn <= n){
            // 从左到右
            for (int i = xstart ; i <= xend && tn <= n ; i++){
                arrStr[ystart][i] = String.valueOf(tn++);
            }
            ystart += 1;
            if (ystart > yend){
                break;
            }

            // 从上到下
            for (int i = ystart ; i <= yend && tn <= n ; i++){
                arrStr[i][xend] = String.valueOf(tn++);
            }
            xend -= 1;
            if (xend < xstart){
                break;
            }

            // 从右到左
            for (int i = xend ; i >= xstart && tn <= n ; i--){
                arrStr[yend][i] = String.valueOf(tn++);
            }
            yend -= 1;
            if (yend < ystart){
                break;
            }

            // 从下向上
            for (int i = yend ; i >= ystart && tn <= n ; i--){
                arrStr[i][xstart] = String.valueOf(tn++);
            }
            xstart += 1;
            if (xstart > xend){
                break;
            }

        }

        for (int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < xLong ; j++){
                System.out.print(arrStr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int rows = m;
        int cols = (n - 1) / m + 1;


        int[][] grid = new int[rows][cols];

        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;
        int cur = 1;

        while (cur <= n){
            // 从左到右，最上面固定
            for (int i = left ; i <= right && cur <= n ; i++){
                grid[top][i] = cur++;
            }
            if (++top > bottom) break;

            // 从上到下，最右边固定
            for (int i = top ; i <= bottom && cur <= n ; i++){
                grid[i][right] = cur++;
            }
            if (--right < left) break;

            // 从右到左，最下面固定
            for (int i = right ; i >= left && cur <= n ; i--){
                grid[bottom][i] = cur++;
            }
            if (--bottom < top) break;

            // 从下往上，最左边固定
            for (int i = bottom ; i >= top && cur <= n ; i--){
                grid[i][left] = cur++;
            }
            if (++left > right) break;

        }

        for (int i = 0 ; i < rows ; i++){
            for (int j = 0 ; j < cols ; j++){
                System.out.print(grid[i][j] == 0 ? "* " : grid[i][j] + " ");
            }
            System.out.println();
        }

    }
}
