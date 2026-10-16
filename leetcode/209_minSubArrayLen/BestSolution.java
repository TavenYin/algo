class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        var left = 0;
        var sum = 0;
        var minLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            // 窗口右移后, 计算 sum
            sum += nums[right];

            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}