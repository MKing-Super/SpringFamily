package pers.mk.interview.algorithm.a;

/*
3. 最长公共前缀

    题目描述：编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
    示例：
        输入：strs = ["flower","flow","flight"]
        输出："fl"

 */
public class a1_3 {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};


        // method 1:
        int minLength = 0;
        String res1 = "";
        for (int i = 0 ; i < strs.length ; i++){
            if (i == 0){
                minLength = strs[i].length();
            }else if (strs[i].length() < minLength){
                minLength = strs[i].length();
            }
        }
        if (minLength == 0){
            res1 = "";
            System.out.println(res1);
        }
        for (int i = 0 ; i < minLength ; i++){
            for (int j = 0 ; j < strs.length - 1 ; j++){
                if (strs[j].charAt(i) == strs[j+1].charAt(i)){
                    if (j == strs.length - 2){
                        res1 += strs[j].charAt(i);
                    }
                }else {
                    break;
                }
            }
        }
        System.out.println(res1);


    }
}
