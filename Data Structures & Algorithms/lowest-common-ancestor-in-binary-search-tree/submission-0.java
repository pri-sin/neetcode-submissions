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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. Base case: If the node doesn't exist, pass null back up
        if (root == null) return null;

        // 2. VALUE CHECK (Prevents NullPointerException):
        // Always compare using 'root.val == p.val' instead of reference equality 'root == p'.
        // If LeetCode creates p and q as separate memory objects, 'root == p' will fail,
        // cause the function to return null, and crash the runner with a NullPointerException.
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        // 3. Post-Order Traversal: Search left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 4. Decision Logic:
        // - If p and q were found in different branches, 'root' is their LCA.
        if (left != null && right != null) {
            return root;
        }

        // - Otherwise, pass up whichever subtree returned a non-null node.
        return left != null ? left : right;
    }
}