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
    List<Integer> res;
    public int kthSmallest(TreeNode root, int k) {
        res=new ArrayList<>();
        node(root, k);
        return res.get(k-1);
    }

    public void node(TreeNode root, int k) {
        if(root==null) return;
        node(root.left, k);
        res.add(root.val);
        node(root.right, k);

    }
}
