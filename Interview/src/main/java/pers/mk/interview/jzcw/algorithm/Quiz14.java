package pers.mk.interview.jzcw.algorithm;

// 704. 二分查找
public class Quiz14 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,3,5,9,12};
        int target = 2;
        System.out.println(mk(nums,target));
    }

    private static int mk(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1]){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right){
            int mid = left + right >> 1;
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                index = mid;
                break;
            }
        }
        return index;
    }
}
