/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public void reorderList(ListNode head) {
        //fast slow pointer to find mid point
        ListNode slow=head, fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        //slow is the mid point now
        ListNode l2=slow.next;
        slow.next=null;
        ListNode prev=null;

        //reversing the second half of linked list
        while(l2!=null){
            ListNode curr=l2.next;
            l2.next=prev;
            prev=l2;
            l2=curr;
        }
        l2=prev;

        
        //merging two linked lists
        ListNode l1=head;
        while(l2!=null){
            ListNode nextL1=l1.next;
            ListNode nextL2=l2.next;
            
            l1.next=l2;
            l2.next=nextL1;

            l1=nextL1;
            l2=nextL2;
        }
    }
}
