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
    private int preIdx = 0;
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Step 1: Map each value to its index in inorder array
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Step 2: Recursively build the tree
        return buildSubtree(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildSubtree(int[] preorder, int inLeft, int inRight) {
        // Base case: range is invalid (no nodes to add)
        if (inLeft > inRight) {
            return null;
        }

        // Pick current root element from preorder sequence
        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);

        // Find the split point in the inorder sequence
        int index = inorderMap.get(rootVal);

        // Construct left and right subtrees
        // Left subtree must be constructed first because preorder is (Root -> Left -> Right)
        root.left = buildSubtree(preorder, inLeft, index - 1);
        root.right = buildSubtree(preorder, index + 1, inRight);

        return root;
    }
}
