package pers.mk.interview.algorithm.base;

import pers.mk.interview.model.CircularLinkedList;
import pers.mk.interview.model.DoublyLinkedList;
import pers.mk.interview.model.SinglyLinkedList;

public class LinkedList {
    public static void main(String[] args) {
        // 单链表
//        method1();
        // 双向链表
//        method2();
        // 循环链表
        method3();

    }



    private static void method3(){
        CircularLinkedList list = new CircularLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addAtIndex(3,9);
        list.removeFirst();
        list.removeLast();
        list.removeAtIndex(3);
        list.get(2);


        list.printList();
    }

    private static void method2(){
        DoublyLinkedList list = new DoublyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addAtIndex(3,9);
        list.removeFirst();
        list.removeLast();
        list.removeAtIndex(4);
        list.removeValue(4);

        list.printForward();
        list.printBackward();
    }

    private static void method1(){
        SinglyLinkedList list = new SinglyLinkedList();
        // 添加首
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        // 添加尾
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        //
        System.out.println(list.get(1));
        list.removeFirst();
        list.removeLast();
        list.removeAtIndex(1);
        list.removeValue(4);


        // 遍历
        list.printList();
    }
}
