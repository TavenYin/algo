package leetcode.p1_twoSum;

import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 给每个元素创建索引
        var hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];
            var indexes = hashMap.get(target - num);
            if (indexes != null) {
                return new int[]{i, indexes};
            }
            hashMap.put(num, i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.err.println(new Solution().twoSum(new int[]{3,2,4}, 6));
    }
}
