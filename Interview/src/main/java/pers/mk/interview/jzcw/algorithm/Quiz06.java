package pers.mk.interview.jzcw.algorithm;

import java.util.HashSet;

// 141. 环形链表
public class Quiz06 {

    public static void main(String[] args) {

        // 思路对
//        System.out.println(mk(null));

        // 还是利用 set 的特性
        System.out.println(m1(null));

        // 快慢指针
        System.out.println(m2(null));
    }



    private static boolean mk(ListNode head) {
        HashSet<String> set = new HashSet<>();
        boolean isCycle = false;
        while (true){
            if (head == null){
                isCycle = false;
                break;
            }
            String str = head.toString();
            if (set.contains(str)){
                isCycle = true;
                break;
            }
            set.add(str);
            head = head.next;
        }
        return isCycle;
    }

    static private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }



    private static boolean m1(ListNode head){
        HashSet<ListNode> set = new HashSet<>();
        boolean isCycle = false;
        while (head != null){
            if (!set.add(head)){
                isCycle = true;
                break;
            }
            head = head.next;
        }
        return isCycle;
    }

    private static boolean m2(ListNode head){
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if (fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}
