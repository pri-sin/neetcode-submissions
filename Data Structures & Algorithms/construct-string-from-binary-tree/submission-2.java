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
/*
class Solution {
    public String tree2str(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        getString(root, sb);
        return sb.toString();
    }

    public void getString(TreeNode root, StringBuilder sb){
        if(root!=null){
            sb.append(root.val);
            if(root.left==null && root.right==null){
                return ;
            }

            if(root.left!=null){
                sb.append("(");
                getString(root.left,sb);
                sb.append(")");
            }else{
                sb.append("()");
            }

            if(root.right!=null){
                sb.append("(");
                getString(root.right,sb);
                sb.append(")");
            }
        }

    }
}//Gives NZEC Stack overflow error
*/
//On a heavily skewed tree, such as 10⁴ nodes each with only one child, getString calls itself once per level. Some judges give Java a small thread stack, and that many frames overflows it. NZEC (non-zero exit code) is how they report the crash.


class Solution {
    public String tree2str(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (visited.contains(node)) {
                // Done with this node's subtree: close its paren
                stack.pop();
                sb.append(")");
            } else {
                visited.add(node);
                sb.append("(").append(node.val);

                // Empty left child must be shown only if a right child exists
                if (node.left == null && node.right != null) {
                    sb.append("()");
                }
                // Push right first so left is processed first
                if (node.right != null) stack.push(node.right);
                if (node.left != null)  stack.push(node.left);
            }
        }

        // Strip the outer parens wrapped around the root
        return sb.substring(1, sb.length() - 1);
    }
}
