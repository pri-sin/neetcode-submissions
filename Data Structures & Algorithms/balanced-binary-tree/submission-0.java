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
    public boolean isBalanced(TreeNode root) {
        return testBalance(root) != -1;
    }

    public int testBalance(TreeNode root) {
        if(root==null)
            return 0;

        int leftTree=testBalance(root.left);
        if(leftTree==-1) return -1;

        int rightTree=testBalance(root.right);
        if(rightTree==-1) return -1;

        if(Math.abs(rightTree-leftTree)>1){
            return -1;
        }
        
        return 1+Math.max(leftTree,rightTree);

    }

}
