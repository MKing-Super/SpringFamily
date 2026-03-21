package pers.mk.interview.model;

import java.util.NoSuchElementException;

public class CircularLinkedList {
    private CircularListNode tail;// 指向最后一个节点
    private int size;

    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    // 1. CREATE - 添加节点
    // 在头部添加
    public void addFirst(int val){
        CircularListNode newNode = new CircularListNode(val);
        if (tail == null){
            tail = newNode;
            tail.next = tail;
        }else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    // 在尾部添加
    public void addLast(int val){
        addFirst(val);
        tail = tail.next;
    }

    // 在指定索引位置添加
    public void addAtIndex(int index,int val){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index : " + index);
        }
        if (index == 0){
            addFirst(val);
        }else if (index == size){
            addLast(val);
        }else {
            CircularListNode prev = getNode(index - 1);
            CircularListNode newNode = new CircularListNode(val);
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }
    }

    // 2. READ - 获取节点
    public CircularListNode getNode(int index){
        if (size == 0){
            throw new NoSuchElementException("list is empty");
        }
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index : " + index);
        }
        CircularListNode current = tail.next; // 从头开始
        for (int i = 0 ; i < index; i++){
            current = current.next;
        }
        return current;
    }

    public int get(int index){
        return getNode(index).val;
    }

    public int getFirst(){
        if (tail == null){
            throw new NoSuchElementException();
        }
        return tail.next.val;
    }

    public int getLast(){
        if (tail == null){
            throw new NoSuchElementException();
        }
        return tail.val;
    }

    public boolean contains(int val){
        if (size == 0){
            return false;
        }
        CircularListNode current = tail.next;// 从头开始
        for (int i = 0 ; i < size ; i++){
            if (current.val == val){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 3. UPDATE - 更新节点值
    public void set(int index,int val){
        getNode(index).val = val;
    }

    // 4. DELETE - 删除节点
    // 删除头部节点
    public int removeFirst(){
        if (size == 0){
            throw new NoSuchElementException("list is empty");
        }
        CircularListNode head = tail.next;
        int val = head.val;

        if (size == 1){
            tail = null;
        }else {
            tail.next = head.next;
        }
        size--;
        return val;
    }

    // 删除尾部节点
    public int removeLast(){
        if (size == 0){
            throw new NoSuchElementException("list is empty");
        }
        if (size == 1){
            return removeFirst();
        }
        // 找到尾节点的前一个节点
        CircularListNode prev = this.tail;
        for (int i = 0 ; i < size - 1 ;i++){
            prev = prev.next;
        }
        int val = tail.val;
        prev.next = tail.next;// 前一个节点指向头节点
        tail = prev;// 更新尾节点
        size--;
        return val;
    }

    // 删除指定索引节点
    public int removeAtIndex(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index : " + index);
        }
        if (index == 0){
            return removeFirst();
        }else if (index == size -1){
            return removeLast();
        }else {
            CircularListNode prev = getNode(index - 1);
            CircularListNode toRemove = prev.next;
            int val = toRemove.val;
            prev.next = toRemove.next;
            size--;
            return val;
        }

    }

    // 5. 工具方法
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        tail = null;
        size = 0;
    }

    public void printList(){
        if (isEmpty()){
            System.out.println("list is empty");
            return;
        }
        CircularListNode current = tail.next;// 从头开始
        for (int i = 0 ; i < size ; i++){
            System.out.print(current.val);
            if (i < size - 1){
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> (back to head)");
    }

}
