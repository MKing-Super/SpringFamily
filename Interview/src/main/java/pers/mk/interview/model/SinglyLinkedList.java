package pers.mk.interview.model;

import java.util.NoSuchElementException;

public class SinglyLinkedList {
    private ListNode head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // 1. CREATE - 添加节点
    // 在头部添加
    public void addFirst(int val){
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // 在尾部添加
    public void addLast(int val){
        ListNode newNode = new ListNode(val);
        if (head == null){
            head = newNode;
        }else {
            ListNode current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
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
        }
        ListNode newNode = new ListNode(val);
        ListNode prev = getNode(index - 1);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    // 2. READ - 获取节点
    // 获取索引位置的节点
    public ListNode getNode(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ListNode current = head;
        for (int i = 0 ; i < index ; i++){
            current = current.next;
        }
        return current;
    }

    // 获取索引位置的值
    public int get(int index){
        return getNode(index).val;
    }

    // 查找值是否存在
    public boolean contains(int val){
        ListNode current = head;
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
        ListNode node = getNode(index);
        node.val = val;
    }

    // 4. DELETE - 删除节点
    // 删除头部节点
    public int removeFirst(){
        if (head == null){
            throw new NoSuchElementException("List is empty");
        }
        int val = head.val;
        head = head.next;
        size--;
        return val;
    }

    // 删除尾部节点
    public int removeLast(){
        if (head == null){
            throw new NoSuchElementException("List is empty");
        }
        if (size == 1){
            return removeFirst();
        }
        ListNode prev = getNode(size - 2);
        int val = prev.next.val;
        prev.next = null;
        size--;
        return val;
    }

    // 删除指定索引节点
    public int removeAtIndex(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index:" + index);
        }
        if (index == 0){
            return removeFirst();
        }
        ListNode prev = getNode(index - 1);
        ListNode toRemove = prev.next;
        int val = toRemove.val;
        prev.next = toRemove.next;
        size--;
        return val;
    }

    // 删除指定值的第一个节点
    public boolean removeValue(int val){
        if (head == null) {
            return false;
        }
        if (head.val == val){
            removeFirst();
            return true;
        }
        ListNode prev = head;
        while (prev.next != null && prev.next.val != val){
            prev = prev.next;
        }
        if (prev.next != null){
            prev.next = prev.next.next;
            size--;
            return true;
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
        size = 0;
    }

    // 遍历打印
    public void printList(){
        ListNode current = head;
        while (current != null){
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

}
