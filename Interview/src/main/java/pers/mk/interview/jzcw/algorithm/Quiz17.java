package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

// 283. 移动零
public class Quiz17 {

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,0,1};

        // 注意审题啊！！！！
//        mk(nums);

        // 双指针
        m1(nums);
    }


    private static void mk(int[] nums) {
        Arrays.sort(nums);
        int left = -1;
        int right = -1;
        for (int i = 0 ; i < nums.length ; i++){
            if (nums[i] == 0){
                if (left == -1){
                    left = i;
                }
                right = i;
            }
        }
        int move = right - left + 1;
        if (left != -1){
            for (int i = left ; i < nums.length ; i++){
                if (i + move < nums.length){
                    nums[i] = nums[i + move];
                }else {
                    nums[i] = 0;
                }
            }
        }
        System.out.println(JSON.toJSONString(nums));
    }



    private static void m1(int[] nums) {
        int len = nums.length;
        int left = 0 ;
        int right = 0;
        while (right < len ){

            if (nums[right] != 0){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
            }
            right++;
        }
        System.out.println(JSON.toJSONString(nums));
    }


}
