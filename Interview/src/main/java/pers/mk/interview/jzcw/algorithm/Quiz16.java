package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

// 88. 合并两个有序数组
public class Quiz16 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = new int[]{2,5,6};
        int n = 3;

        // 逻辑复杂
//        mk(nums1,m,nums2,n);

        // 合并 + 排序
//        m1(nums1,m,nums2,n);

        // 逆向双指针
        m2(nums1,m,nums2,n);
    }

    private static void mk(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0 ){
            for (int i = 0 ; i < m + n ; i++){
                nums1[i] = nums2[i];
            }
            return;
        }
        if (n == 0){
            return;
        }

        int end = m - 1;
        int start2 = 0;
        for (int i = 0 ; i < m + n ; i++){
            for (int j = start2 ; j < n ; j++ ){
                if (nums1[i] > nums2[j]){
                    for (int k = end ; k >= i ; k--){
                        nums1[k + 1] = nums1[k];
                    }
                    end++;
                    start2++;
                    nums1[i] = nums2[j];
                }else if (i > end && nums1[i] == 0){
                    nums1[i] = nums2[j];
                    start2++;
                    break;
                }
            }
        }
        System.out.println(JSON.toJSONString(nums1));
    }


    private static void m1(int[] nums1, int m, int[] nums2, int n){
        for(int i = 0 ; i < n ; i++ ){
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
        System.out.println(JSON.toJSONString(nums1));
    }

    private static void m2(int[] nums1, int m, int[] nums2, int n){
        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n -1;
        int cur;
        while (p1 >= 0 || p2 >= 0){
            if (p1 == -1){
                cur = nums2[p2--];
            }else if (p2 == -1){
                cur = nums1[p1--];
            }else if (nums1[p1] > nums2[p2]){
                cur = nums1[p1--];
            }else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
        System.out.println(JSON.toJSONString(nums1));
    }

}
