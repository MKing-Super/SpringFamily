package pers.mk.interview.jzcw.algorithm;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;

// 206. 反转链表
public class Quiz04 {

    public static void main(String[] args) {
        ListNode node = new ListNode(2);
        ListNode head = new ListNode(1, node);

        // 取出节点，重新排列。
//        System.out.println(JSON.toJSONString(mk(head)));


        // 头插法
        System.out.println(JSON.toJSONString(m1(head)));
    }

    private static ListNode mk(ListNode head) {
        if (head == null){
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (true){
            list.add(head.val);
            if (head.next == null){
                break;
            }
            head = head.next;
        }
        ListNode revers = null;
        for (int i = 0 ; i < list.size() ; i++){
            if (revers == null){
                revers = new ListNode(list.get(i));
            }else {
                revers = new ListNode(list.get(i), revers);
            }
        }
        return revers;
    }

    static private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }




    private static ListNode m1(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}


