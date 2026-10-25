package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;

// 33. 搜索旋转排序数组
// 二分 变体
public class Quiz15 {

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;

        // 思路不清晰！！！
//        System.out.println(mk(nums,target));


        // 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
        // 此时有序部分用二分法查找。
        // 无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
        System.out.println(m1(nums,target));
    }



    private static int mk(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + right >> 1;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] > target){
                if (nums[left] <= nums[mid]){
                    right = mid - 1;
                }else {

                }
            }
        }
        return -1;
    }


    private static int m1(int[] nums, int target){
        System.out.println(JSON.toJSONString(nums));
        int len = nums.length;
        if (len == 0){
            return -1;
        }
        if (len == 1){
            if (nums[0] == target){
                return 0;
            }else {
                return -1;
            }
        }

        int left = 0;
        int right = len - 1;
        while (left <= right){
            int mid = left + right >> 1;
            System.out.println(left + " " + right + " " +mid + " = " + nums[mid]);
            if (nums[mid] == target){
                return mid;
            }
            if (nums[0] <= nums[mid]){
                if (nums[0] <= target && target < nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                if (nums[mid] <= target && target <= nums[len - 1]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
