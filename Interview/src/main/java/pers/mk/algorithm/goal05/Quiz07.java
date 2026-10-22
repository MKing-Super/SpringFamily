package pers.mk.algorithm.goal05;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Quiz07 {
    public static void main(String[] args) {
        // 审题！是子数组中最多元素出现k次！
//        mk();


        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        int len = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        String[] arr = in.nextLine().trim().split(" ");
        List<String> list = Arrays.asList(arr);

        HashSet<List<String>> set = new HashSet<>();
        for (int i = 1 ; i <= arr.length ; i++){

            for (int left = 0 ; left < arr.length ; left++){
                ArrayList<String> tempList = new ArrayList<>();
                for (int right = left ; right < left + i && right < arr.length ; right++){
                    tempList.add(arr[right]);
                }
                set.add(tempList);
            }

        }

        List<List<String>> lenList = new ArrayList<>(set);
        lenList.sort((a,b) -> {
            return a.size() - b.size();
        });
//        System.out.println(JSON.toJSONString(lenList));

        int count = 0;
        for (int i = 0 ; i < lenList.size() ; i++){
            List<String> tempList = lenList.get(i);
            int tempLen = tempList.size();
            int tempCount = 0;
            for (int left = 0 ; left < list.size() && left + tempLen <= list.size() ; left++){
                List<String> strings = list.subList(left, left + tempLen);
                boolean pass = true;
                for (int t = 0 ; t < tempList.size() ; t++){
                    if (!strings.get(t).equals(tempList.get(t))){
                        pass = false;
                        break;
                    }
                }
                if (pass){
                    tempCount++;
                }
            }
            if (tempCount >= k){
                count++;
            }

        }

        System.out.println(count);
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().trim().split(" ");
        int len = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        String[] arrStr = in.nextLine().trim().split(" ");

        int[] nums = new int[len];
        for (int i = 0 ; i < len ; i ++){
            nums[i] = Integer.parseInt(arrStr[i]);
        }

        int count = 0;

        for (int left = 0 ; left < len ; left++){
            HashMap<Integer, Integer> freqMap = new HashMap<>();
            int maxFreq = 0 ;

            for (int right = left ; right < len ; right++){
                int val = nums[right];
                freqMap.put(val,freqMap.getOrDefault(val,0) + 1);
                int curFreq = freqMap.get(val);
                maxFreq = Math.max(maxFreq,curFreq);

                if (maxFreq >= k){
                    count++;
                }
            }
        }
        System.out.println(count);
    }


}
