package leetcode.p11_maxArea;

public class Solution {
    public int maxArea(int[] height) {
        var left = 0;
        var right = height.length - 1;
        var maxArea = 0;
        while (left <= right) {
            var leftHeight = height[left];
            var rightHeight = height[right];
            var width = right - left;
            var area = Math.min(leftHeight, rightHeight) * width;
            maxArea = Math.max(maxArea, area);
            if (leftHeight > rightHeight) {
                right--;
            } else if (leftHeight < rightHeight) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        return maxArea;
    }
}
