package pers.mk.interview.model;

import java.util.NoSuchElementException;

public class LinkedListQueue {
    private QueueNode front;
    private QueueNode rear;
    private int size;

    public LinkedListQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // 入队
    public void enqueue(int val){
        QueueNode newNode = new QueueNode(val);
        if (size == 0){
            front = newNode;
            rear = newNode;
        }else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // 出队
    public int dequeue(){
        if (size == 0){
            throw new NoSuchElementException("queue is empty");
        }
        if (size == 1){
            int val = front.val;
            front = null;
            rear = null;
            return val;
        }
        int val = front.val;
        front = front.next;
        if (front == null){
            rear = null;
        }
        size--;
        return val;
    }

    // 获取队头元素
    public int peek(){
        if (size == 0){
            throw new NoSuchElementException("queue is empty");
        }
        return front.val;
    }

    public boolean isEmpty(){
        return front == null;
    }

    public int size(){
        return size;
    }

    public void print(){
        System.out.print("queue (front -> rear) : ");
        QueueNode current = this.front;
        while (current != null){
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println(" ");
    }

}
