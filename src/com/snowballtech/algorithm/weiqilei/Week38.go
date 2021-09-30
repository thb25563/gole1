package main

import (
	"fmt"
	"sort"
)
func find(nums []int)([]int){
	//将输入的数组升序排列
	sort.Ints(nums)
	var result []int
	var t int;
	for i := 0; i < len(nums); {
		if i==len(nums)-1||nums[i]!=nums[i+1] {
			result= append(result, nums[i])
			i++
		}else {
			//发现某个值连续出现两次以上则报错
			if t!=nums[i] {
				t=nums[i]
				i+=2
			}else {
				panic("输入存在不规范")
			}
		}
	}
	return result
}

func main() {

	var length int;

	fmt.Println("输入数组长度")
	fmt.Scanln(&length)
	if length < 2 || length > 3*1e4||length%2!=0 {
		panic("数组长度不合法将会在后续引起问题")
	}
	var nums []int = make([]int, length, length);
	//输入部分
	var temp int32;
	for i := 0; i < length; i++ {
		fmt.Println("请输入nums[",i,"],-2^31 <= nums[i] <= 2^31 - 1")
		fmt.Scanln(&temp)
		nums[i]=int(temp)
	}


	fmt.Println(nums)
	//放置结果
	var result []int=find(nums)

	//输出结果
	fmt.Print("[",result[0],",",result[1],"]")

}
