package com.dixon.leetcode.leetcode;

import com.dixon.descapi.Method;
import com.dixon.descapi.Topic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.09
 * Functional desc:  LeetCode 题库 第107题 从底至上层次遍历二叉树
 */
@Topic(index = 107, topic = TopicOneHundredSeven.TOPIC, example = TopicOneHundredSeven.EXAMPLE)
public class TopicOneHundredSeven {

    public static final String TOPIC = "给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）";

    public static final String EXAMPLE = "给定二叉树 [3,9,20,null,null,15,7],\n" +
            "    3\n" +
            "   / \\\n" +
            "  9  20\n" +
            "    /  \\\n" +
            "   15   7\n" +
            "返回其自底向上的层次遍历为：\n" +
            "[\n" +
            "  [15,7],\n" +
            "  [9,20],\n" +
            "  [3]\n" +
            "]\n";

    @Method(index = 1,
            explanation = "BFS 层次遍历法",
            timeComplexity = "n",
            spaceComplexity = "n")
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int len = q.size();
            // 每次都把当前行遍历完
            for (int i = 0; i < len; i++) {
                TreeNode node = q.poll();
                tmp.add(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            // 在索引 0 的位置加入一维数组 tmp
            // 每次新的数组进来都会被放在开始的位置
            ans.add(0, tmp);
        }
        return ans;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
