package linked_list.copy_linked_list_with_random_pointer;

import common.RandomNode;

public class Solution {

    public RandomNode copyRandomList(RandomNode head) {
        if (head == null) {
            return null;
        }
        for (RandomNode cur = head; cur != null; cur = cur.next.next) {
            RandomNode copy = new RandomNode(cur.val);
            copy.next = cur.next;
            cur.next = copy;
        }
        for (RandomNode cur = head; cur != null; cur = cur.next.next) {
            cur.next.random = cur.random == null ? null : cur.random.next;
        }
        RandomNode copyHead = head.next;
        for (RandomNode cur = head; cur != null; cur = cur.next) {
            RandomNode copy = cur.next;
            cur.next = copy.next;
            copy.next = copy.next == null ? null : copy.next.next;
        }
        return copyHead;
    }
}
