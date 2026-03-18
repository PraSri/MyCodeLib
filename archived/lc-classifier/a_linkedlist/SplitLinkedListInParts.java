package a_linkedlist;

import LinkedList.ListNode;

public class SplitLinkedListInParts {

    public ListNode[] splitListToParts(ListNode head, int k) {

        ListNode[] res = new ListNode[k];

        int size = 0;
        for (ListNode node = head; node != null; node = node.next) {
            size++;
        }

        int n = size / k;
        int r = size % k;

        ListNode node = head, prev = null;

        for (int i = 0; node != null & i < k; i++, r--) {
            res[i] = node;
            for (int j = 0; j < n + (r > 0 ? 1 : 0); j++) {
                prev = node;
                node = node.next;
            }
            prev.next = null;
        }
        return res;
    }

}
