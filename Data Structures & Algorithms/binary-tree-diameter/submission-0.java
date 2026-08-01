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
    int diameter=0;
    public int diameterOfBinaryTree(TreeNode root) {
        dia(root);
        return diameter;
    }

    public int dia(TreeNode root){
        if(root==null) return 0;

        int leftTree=dia(root.left);
        int rightTree=dia(root.right);

        int curr_diameter=leftTree+rightTree;

        diameter=Math.max(curr_diameter, diameter);

        return 1+Math.max(leftTree,rightTree);
    }
}
