package com.dixon.leetcode.leetcode;

import com.dixon.descapi.Method;
import com.dixon.descapi.Topic;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.09
 * Functional desc: LeetCode 题库 第203题 删除链表元素
 */
@Topic(index = 203, topic = TopicTwoHundredThree.TOPIC, example = TopicTwoHundredThree.EXAMPLE)
public class TopicTwoHundredThree {

    public static final String TOPIC = "删除链表中等于给定值 val 的所有节点。";

    public static final String EXAMPLE = "示例:\n" +
            "输入: 1->2->6->3->4->5->6, val = 6\n" +
            "输出: 1->2->3->4->5";

    @Method(index = 1,
            explanation = "哨兵节点。\n举个例子，1->2->2->3，val = 2，\n" +
                    "第一次：进入时，prev = 0，cur = 1，prev.next = 1，结束时，prev = 1，cur = 2\n" +
                    "第二次：进入时，prev = 1，cur = 2(第一个2)，prev.next = 2(第一个2)，结束时，prev = 1，cur = 2(第二个2)\n" +
                    "第三次：进入时，prev = 1，cur = 2(第二个2)，prev.next = 3，结束时，prev = 1，cur = 3\n" +
                    "第四次：进入时，prev = 1，cur = 3，prev.next = 3，结束时，prev = 3，cur = null\n" +
                    "结束。\n" +
                    "总结：原链表已有顺序，只需要【延用】，并把多余的节点去掉即可。",
            timeComplexity = "N",
            spaceComplexity = "1")
    public static ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) prev.next = curr.next;
            else prev = curr;
            curr = curr.next;
        }
        return sentinel.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
