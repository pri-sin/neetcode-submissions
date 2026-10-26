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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        Queue<TreeNode> q=new ArrayDeque<>();
        if(root==null) return res;
        q.offer(root);

        while(!q.isEmpty()){
            int size=q.size();
            int maxnum=Integer.MIN_VALUE;
            for(int i=0;i<size;i++){
                TreeNode curr=q.poll();
                maxnum=Math.max(maxnum, curr.val);

                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
            }
            res.add(maxnum);
        }

        return res;
    }
}
