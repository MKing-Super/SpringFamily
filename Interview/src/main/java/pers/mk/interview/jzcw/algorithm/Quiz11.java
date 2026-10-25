package pers.mk.interview.jzcw.algorithm;

// 53. 最大子数组和
public class Quiz11 {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // 思路错误！用滑窗做不出来
//        System.out.println(mk(nums));

        // 动态规划
//        System.out.println(m1(nums));

//        System.out.println(11 >> 1);
        // 分治
        System.out.println(m2(nums));
    }


    private static int mk(int[] nums) {

        int left = 0;
        int maxTotal = Integer.MIN_VALUE;
        int sum = 0;
        for (int right = 0 ; right < nums.length ; right++){

            while (left <= right ){
                sum += nums[right];
                if (sum > maxTotal){
                    maxTotal = sum;
                    break;
                }
                if (nums[left] < 0){
                    sum -= nums[left];
                    left++;
                    continue;
                }
                left++;

            }
        }
        return maxTotal;
    }


    private static int m1(int[] nums){
        int pre = 0;
        int maxTotal = nums[0];
        for (int x : nums){
            pre = Math.max(pre + x,x);
            maxTotal = Math.max(maxTotal,pre);
        }
        return maxTotal;
    }


    private static int m2(int[] nums){
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    private static class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }
    private static  Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    private static  Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }


}
