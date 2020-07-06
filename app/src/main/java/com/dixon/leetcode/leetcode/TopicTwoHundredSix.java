package com.dixon.leetcode.leetcode;

import com.dixon.descapi.Method;
import com.dixon.descapi.Topic;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.06
 * Functional desc: LeetCode 题库 第206题 反转链表
 */
@Topic(index = 206, topic = TopicTwoHundredSix.TOPIC, example = TopicTwoHundredSix.EXAMPLE)
public class TopicTwoHundredSix {

    public static final String TOPIC = "反转一个单链表。\n" +
            "其中单链表数据结构 ListNode：\n" +
            "private static class ListNode {\n" +
            "    int val;\n" +
            "    ListNode next;\n" +
            "    ListNode(int x) {\n" +
            "        val = x;\n" +
            "    }\n" +
            "}";

    public static final String EXAMPLE = "输入: 1->2->3->4->5->NULL\n" +
            "输出: 5->4->3->2->1->NULL";


    @Method(index = 1,
            explanation = "迭代法。\n" +
                    "在遍历列表时，将当前节点的 next 指针改为指向前一个元素。\n" +
                    "举例：1->2->3->null\n" +
                    "第1次：1->null cur=2 prev=1\n" +
                    "第2次：2->1 cur=3 prev=2\n" +
                    "第3次：3->2 cur=null prev=3\n" +
                    "结束。",
            timeComplexity = "n",
            spaceComplexity = "1")
    public static ListNode reverseListA(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    // node.next=null 应当理解为 node 的下一位指向 null
    // 而不应该理解为 node 的下一位的 node 的指向为 null，因为 next 代表的才是本 node 的下一位指向
    // 即 a.next=2 表示的是 a 下一位指向 2，而不是 a 的下一位 a.next 指向 2！
    @Method(index = 2,
            explanation = "递归法。\n" +
                    "举例：1->2-3->null\n" +
                    "先一直往下递归，直到head=3 return 3\n" +
                    "第1次：node=2 node.next=3 3->2 2->null \n" +
                    "第2次：node=1 node.next=2 2->1 1->null\n" +
                    "结束。\n" +
                    "提示：\n" +
                    "node.next=null 应当理解为 node 的下一位指向 null，\n" +
                    "而不应该理解为 node 的下一位的 node 的指向为 null，因为 next 代表的才是本 node 的下一位指向，\n" +
                    "即假如原 a.next=b（a 下一位指向 b），则 a.next=c 表示的是 a 下一位转而指向 c，而不是 a 的原下一位 b 指向 c！\n" +
                    "同理，node.next.next = node 可以理解为：nodeA 的下一位 nodeB 的指向为 nodeA。",
            timeComplexity = "n",
            spaceComplexity = "n")
    public ListNode reverseListB(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseListB(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
