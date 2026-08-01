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

class Solution {
    private int count;
    private int result;

    public int kthSmallest(TreeNode root, int k) {
        this.count = k;
        traverse(root);
        return result;
    }

    private void traverse(TreeNode root) {
        // Base case, or stop early if we already found the result
        if (root == null || count == 0) return;

        // 1. Go Left
        traverse(root.left);

        // 2. Process Current Node
        count--;
        if (count == 0) {
            result = root.val;
            return; // Found it! Stop exploring this subtree.
        }

        // 3. Go Right
        traverse(root.right);
    }
}