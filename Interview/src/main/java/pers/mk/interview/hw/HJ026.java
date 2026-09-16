package pers.mk.interview.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*

描述
对于给定的由可见字符和空格组成的字符串，按照下方的规则进行排序：
∙ ∙按照字母表中的顺序排序（不区分大小写）；
∙ ∙同一字母的大小写同时存在时，按照输入顺序排列；
∙ ∙非字母字符保持原来的位置不参与排序；
直接输出排序后的字符串。

 */
public class HJ026 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] arr = str.toCharArray();

        // 1. 抽出所有字母，并记录它们原来的位置
        List<Character> letters = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            // 如果只考虑英文字母；更通用可用 Character.isLetter(c)
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                letters.add(c);
                pos.add(i);
            }
        }

        // 2. 对字母做稳定排序：按忽略大小写的字母升序
        //    List.sort 是稳定排序；比较器只比小写，相等返回 0，保留输入顺序
        letters.sort((c1, c2) ->
                Character.compare(Character.toLowerCase(c1), Character.toLowerCase(c2))
        );

        // 3. 把排好序的字母按原字母出现的位置依次填回
        for (int k = 0; k < pos.size(); k++) {
            arr[pos.get(k)] = letters.get(k);
        }

        // 4. 输出（char[] 会被 println 当作字符串打印）
        System.out.println(arr);
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
