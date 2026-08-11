/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {

        Node dummy=head;
        Node res=new Node(0);
        HashMap<Node, Node> map=new HashMap();

        Node headres=res;

        while(dummy!=null){
            Node newNode=new Node(dummy.val);
            map.put(dummy, newNode);
            res.next=newNode;
            res=res.next;
            dummy=dummy.next;
        }

        Node headres2=headres;
        headres2=headres2.next;

        while(head!=null){
            headres2.random=map.get(head.random);
            head=head.next;
            headres2=headres2.next;
        }

        return headres.next;
        
    }
}
