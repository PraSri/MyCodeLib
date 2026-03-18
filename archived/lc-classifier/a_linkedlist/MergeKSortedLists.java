package a_linkedlist;

import LinkedList.ListNode;

import static a_linkedlist.MergeTwoSortedLists.mergeTwoLists;

public class MergeKSortedLists {

//    https://leetcode.com/problems/merge-k-sorted-lists/

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKListsHelper(lists);
    }

    public static ListNode mergeKListsHelper(ListNode[] lists) {

        int n = lists.length;

        if (n == 0) {
            return null;
        }

        ListNode head = merge(lists, 0, n - 1);

        return head;

    }

    private static ListNode merge(ListNode[] lists, int s, int e) {

        if (s == e)
            return lists[e];
        int mid = s + (e - s) / 2;
        ListNode l1 = merge(lists, s, mid);
        ListNode l2 = merge(lists, mid + 1, e);

        return mergeTwoLists(l1, l2);
    }

}
