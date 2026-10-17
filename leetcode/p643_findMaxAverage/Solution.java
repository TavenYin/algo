package leetcode.p643_findMaxAverage;

public class Solution {
    public double findMaxAverage(int[] nums, int k) {
        var tempSum = 0;
        int maxSum = Integer.MIN_VALUE;
        var left = 0;
        for (int right = 0; right < nums.length; right++) {
            tempSum += nums[right];
            if (right + 1 >= k) {
                maxSum = Math.max(tempSum, maxSum);
                tempSum -= nums[left++];
            }
        }
        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        var nums = new int[] {-1};
        var k = 1;
        System.out.println(new Solution().findMaxAverage(nums, k));
    }
}
