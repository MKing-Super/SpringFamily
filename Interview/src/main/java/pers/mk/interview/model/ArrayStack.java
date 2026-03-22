package pers.mk.interview.model;

import java.util.EmptyStackException;

public class ArrayStack {
    private int[] stack;
    private int capacity;
    private int top;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.top = 0;
    }

    // 入栈
    public void push(int value){
        if (top == capacity){
            throw new StackOverflowError("stack is full");
        }
        stack[top] = value;
        top = top + 1;
    }

    // 出栈
    public int pop(){
        if (top == 0){
            throw new EmptyStackException();
        }
        int res = stack[top - 1];
        top = top - 1;
        return res;
    }

    // 查看栈顶元素
    public int peek(){
        if (isEmpty()){
            throw  new EmptyStackException();
        }
        return stack[top - 1];
    }

    // 判断栈是否为空
    public boolean isEmpty(){
        return top == 0;
    }

    // 获取栈大小
    public int size(){
        return top;
    }


    public void print(){
        System.out.print("stack (bottom -> top): ");
        for (int i = 0 ; i < top ; i++){
            System.out.print(stack[i] + " ");
        }
        System.out.println(" ");
    }

}
