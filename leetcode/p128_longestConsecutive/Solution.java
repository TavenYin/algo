package leetcode.p128_longestConsecutive;

import java.util.HashMap;

public class Solution {

    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        // key nums[i], value 当前连续长度 (只维护最左和最右)
        var hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];
            if (hashMap.containsKey(num)) {
                continue;
            }

            var currentLeftValue = hashMap.get(num - 1);
            var currentRightValue = hashMap.get(num + 1);

            var v = 0;
            if (currentLeftValue != null && currentRightValue != null) {
                v = currentLeftValue + currentRightValue + 1;
                hashMap.put(num - currentLeftValue, v);
                hashMap.put(num + currentRightValue, v);
                hashMap.put(num, v);
            } else if (currentLeftValue != null) {
                v = currentLeftValue + 1;
                hashMap.put(num - currentLeftValue, v);
                hashMap.put(num, v);
            } else if (currentRightValue != null) {
                v = currentRightValue + 1;
                hashMap.put(num + currentRightValue, v);
                hashMap.put(num, v);
            } else {
                v = 1;
                hashMap.put(num, v);
            }

            if (v > maxLength) {
                maxLength = v;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{1,2,4,5,3,0,3,6,7}));
    }
}
