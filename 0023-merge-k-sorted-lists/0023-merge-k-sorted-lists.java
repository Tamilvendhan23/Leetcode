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
// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {
        
//     }
// }
// import java.util.PriorityQueue;
 

  class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Create a priority queue (min-heap) to store nodes
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each linked list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        // Dummy node to form the result linked list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Process nodes from the heap
        while (!minHeap.isEmpty()) {
            // Get the smallest node
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            // If there are more nodes in the list, add them to the heap
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        // Return the merged linked list
        return dummy.next;
    }
}
