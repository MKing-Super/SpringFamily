package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;

// 21. 合并两个有序链表
public class Quiz05 {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        // 思路逻辑混乱！！！
//        System.out.println(JSON.toJSONString(mk(list1,list2)));

        // 先造个假的链表头部
        System.out.println(JSON.toJSONString(m1(list1,list2)));
    }

    private static ListNode mk(ListNode list1, ListNode list2) {
        ListNode curNode = null;
        ListNode newHead = null;
        while (list1 != null || list2 != null){

            if (list1 != null && list2 != null){
                if (list1.val < list2.val){
                    ListNode next1 = list1.next;
                    if (curNode == null){
                        list1.next = null;
                        newHead = list1;
                    }else {
                        curNode.next = list1;
                        curNode = curNode.next;
                    }
                    list1 = next1;
                    continue;
                }
                if (list1.val == list2.val){
                    ListNode next1 = list1.next;
                    if (curNode == null){
                        list1.next = null;
                        newHead = list1;
                    }else {
                        curNode.next = list1;
                        curNode = curNode.next;
                    }
                    list1 = next1;

                    ListNode next2 = list2.next;
                    if (curNode == null){
                        list2.next = null;
                        newHead = list2;
                    }else {
                        curNode.next = list2;
                        curNode = curNode.next;
                    }
                    list2 = next2;
                    continue;
                }
                if (list1.val > list2.val){
                    ListNode next2 = list2.next;
                    if (curNode == null){
                        list2.next = null;
                        newHead = list2;
                    }else {
                        curNode.next = list2;
                        curNode = curNode.next;
                    }
                    list2 = next2;
                    continue;
                }
            }else if (list1 == null && list2 != null){
                ListNode next2 = list2.next;
                if (curNode == null){
                    list2.next = null;
                    newHead = list2;
                }else {
                    curNode.next = list2;
                    curNode = curNode.next;
                }
                list2 = next2;
                continue;
            }else if (list1 != null && list2 == null){
                ListNode next1 = list1.next;
                if (curNode == null){
                    list1.next = null;
                    newHead = list1;
                }else {
                    curNode.next = list1;
                    curNode = curNode.next;
                }
                list1 = next1;
                continue;
            }

        }

        return newHead;
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


    private static ListNode m1(ListNode list1, ListNode list2){
        ListNode head = new ListNode(-1);
        ListNode prev = head;

        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                prev.next = list1;
                list1 = list1.next;
            }else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        prev.next = list1 == null ? list2 : list1;

        return head.next;
    }


}
