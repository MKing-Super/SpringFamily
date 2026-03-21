package pers.mk.interview.algorithm.a;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/*
1. 两数之和

    题目描述：给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出和为目标值 target的那两个整数，并返回它们的数组索引。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。你可以按任意顺序返回答案。
    示例：
        输入：nums = [2, 7, 11, 15], target = 9
        输出：[0, 1]
        解释：nums[0] + nums[1] == 9，返回 [0, 1]。

 */
public class a1_1 {
    public static void main(String[] args) {
        Integer[] array = {2,7,11,15};
        Integer target = 9;

        // 法1：
        ArrayList<Integer> res1 = new ArrayList<>();
        for (int i = 0 ; i < array.length ; i++){
            for (int j = i ; j < array.length ; j++){
                if (i != j && array[i] + array[j] == target){
                    res1.add(i);
                    res1.add(j);
                }
            }
        }
        System.out.println(JSON.toJSONString(res1));




    }
}
