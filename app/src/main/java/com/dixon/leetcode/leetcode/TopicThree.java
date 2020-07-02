package com.dixon.leetcode.leetcode;

import com.dixon.descapi.Method;
import com.dixon.descapi.Topic;

import java.util.HashSet;
import java.util.Set;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.02
 * Functional desc: LeetCode 题库 第三题
 */
@Topic(index = 3, topic = TopicThree.TOPIC, example = TopicThree.EXAMPLE)
public class TopicThree {

    public static final String TOPIC = "给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。";

    public static final String EXAMPLE = "示例 1:\n" +
            "输入: \"abcabcbb\"\n" +
            "输出: 3 \n" +
            "解释: 因为无重复字符的最长子串是 \"abc\"，所以其长度为 3。\n" +
            "示例 2:\n" +
            "输入: \"bbbbb\"\n" +
            "输出: 1\n" +
            "解释: 因为无重复字符的最长子串是 \"b\"，所以其长度为 1。\n" +
            "示例 3:\n" +
            "输入: \"pwwkew\"\n" +
            "输出: 3\n" +
            "解释: 因为无重复字符的最长子串是 \"wke\"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，\"pwke\" 是一个子序列，不是子串。\n";

    @Method(index = 1,
            explanation = "滑动窗口扫描，核心是左右指针各遍历一次，右指针移动时遇到重复，则左指针移动，直到没有重复或遍历完毕为止。\n\n" +
                    "提示，对于 Java String：\n" +
                    "charAt：时间复杂度 O(1)，源码为 value[index]； \n" +
                    "contains：时间复杂度 O(n*m)；\n" +
                    "subString：时间复杂度 O(n)，beginIndex 包括在内，endIndex 不包括在内；\n" +
                    "所以判重不能用 String，而要考虑用 Hash 集合。 \n\n" +
                    "∣Σ∣ 表示所用字符集的总大小，因为 Hash 集合最大存储所有出现的字符。 \n",
            timeComplexity = "n",
            spaceComplexity = "∣Σ∣"
    )
    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
