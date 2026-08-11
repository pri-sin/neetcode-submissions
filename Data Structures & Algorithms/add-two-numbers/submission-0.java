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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res=new ListNode(0);

        ListNode iter=res;
        int carry=0;

        while(l1!=null && l2!=null){
            int sum=l1.val+l2.val+carry;
            carry=sum/10;
            ListNode newNode=new ListNode(sum%10);
            iter.next=newNode;
            iter=iter.next;
            l1=l1.next;
            l2=l2.next;
        }

        while(l1!=null){
            ListNode newNode=new ListNode((l1.val+carry)%10);
            carry=(l1.val+carry)/10;
            iter.next=newNode;
            iter=iter.next;
            l1=l1.next;
        }

        while(l2!=null){
            ListNode newNode=new ListNode((l2.val+carry)%10);
            carry=(l2.val+carry)/10;
            iter.next=newNode;
            iter=iter.next;
            l2=l2.next;
        }

        if(carry!=0){
            ListNode newNode=new ListNode(carry);
            iter.next=newNode;
        }

        return res.next;
    }
}
