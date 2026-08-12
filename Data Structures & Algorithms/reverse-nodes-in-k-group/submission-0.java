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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode iter=head;
        int count=0;

        while(iter!=null && count<k){
            iter=iter.next;
            count++;
        }

        if(count==k){
            ListNode reversedHead=reverseKGroup(iter, k);

            while(count>0){
                ListNode temp=head.next;
                head.next=reversedHead;
                reversedHead=head;
                head=temp;
                count--;
            }

            head=reversedHead;
        }
        return head;
    }
}
