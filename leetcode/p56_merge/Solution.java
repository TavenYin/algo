package leetcode.p56_merge;

import java.util.ArrayList;
import java.util.Arrays;

// https://leetcode.cn/problems/merge-intervals/

public class Solution {
    public int[][] merge(int[][] intervals) {
        var result = new ArrayList<int[]>();
        // 顺序排序
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        for (int i = 0; i < intervals.length;) {
            // 合并条件: intervals[i][1] >= intervals[i+1][0]
            var nextIndex = i + 1;
            var currentInterval = intervals[i];
            while (nextIndex < intervals.length && currentInterval[1] >= intervals[nextIndex][0]) {
                // 合并
                currentInterval = new int[] {currentInterval[0], Math.max(currentInterval[1], intervals[nextIndex][1])};
                nextIndex++;
            }
            i = nextIndex;
            result.add(currentInterval);
        }

        return result.toArray(new int[result.size()][]);
    }
}
