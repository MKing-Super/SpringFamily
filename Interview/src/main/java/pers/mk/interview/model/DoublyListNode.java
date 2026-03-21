package pers.mk.interview.model;

public class DoublyListNode {
    int val;
    DoublyListNode prev;
    DoublyListNode next;

    public DoublyListNode(int val) {
        this.val = val;
        prev = null;
        next = null;
    }

    public DoublyListNode(int val, DoublyListNode prev, DoublyListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}
