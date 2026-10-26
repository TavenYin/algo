package leetcode.p49_groupAnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        var hashMap = new HashMap<String, List<String>>();
        for (int index = 0; index < strs.length; index++) {
            var str = strs[index];
            var charArray = str.toCharArray();
            Arrays.sort(charArray);
            hashMap.computeIfAbsent(new String(charArray), (k) -> new ArrayList<String>()).add(str);
        }
        return hashMap.values().stream().toList();
    }
    public static void main(String[] args) {
        new Solution().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }
}