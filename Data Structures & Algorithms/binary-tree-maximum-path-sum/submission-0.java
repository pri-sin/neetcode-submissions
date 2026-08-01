/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Solution {
    private int globalMax = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        gainFromSubtree(root);
        return globalMax;
    }

    private int gainFromSubtree(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively get maximum path sum from left and right subtrees.
        // Ignore negative sums by comparing with 0.
        int leftGain = Math.max(0, gainFromSubtree(node.left));
        int rightGain = Math.max(0, gainFromSubtree(node.right));

        // 1. Price of path where current node is the PEAK (connects left and right)
        int currentPeakSum = node.val + leftGain + rightGain;

        // Update the global maximum path sum found so far
        globalMax = Math.max(globalMax, currentPeakSum);

        // 2. Return max gain if current node continues UPWARDS to its parent
        return node.val + Math.max(leftGain, rightGain);
    }
}