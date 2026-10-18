package k_way_merge.merge_k_sorted_linked_lists;

import common.ListNode;

import java.util.PriorityQueue;

public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            tail.next = node;
            tail = node;
            if (node.next != null) {
                heap.offer(node.next);
            }
        }
        return dummy.next;
    }
}
