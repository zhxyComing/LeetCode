package com.dixon.leetcode.leetcode;

import com.dixon.descapi.Method;
import com.dixon.descapi.Topic;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: LeetCode 题库 第二题
 */
@Topic(index = 2, topic = TopicTwo.TOPIC, example = TopicTwo.example)
public class TopicTwo {

    public static final String TOPIC = "给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。\n" +
            "如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。\n" +
            "您可以假设除了数字 0 之外，这两个数都不会以 0 开头。";

    public static final String example = "输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)\n" +
            "输出：7 -> 0 -> 8\n" +
            "原因：342 + 465 = 807";

    @Method(index = 1,
            explanation = "链表，其中ListNode为：\n\n" +
                    "public static class ListNode {\n" +
                    "   int val;\n" +
                    "   ListNode next;\n" +
                    "   ListNode(int x) {\n" +
                    "       val = x;\n" +
                    "   }\n" +
                    "}\n" +
                    "m、n 表示链表长度。\n",
            timeComplexity = "max(m,n)",
            spaceComplexity = "max(m,x)")
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
