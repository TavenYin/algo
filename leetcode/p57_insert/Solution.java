package leetcode.p57_insert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][] {newInterval};
        }

        var intervalList = new ArrayList<>(List.of(intervals));

        // 找到一个空隙
        var insertIndex = searchInterval(intervals, newInterval);
        var insertIndexElement = insertIndex >= intervals.length || insertIndex < 0 ? null : intervalList.get(insertIndex);
        
        // newInterval 尝试和 insertIndex 合并
        if (insertIndexElement != null && (newInterval[1] >= insertIndexElement[0])) {
            insertIndexElement = new int[]{ Math.min(newInterval[0], insertIndexElement[0]), Math.max(newInterval[1], insertIndexElement[1]) };
            intervalList.set(insertIndex, insertIndexElement);
        } else {
            insertIndexElement = newInterval;
            if (insertIndex >= intervals.length) {
                intervalList.add(insertIndexElement);
                return intervalList.toArray(new int[intervalList.size()][]);
            } else if (insertIndex < 0) {
                intervalList.addFirst(insertIndexElement);
                return intervalList.toArray(new int[intervalList.size()][]);
            } else {
                intervalList.add(insertIndex, insertIndexElement);
            }
        }

        // 尝试向左合并
        var leftIndex = insertIndex - 1;
        int[] tempInterval = null;
        while (insertIndexElement != null && leftIndex >= 0 && (tempInterval = intervalList.get(leftIndex))[1] >= insertIndexElement[0]) {
            insertIndexElement = new int[]{ Math.min(tempInterval[0], insertIndexElement[0]), Math.max(tempInterval[1], insertIndexElement[1]) };
            intervalList.set(leftIndex, insertIndexElement);
            intervalList.remove(insertIndex);
            insertIndex = leftIndex;
            leftIndex--;
        }
        
        // 尝试向右合并
        var rightIndex = insertIndex + 1;
        while (insertIndexElement != null && rightIndex <= intervalList.size() - 1 && insertIndexElement[1] >= (tempInterval = intervalList.get(rightIndex))[0]) {
            insertIndexElement = new int[]{ Math.min(tempInterval[0], insertIndexElement[0]), Math.max(tempInterval[1], insertIndexElement[1]) };
            intervalList.set(insertIndex, insertIndexElement);
            intervalList.remove(rightIndex);
        }

        return intervalList.toArray(new int[intervalList.size()][]);
    }

    // 二分查找, 找到合适的空隙
    public int searchInterval(int[][] intervals, int[] newInterval) {
        int left = 0, right = intervals.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int[] midInterval = intervals[mid];

            // 假设 newInterval 刚好能插入 mid 的左侧
            // if (mid-1 >= 0 && compareInterval(intervals[mid-1], newInterval) == -1 && compareInterval(newInterval, intervals[mid]) == -1) {
            //     return mid;
            // }

            // // 假设 newInterval 刚好能插入 mid 的右侧
            // if (mid+1 <= intervals.length - 1 && compareInterval(intervals[mid], newInterval) == -1 && compareInterval(newInterval, intervals[mid + 1]) == -1) {
            //     return mid + 1;
            // }

            var compareResult = compareInterval(midInterval, newInterval);
            if (compareResult == 0) {
                // 当前元素和 newInterval 可以合并
                return mid;
            } else if (compareResult == -1) {
                // intervals[mid] < target
                left = mid + 1;
            } else if (compareResult == 1) {
                // intervals[mid] > target
                right = mid - 1;
            }
        }
        return left;
    }

    public int compareInterval(int[] interval1, int[] interval2) {
        int[] left = null;
        int[] right = null;
        
        if (interval1[0] == interval2[0]) {
            return 0;
        } else if (interval1[0] < interval2[0]) {
            left = interval1;
            right = interval2;
        } else {
            left = interval2;
            right = interval1;
        }

        if (left[1] >= right[0]) {
            // 有交集
            return 0;
        } else if (interval2[0] > interval1[0]) {
            // interval2 > interval1
            return -1;
        } else {
            // interval1 > interval2
            return 1;
        }
    }

    public static void main(String[] args) {
        // 1. 直接把 LeetCode 的输入贴在双引号里
        String input = "[[3,5],[12,15]]";
        
        // 2. 核心转换逻辑：利用正则和 Stream 流一气呵成
        int[][] intervals = Arrays.stream(input.substring(2, input.length() - 2).split("\\],\\["))
                .map(row -> Arrays.stream(row.split(",")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        int[] newInterval = new int[] {6,6};
        System.out.println(Arrays.deepToString(new Solution().insert(intervals, newInterval)));
    }
}