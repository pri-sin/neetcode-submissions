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
    int result;
    public int kthSmallest(TreeNode root, int k) {
        count=k;
        node(root, k);
        return result;
    }

    public void node(TreeNode root, int k) {
        if(root==null || count==0) return;
        node(root.left, k);

        count--;
        if(count==0){
            result=root.val;
            return;
        }
        node(root.right, k);
    }
}
