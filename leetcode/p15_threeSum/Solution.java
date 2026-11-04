package leetcode.p15_threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors; 

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        var hashMap = new HashMap<Integer, List<Integer>>();
        var set = new HashSet<String>();
        var result = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            var n = nums[i];
            hashMap.computeIfAbsent(n, (k) -> new ArrayList<Integer>()).add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            var first = nums[i];
            var target = 0 - first;
            var secondIndex = i + 1;
            while (secondIndex < nums.length - 1) {
                var second = nums[secondIndex];
                var third = target - second;
                var thirdIndexes = hashMap.get(third);
                var finalSencondIndex = secondIndex;
                if (thirdIndexes != null && thirdIndexes.getLast() > finalSencondIndex) {
                    var list = Arrays.asList(first, second, third);
                    Collections.sort(list);
                    var seen = list.stream().map(String::valueOf).collect(Collectors.joining(","));
                    if (set.add(seen)) {
                        result.add(list);
                    }
                }
                secondIndex++;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(new Solution().threeSum(new int[] {-1,0,1,2,-1,-4}));
    }
}
