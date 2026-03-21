package pers.mk.interview.model;

import java.util.NoSuchElementException;

public class DoublyLinkedList {
    private DoublyListNode head;
    private DoublyListNode tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 1. CREATE - 添加节点
    // 在头部添加
    public void addFirst(int val){
        DoublyListNode newNode = new DoublyListNode(val);
        if (head == null){
            head = newNode;
            tail = newNode;
        }else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // 在尾部添加
    public void addLast(int val){
        DoublyListNode newNode = new DoublyListNode(val);
        if (tail == null){
            head = newNode;
            tail = newNode;
        }else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // 在指定索引位置添加
    public void addAtIndex(int index,int val){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index : " + index);
        }
        if (index == 0){
            addFirst(val);
            return;
        }else if (index == size){
            addLast(val);
            return;
        }
        DoublyListNode newNode = new DoublyListNode(val);
        DoublyListNode current = getNode(index);
        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    // 2. READ - 获取节点
    // 获取索引位置的节点（优化：从头部或尾部开始）
    public DoublyListNode getNode(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index : " + index);
        }
        if (index < size / 2){
            DoublyListNode current = this.head;
            for (int i = 0 ; i < index;i++){
                current = current.next;
            }
            return current;
        }else {
            DoublyListNode current = this.tail;
            for (int i = size -1 ; i > index ; i--){
                current = current.prev;
            }
            return current;
        }
    }

    public int getFirst(){
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.val;
    }

    public int getLast(){
        if (tail == null){
            throw new NoSuchElementException();
        }
        return tail.val;
    }

    // 查找值是否存在
    public boolean contains(int val){
        DoublyListNode current = this.head;
        while (current != null){
            if (current.val == val){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 3. UPDATE - 更新节点值
    public void set(int index,int val){
        DoublyListNode node = getNode(index);
        node.val = val;
    }

    // 4. DELETE - 删除节点
    // 删除头部节点
    public int removeFirst(){
        if (head == null){
            throw new NoSuchElementException("list is empty");
        }
        int val = head.val;
        if (head == tail){
            head = null;
            tail = null;
        }else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return val;
    }

    // 删除尾部节点
    public int removeLast(){
        if (tail == null){
            throw new NoSuchElementException("list is empty");
        }

        int val = tail.val;
        if (head == tail){
            head = null;
            tail = null;
        }else {
            tail = tail.prev;
            tail.next = null;
        }
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
        }else if (index == size - 1){
            return removeLast();
        }
        DoublyListNode toRemove = getNode(index);
        int val = toRemove.val;
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        size--;
        return val;
    }

    // 删除指定值的第一个节点
    public boolean removeValue(int val){
        DoublyListNode current = this.head;
        while (current != null){
            if (current.val == val){
                if (current == head){
                    removeFirst();
                }else if (current == tail){
                    removeLast();
                }else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 5. 工具方法
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    // 正向遍历打印
    public void printForward(){
        DoublyListNode current = this.head;
        System.out.print("Forward: null <-");
        while (current != null){
            System.out.print(current.val);
            if (current.next != null){
                System.out.print(" <-> ");
            }else {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println("null");
    }

    public void printBackward(){
        DoublyListNode current = this.tail;
        System.out.print("Backward: null <- ");
        while (current != null){
            System.out.print(current.val);
            if (current.prev != null){
                System.out.print(" <-> ");
            }else {
                System.out.print(" -> ");
            }
            current = current.prev;
        }
        System.out.println("null");
    }




}
