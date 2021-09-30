package main

import "fmt"

//定义单链表结构体
type ListNode struct {
	Val  int
	Next *ListNode
}

//思路:使用二路归并排序
func main() {
	// 改为控制台输入方式  Add by Max Yu 2021.09.09
	//要排序的数组
	arr := []int{-1, 5, 3, 4, 0}

	//头节点
	var head *ListNode = &ListNode{}
	var p *ListNode = head

	//数组放入链表
	for val := range arr {
		p.Next = &ListNode{Val: arr[val]}
		p = p.Next
	}

	//排序
	result := sort(head.Next)

	//输出
	fmt.Print("[")
	for true {
		if result != nil {
			fmt.Print(result.Val)
			result = result.Next
			if result != nil {
				fmt.Print(",")
			}
		} else {
			fmt.Print("]")
			break
		}
	}

}

//分割链表
func sort(node *ListNode) *ListNode {
	//若有该节点存在且有下一个节点
	if node == nil || node.Next == nil {
		return node
	}

	//快慢指针
	slow := node
	fast := node
	//查找中间节点
	for true {
		if fast.Next == nil || fast.Next.Next == nil {
			break
		} else {
			slow = slow.Next
			fast = fast.Next.Next
		}
	}
	//中间节点
	mid := slow.Next
	//左链表（中点之后赋空）
	slow.Next = nil

	//递归分割链表
	return merge(sort(node), sort(mid))
}

//二路归并
func merge(left *ListNode, right *ListNode) *ListNode {
	//结果
	result := &ListNode{}
	//结果游标
	temp := result

	//归并排序，将两个连表彰值较小的节点放入结果链表（不能直接赋予指针，会导致地址变换，返回结果为空）
	for true {
		if left == nil || right == nil {
			break
		}
		if left.Val < right.Val {
			temp.Val = left.Val
			left = left.Next
		} else {
			temp.Val = right.Val
			right = right.Next
		}
		//创建Next指针并移动游标
		temp.Next = &ListNode{}
		temp = temp.Next
	}

	//将剩余的链表直接赋值给结果链表（不能直接赋予指针，会导致地址变换，返回结果为空）
	if left != nil {
		temp.Val = left.Val
		temp.Next = left.Next
	}
	if right != nil {
		temp.Val = right.Val
		temp.Next = right.Next
	}
	return result
}
