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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode prev=new ListNode(0);
        ListNode iter=head;

        int count=1;
        while(count!=left){
            prev=iter;
            iter=iter.next;
            count++;
        }

        ListNode leftmostprev=prev;
        ListNode leftmost=iter;

        //reverse
        while(count<=right){
            ListNode nextn=iter.next;
            iter.next=prev;
            prev=iter;
            iter=nextn;
            count++;
        }

        leftmostprev.next=prev;
        leftmost.next=iter;

        return left==1?prev:head;

    }
}