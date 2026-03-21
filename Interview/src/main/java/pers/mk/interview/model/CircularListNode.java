package pers.mk.interview.model;

public class CircularListNode {
    int val;
    CircularListNode next;

    public CircularListNode(int val) {
        this.val = val;
        this.next = this;// 指向自己形成循环
    }
}
