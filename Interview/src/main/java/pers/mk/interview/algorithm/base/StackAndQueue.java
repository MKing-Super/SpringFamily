package pers.mk.interview.algorithm.base;

import pers.mk.interview.model.ArrayStack;
import pers.mk.interview.model.CircularQueue;
import pers.mk.interview.model.LinkedListQueue;
import pers.mk.interview.model.LinkedListStack;

import java.util.Stack;

public class StackAndQueue {
    public static void main(String[] args) {
        // 基于数组实现栈
//        method1();
        // 基于链表实现栈
//        method2();

        // 基于数组实现循环队列
//        method3();
        // 基于链表实现队列
//        method4();

        // 栈的应用 - 括号匹配
//        method5();




    }

    private static void method5(){
        String s = "(j)";
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()){
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    System.out.println("stack is empty 1");
                    return;
                }
                Character top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')
                ){
                    System.out.println("false");
                    return;
                }
            }
        }
        System.out.println("stack is empty 2");
    }



    private static void method4(){
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
        queue.print();

        queue.dequeue();
        queue.dequeue();
        queue.print();

        System.out.println(queue.peek());
        System.out.println(queue.size());
    }

    private static void method3(){
        CircularQueue queue = new CircularQueue(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);
        queue.print();

        queue.dequeue();
        queue.dequeue();
        queue.print();

        System.out.println(queue.peek());
    }

    private static void method2(){
        LinkedListStack stack = new LinkedListStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.print();

        stack.pop();
        stack.pop();
        stack.print();

        System.out.println(stack.size());

        System.out.println(stack.peek());;
    }

    private static void method1(){
        ArrayStack stack = new ArrayStack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
//        stack.push(6);
        stack.print();

        stack.pop();
        stack.pop();
        System.out.println(stack.peek());;
        stack.print();

        System.out.println(stack.size());
    }
}
