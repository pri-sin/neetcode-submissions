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
    int count;
    public int goodNodes(TreeNode root) {
        count=0;
        getGoodNodes(root, root.val);
        return count;
    }

    public void getGoodNodes(TreeNode root, int maxSoFar){
        if(root==null) return;

        if(root.val>=maxSoFar){
            count++;
            maxSoFar=root.val;
        }

        getGoodNodes(root.left, maxSoFar);
        getGoodNodes(root.right, maxSoFar);
    }
}
