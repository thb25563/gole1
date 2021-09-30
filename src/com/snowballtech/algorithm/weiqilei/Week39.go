package main

import (
	"fmt"
	"strconv"
)

func main() {
	//输入部分
	var str string
	fmt.Scanln(&str)
	if len(str) < 3 {
		panic("输入不合法")
	}
	//计算累加数
	b := accumulate(str)
	fmt.Println(b)
}

func accumulate(str string) bool {
	//确定最初的两个值
	var index int
	num1, err := strconv.Atoi(str[0:1])
	if num1 == 0 {
		panic("第一个字符为0")
	}
	var num2 int
	//最差情况两个加数和结果所占的位数大约对半分，找到表示第二个数结尾的位置表示成功找到两个起始加数
	for i := 1; i < (len(str)+1)/2 && index == 0; i++ {
		num1, err = strconv.Atoi(str[0:i])
		if err != nil {
			panic(err)
		}
		//防止n,1,n
		for j := i + 1; j < (len(str)+1)/2+1 && index == 0; j++ {
			num2, err = strconv.Atoi(str[i:j])
			if err != nil {
				panic(err)
			}
			if num2 != 0 {
				num3 := num1 + num2
				//防止越界
				if len(strconv.Itoa(num3))+j > len(str) {
					return false
				}
				if strconv.Itoa(num3) == str[j:j+len(strconv.Itoa(num3))] {
					index = j
				}
			} else {
				break
			}
		}
	}
	//index为0表示没有找到两个起始加数
	if index == 0 {
		return false
	}
	//判断是否满足累加序列
	for index < len(str) {
		//将前两个数的值计算出后和原数组中的切片做比较
		num3 := strconv.Itoa(num1 + num2)
		//防止进位导致越界
		if len(num3)+index > len(str) {
			return false
		}
		if str[index:index+len(num3)] != num3 {
			//如果两者值不相等或是存在非数字字符那么打印并且返回false
			//fmt.Println(str[index:index+len(num3)])
			return false
		}
		//比较num1+num2和字符串下一个位置的值无误后，保证下一次循环各个值的逻辑意义一致进行处理
		num1 = num2
		num2, _ = strconv.Atoi(num3)
		index += len(num3)
	}
	//fmt.Println(true)
	return true
}
