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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode dummy=head;
        ListNode prev=null;

        while(dummy!=null && dummy.next!=null){
            int a=dummy.val;
            int b=dummy.next.val;

            int gcdnum=gcd(a,b);
            prev=dummy;
            ListNode nextn=dummy.next;
            ListNode newn=new ListNode(gcdnum);
            prev.next=newn;
            newn.next=nextn;
            dummy=nextn;
        }

        return head;
    }

    public int gcd(int a, int b){
        if(b==0){
            return a;
        }

        return gcd(b, a%b);
    }
}