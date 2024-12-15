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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases (e.g., removing the first node)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Initialize two pointers
        ListNode first = dummy;
        ListNode second = dummy;

        // Move `first` pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Move both pointers until `first` reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Remove the target node
        second.next = second.next.next;

        // Return the new head (dummy.next)
        return dummy.next;
    }
}
