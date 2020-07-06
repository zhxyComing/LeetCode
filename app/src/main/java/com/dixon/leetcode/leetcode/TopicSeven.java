package com.dixon.leetcode.leetcode;

import com.dixon.descapi.Method;
import com.dixon.descapi.Topic;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.06
 * Functional desc: LeetCode 题库 第七题
 */
@Topic(index = 7, topic = TopicSeven.TOPIC, example = TopicSeven.EXAMPLE)
public class TopicSeven {

    public static final String TOPIC = "给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。\n注意:\n" +
            "假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。";

    public static final String EXAMPLE = "示例 1:\n" +
            "输入: 123\n" +
            "输出: 321\n" +
            "示例 2:\n" +
            "输入: -123\n" +
            "输出: -321\n" +
            "示例 3:\n" +
            "输入: 120\n" +
            "输出: 21";

    @Method(index = 1,
            explanation = "弹出和推入数字 & 溢出前进行检查 \n反转方式虽然简单但很有启发，值得看！",
            timeComplexity = "log(x)",
            spaceComplexity = "1")
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
