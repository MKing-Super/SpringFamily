package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 15. 三数之和
public class Quiz03 {

    public static void main(String[] args) {
        int[] nums = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};


        // 思路错误！！！
        // [-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0]
        // [0,0,0,0]
//        System.out.println(JSON.toJSONString(mk(nums)));

        // 思路：双指针
        System.out.println(JSON.toJSONString(m1(nums)));


    }

    private static List<List<Integer>> mk(int[] nums){
        List<List<Integer>> arrList = new ArrayList<>();
        for (int i = 0 ; i < nums.length ; i++){
            for (int j = i + 1 ; j < nums.length ; j++){
                for (int k = j + 1 ; k < nums.length ; k++){
                    if (nums[i] + nums[j] + nums[k] == 0){
                        boolean pass = true;
                        for (int h = 0 ; h < arrList.size() ; h++){
                            List<Integer> integers = arrList.get(h);
//                            HashMap<Integer, Integer> freq = new HashMap<>();
//                            freq.put(integers.get(0),freq.getOrDefault(integers.get(0),0) + 1);
//                            freq.put(integers.get(1),freq.getOrDefault(integers.get(1),0) + 1);
//                            freq.put(integers.get(2),freq.getOrDefault(integers.get(2),0) + 1);
                            if (integers.contains(nums[i]) && integers.contains(nums[j]) && integers.contains(nums[k])  ){
                                pass = false;
                            }
//                            if (freq.containsKey(nums[i]) && freq.containsKey(nums[j]) && freq.containsKey(nums[k]) ){
//                                if (freq.get(nums[i]) - 1 < 0 ){
//                                    pass = false;
//                                }
//                                freq.put(nums[i],freq.get(nums[i]) - 1);
//                                if (freq.get(nums[j]) - 1 < 0 ){
//                                    pass = false;
//                                }
//                                freq.put(nums[j],freq.get(nums[j]) - 1);
//                                if (freq.get(nums[k]) - 1 < 0 ){
//                                    pass = false;
//                                }
//                                freq.put(nums[k],freq.get(nums[k]) - 1);
//                            }
                        }
                        if (nums[i] == 0 && nums[j] == 0 && nums[k] == 0){
                            pass = true;
                        }
                        if (pass){
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[k]);
                            if (!arrList.contains(temp)){
                                arrList.add(temp);
                            }

                        }

                    }
                }
            }
        }


        arrList.sort((a,b) -> {
            if (!a.get(0).equals(b.get(0))){
                return a.get(0) - b.get(0);
            }else if (!a.get(1).equals(b.get(1))){
                return a.get(1) - b.get(1);
            }else if (!a.get(2).equals(b.get(2))){
                return a.get(2) - b.get(2);
            }else {
                return 0;
            }
        });
        return arrList;
    }

    private static List<List<Integer>> m1(int[] nums){
        ArrayList<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3){
            return ans;
        }
        int len = nums.length;

        Arrays.sort(nums);

        for (int i = 0 ; i < len ; i++){
            if (nums[i] > 0){
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if (sum < 0) {
                    left++;
                }else if (sum > 0){
                    right--;
                }
            }
        }
        return ans;
    }

}
