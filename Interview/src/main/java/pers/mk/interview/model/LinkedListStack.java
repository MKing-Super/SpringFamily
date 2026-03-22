package pers.mk.interview.model;

import java.util.EmptyStackException;

public class LinkedListStack {
    private ListNode top;
    private int size;

    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    // 入栈
    public void push(int val){
        ListNode newNode = new ListNode(val);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // 出栈
    public int pop(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        int val = top.val;
        top = top.next;
        size--;
        return val;
    }

    // 查看栈顶元素
    public int peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return top.val;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void print(){
        System.out.println("stack top -> bottom : ");
        ListNode current = this.top;
        while (current != null){
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println("  ");
    }

}
