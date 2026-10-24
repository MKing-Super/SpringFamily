package pers.mk.interview.jzcw.algorithm;

import com.alibaba.fastjson.JSON;
import pers.mk.interview.model.ListNode;

// LCR 140. 训练计划 II
public class Quiz07 {

    public static void main(String[] args) {
        ListNode head = new ListNode(2,new ListNode(4,new ListNode(7,new ListNode(8))));

        // ✅️
        System.out.println(JSON.toJSONString(mk(head,1)));
    }


    private static ListNode mk(ListNode head, int cnt) {
        ListNode prev = head;
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        int point = len + 1 - cnt;
        len = 0 ;
        while (prev != null){
            len++;
            if (len == point){
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }
}
