package com.dixon.leetcode.leetcode;

import com.dixon.descapi.Method;
import com.dixon.descapi.Topic;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.03
 * Functional desc: LeetCode 题库第四题
 */
@Topic(index = 4, topic = TopicFour.TOPIC, example = TopicFour.EXAMPLE)
public class TopicFour {

    public static final String TOPIC = "给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。\n" +
            "请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。\n" +
            "你可以假设 nums1 和 nums2 不会同时为空。\n" +
            "中位数求法：\n" +
            "20、21、23、23、25、29、32、33，中位数为 24；\n" +
            "0、20、 20、 20、 30，中位数为 20。\n" +
            "俩个数组的中位数即数组合并后的中位数，但解题不一定要合并。";

    public static final String EXAMPLE = "示例 1:\n" +
            "nums1 = [1, 3]\n" +
            "nums2 = [2]\n" +
            "则中位数是 2.0\n" +
            "示例 2:\n" +
            "nums1 = [1, 2]\n" +
            "nums2 = [3, 4]\n" +
            "则中位数是 (2 + 3)/2 = 2.5";

    @Method(index = 1,
            explanation = "暴力合并解法",
            timeComplexity = "m+n",
            spaceComplexity = "m+n")
    public static double findMedianSortedArraysA(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {
                return nums2[n / 2];
            }
        }
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }
        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }
            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }
        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }

    @Method(index = 2,
            explanation = "扫描法，比直接合并数组的空间复杂度要低。\n不满足题目要求，但是思路很好值得一看。",
            timeComplexity = "m+n",
            spaceComplexity = "1")
    public static double findMedianSortedArraysB(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }
        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    @Method(index = 3,
            explanation = "待补充，先学习基础排序算法与基础数据结构！",
            timeComplexity = "",
            spaceComplexity = "")
    public static double findMedianSortedArraysC(int[] nums1, int[] nums2) {

        return 0;
    }

    @Method(index = 4,
            explanation = "占位！",
            timeComplexity = "",
            spaceComplexity = "")
    public static double findMedianSortedArraysD(int[] nums1, int[] nums2) {

        return 0;
    }
}
