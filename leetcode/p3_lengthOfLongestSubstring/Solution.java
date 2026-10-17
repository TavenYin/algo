package leetcode.p3_lengthOfLongestSubstring;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        var charArray = s.toCharArray();
        var mask = new long[2];
        var result = 0;
        var left = 0;
        for (int right = 0; right < charArray.length; right++) {
            var rightE = (int) charArray[right];
            var rightEIndex = rightE / 64;
            var rightEBit = 1L << rightE % 64;

            // 与运算, 如果返回 == 0 代表没有重复字符
            while ( (mask[rightEIndex] & rightEBit) != 0) {
                // 包含重复字符
                result = Math.max(result, right - left);

                // 从 mask 中删除 left 元素
                var leftE = (int) charArray[left++];
                var leftEIndex = leftE / 64;
                var leftEBit = 1L << leftE % 64;
                mask[leftEIndex] &= ~leftEBit;
            }

            // right 元素添加到 mask
            mask[rightEIndex] |= rightEBit;
        }
        return Math.max(result, charArray.length - left);
    }

    public static void main(String[] args) {
        // System.err.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
        System.err.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    }
}
