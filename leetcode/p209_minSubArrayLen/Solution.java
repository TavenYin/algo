package leetcode.p209_minSubArrayLen;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        var left = 0;
        var right = 0;
        var sum = nums[0];
        var minLen = Integer.MAX_VALUE;
        for (;;) {
            // 符合总和大于 target
            if (sum >= target) {
                // 尝试更新 minLen
                var currentLen = right - left + 1;
                if (currentLen < minLen) {
                    minLen = currentLen;
                }

                if (left + 1 == nums.length) 
                {
                    break;
                }
                // 推动窗口 left 索引前进
                if (left == right) {
                    left = ++right;
                    sum = nums[left];
                } else {
                    sum -= nums[left++];
                }
            } 
            // sum < target
            else {
                if (right + 1 == nums.length) 
                {
                    break;
                }
                // 推动 right 索引前进
                sum += nums[++right];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    public static void main(String[] args) {
        
    }
}
