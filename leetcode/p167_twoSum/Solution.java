package leetcode.p167_twoSum;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            var result = numbers[left] + numbers[right];
            if (result == target) {
                return new int[] {left, right};
            } else if (result > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        System.err.println(new Solution().twoSum(new int[] {2,7,11,15}, 9));
    }

}
