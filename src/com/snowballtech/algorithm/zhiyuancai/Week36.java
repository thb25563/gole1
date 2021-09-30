package com.snowballtech.algorithm.zhiyuancai;

import java.util.*;

//思路:使用二路归并排序
public class Week36 {

    public static void main(String[] args) {
        //头结点
        ListNode head = new ListNode();

        //输入要排序的数
        ListNode next = null;
        System.out.println("请输入要排序的数 回车分割，输入end代表结尾");
        while(true){
            Scanner input = new Scanner(System.in);
            String s = input.next();
            if(s.equals("end")){
                break;
            }else {
                if(next == null){
                    next = new ListNode(Integer.parseInt(s));
                    head.next = next;
                } else {
                    next.next = new ListNode(Integer.parseInt(s));
                    next = next.next;
                }
            }
        }

        //排序
        ListNode sort = sort(head.next);

        //输出
        System.out.print("[");
        while (true) {
            System.out.print(sort.val);
            if (sort.next == null) {
                System.out.print("]");
                break;
            }
            sort = sort.next;
            if(sort != null){
                System.out.print(",");
            }
        }
    }

    //将链表分割为两份
    public static ListNode sort(ListNode head){
        //头结点为空或下一个节点为空时，结束递归
        if (head == null || head.next == null){
            return head;
        }

        //查找中点
        ListNode slow = head;
        ListNode quick = head;
        //快慢指针，快指针到末尾时，满指针到中点
        while(quick.next != null && quick.next.next != null){
            slow = slow.next;
            quick = quick.next.next;
        }
        //中间节点
        ListNode mid = slow.next;
        //截取左链表至中间节点
        slow.next = null;

        //递归左链表和右链表，然后进行合并
        return merge(sort(head), sort(mid));
    }

    //合并左右链表
    public static ListNode merge(ListNode left, ListNode right){
        //排序结果的头结点
        ListNode result = new ListNode();
        //排序结果
        ListNode p = result;

        //循环左链表和右链表，直到一方链表结束
        while (left != null && right != null){
            //将值较小的节点放入结果链表中
            if (left.val < right.val){
                p.next = left;
                left = left.next;
            }else {
                p.next = right;
                right = right.next;
            }
            //移动链表指针
            p = p.next;
        }

        //若右链表结束，则将左链表放入结果链表
        if (left != null){
            p.next = left;
        }
        //若左链表结束，则将右链表放入结果链表
        if (right != null){
            p.next = right;
        }

        return result.next;
    }
}
