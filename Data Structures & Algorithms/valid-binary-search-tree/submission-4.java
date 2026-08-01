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
    public boolean isValidBST(TreeNode root) {
        // Use null instead of -1000 and 1000 to represent "no limit yet"
        return validate(root, null, null);
    }

    public boolean validate(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        // Check if root.val violates the minimum boundary (if it exists)
        if (min != null && root.val <= min) {
            return false;
        }

        // Check if root.val violates the maximum boundary (if it exists)
        if (max != null && root.val >= max) {
            return false;
        }

        // THE FIX: Recursively validate left and right subtrees, updating the bounds
        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }
}