/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // Map original nodes to their cloned counterparts
        Map<Node, Node> map = new HashMap<>();
        
        return clone(node, map);
    }

    private Node clone(Node node, Map<Node, Node> map) {
        // Base case: If already cloned, return the existing clone reference
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // 1. Create clone
        Node copy = new Node(node.val);
        map.put(node, copy);

        // 2. Clone all neighbors
        for (Node neighbor : node.neighbors) {
            // Recursion either clones the neighbor or retrieves its existing copy
            copy.neighbors.add(clone(neighbor, map));
        }

        return copy;
    }
}