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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        List<List<Integer>> result=new ArrayList<>();

        if(root==null){
            return result;
        }
        
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode dummy=q.poll();
                list.add(dummy.val);
                if(dummy.left!=null){
                    q.add(dummy.left);
                }
                if(dummy.right!=null){
                    q.add(dummy.right);
                }
            }
            result.add(list);
        }
        return result;
    }
}
