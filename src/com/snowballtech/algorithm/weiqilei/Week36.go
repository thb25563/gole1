package main

import (
	"fmt"
	"strconv"
)

type mytree struct {
	value interface{}
	left  *mytree
	right *mytree
}

func main() {
	//创建数组;由于数组内传送nil值，
	//起初考虑使用[]interface{}数组类型接受os.stdin传入的参数，
	//但在调用scan时发现，
	//scan遇到interface{}会发生panic 类型为空,
	//尝试多次不同方式但没能解决。
	//目前使用了string类型接收系统输入内容，
	var arr []interface{} /*=[]interface{}{1,2,5,3,4,nil,6};*/
	fmt.Println("输入数据")
	//var temp interface{};
	//var tempint int=0;

	//规范输入内容
	var s string
	var t int = -1
	for t < 0 || t > 2000 {
		fmt.Println("请在[0, 2000]范围内输入结点数")
		fmt.Scanln(&t)
	}
	for ; t > 0; t-- {
		fmt.Println("输入节点值，-100 <= 节点值 <= 100")
		// scanf调用后不会清楚输入缓存区,不能连续使用 MDF by Max Yu 2021.09.09
		fmt.Scanln(&s)
		var temp int
		var err error
		//fmt.Printf("%T,%s\n",s,s)
		//fmt.Printf("%T,%s\n",&s,&s)
		if s == "nil" || s == "null" {
			arr = append(arr, nil)
			continue
		} else {
			temp, err = strconv.Atoi(s)
			if err != nil {
				t++
				fmt.Println("输入错误")
				continue
			}
		}
		arr = append(arr, temp)
	}
	fmt.Println(arr)

	//原本的接收系统输入代码
	//scan遇上interface{}类型，疑似类型为空，无法接收值
	/*	for ; t > 0; t-- {
			fmt.Println("输入节点值，-100 <= 节点值 <= 100,t:",t)
			//fmt.Scan();
			fmt.Scanf("%v",&temp);

			if (temp.(int)<=100&&temp.(int)>=-100||temp==nil) {
				fmt.Println(reflect.TypeOf(temp));
				arr=append(arr, temp)
			}else {
				fmt.Println("本次输入失败")
			}
		}
		fmt.Println(arr);*/
	//fmt.Println(reflect.TypeOf(arr[5]));
	var tree mytree
	var result []interface{}
	//创建树
	tree.buildtree(0, &tree, arr)
	//打印树
	tree.showtree(&tree)
	//树转为单链表
	tree.tree2link(&tree)
	// 根据题意,直接打印链表即可,无需以下操作  Add by Max Yu 2021.09.09
	// 树内容放至数组
	tree.savetree2arr(&tree, &result)
	//输出结果
	//fmt.Println(result);
	fmt.Print("[")
	for i, t := range result {
		fmt.Print(t)
		if i < len(result)-1 {
			fmt.Print(",")
		}
	}
	fmt.Print("]")
}

//构建树函数
func (mytree) buildtree(n int, tree *mytree, arr []interface{}) {
	tree.value = arr[n]
	if (2*n+1) < len(arr) && arr[2*n+1] != nil {
		tree.left = &mytree{}
		tree.buildtree(2*n+1, tree.left, arr)
	}
	if (2*n+2) < len(arr) && arr[2*n+2] != nil {
		tree.right = &mytree{}
		tree.buildtree(2*n+2, tree.right, arr)
	}
}

//树的打印函数
//以先序遍历的结果输出
func (mytree) showtree(tree *mytree) {
	fmt.Println(tree.value)
	if tree.left != nil {
		tree.showtree(tree.left)
	}
	if tree.right != nil {
		tree.showtree(tree.right)
	}
}

//树结果保存至数组
func (mytree) savetree2arr(tree *mytree, arr *[]interface{}) {
	//fmt.Println(tree.value);
	var temptree mytree = *tree
	*arr = append(*arr, tree.value)
	for temptree.right != nil {
		*arr = append(*arr, "null")
		*arr = append(*arr, temptree.right.value)
		temptree = *temptree.right
	}
}

//树转单链表

func (mytree) tree2link(tree *mytree) {
	//递归
	/*var temp_right *mytree=tree.right;
	if(tree.left!=nil){
		var temptree *mytree=tree.left;
		tree.left=nil;
		tree.right=temptree;
		for temptree.right!=nil {
				temptree=temptree.right;
		}
		temptree.right=temp_right;
		tree.tree2link(tree.right);
	}*/
	//迭代
	var treet *mytree = tree
	//var temp_right *mytree=tree.right;
	for treet.right != nil {
		if treet.left != nil {
			var temptree *mytree = treet.left
			var temp_right *mytree = treet.right
			treet.left = nil
			treet.right = temptree
			for temptree.right != nil {
				temptree = temptree.right
			}
			temptree.right = temp_right
		}
		treet = treet.right
	}

}
