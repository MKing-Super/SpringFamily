package pers.mk.interview.model;

import java.util.NoSuchElementException;

public class LinkedListDeque {
    private DequeNode front;
    private DequeNode rear;
    private int size;

    public LinkedListDeque() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // 头部插入
    public void addFirst(int val){
        DequeNode newNode = new DequeNode(val);
        if (front == null){
            front = newNode;
            rear = newNode;
        }else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    // 尾部插入
    public void addLast(int val){
        DequeNode newNode = new DequeNode(val);
        if (front == null){
            front = newNode;
            rear = newNode;
        }else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }

    // 头部删除
    public int removeFirst(){
        if (front == null){
            throw new NoSuchElementException("list is empty");
        }
        int val = front.val;
        if (front == rear){
            front = null;
            rear = null;
        }else {
            front = front.next;
            front.prev = null;
        }
        size--;
        return val;
    }

    public int removeLast(){
        if (front == null){
            throw new NoSuchElementException("list is empty");
        }
        int val = rear.val;
        if (front == rear){
            front = null;
            rear = null;
        }else {
            rear = rear.prev;
            rear.next = null;
        }
        size--;
        return val;
    }

    public int getFirst(){
        if (front == null){
            throw new NoSuchElementException("list is empty");
        }
        return front.val;
    }

    public int getLast(){
        if (front == null){
            throw new NoSuchElementException("list is empty");
        }
        return rear.val;
    }

    public int size(){
        return size;
    }


}
