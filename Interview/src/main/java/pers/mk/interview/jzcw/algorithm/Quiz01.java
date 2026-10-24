package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;

// https://leetcode.cn/problemset/
// 力扣
// 1. 两数之和
public class Quiz01 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;

        int[] resArr = new int[2];
        for (int i = 0 ; i < nums.length ; i++){
            for (int j = i + 1; j < nums.length ; j++){
                if (nums[i] + nums[j] == target){
                    resArr[0] = i;
                    resArr[1] = j;
                    break;
                }
            }
        }

        System.out.println(JSON.toJSONString(resArr));
    }
}
