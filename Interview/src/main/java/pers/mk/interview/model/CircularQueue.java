package pers.mk.interview.model;

import org.hamcrest.core.Is;

import java.util.NoSuchElementException;

public class CircularQueue {
    private int[] queue;
    private int capacity;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int capacity){
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // 入队
    public boolean enqueue(int value){
       if (isFull()){
           return false;
       }
       queue[rear] = value;
       rear = (rear + 1) % capacity;
       size++;
       return true;
    }

    // 出队
    public int dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException("list is empty");
        }
        int value = queue[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    // 获取队头元素
    public int peek(){
        if (isEmpty()){
            throw new NoSuchElementException("list is empty");
        }
        return queue[front];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == capacity;
    }

    public int size(){
        return size;
    }

    public void print(){
        System.out.print("queue (front -> rear) :");
        for (int i = 0 ; i < size ; i++){
            int index = (front + i) % capacity;
            System.out.print(queue[index] + " ");
        }
        System.out.println(" ");
    }


}
