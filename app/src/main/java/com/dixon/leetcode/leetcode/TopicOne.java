package com.dixon.leetcode.leetcode;

import com.dixon.descapi.Topic;
import com.dixon.descapi.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by: dixon.xu
 * Create on: 2020.06.30
 * Functional desc: LeetCode 题库第一题
 */
@Topic(index = 1, topic = TopicOne.TOPIC, example = TopicOne.EXAMPLE)
public class TopicOne {

    public static final String TOPIC = "给定一个整数数组nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。\n" +
            "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。";

    public static final String EXAMPLE = "给定 nums = [2, 7, 11, 15], target = 9\n" +
            "因为 nums[0] + nums[1] = 2 + 7 = 9\n" +
            "所以返回 [0, 1]";


    @Method(index = 1, explanation = "遍历", timeComplexity = "n^2", spaceComplexity = "1")
    public static int[] methodA(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    @Method(index = 2,
            explanation = "一遍哈希表 \n 在表中进行的每次查找只花费 O(1) 的时间",
            timeComplexity = "n",
            spaceComplexity = "n")
    public static int[] methodB(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
