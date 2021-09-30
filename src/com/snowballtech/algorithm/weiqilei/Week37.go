package main

import "fmt"

func main() {
	var nums []int64
	var k int
	//输入部分
	{
		var temp int64
		//注意类型 需要保持一致
		const limit int64 = 2147483648
		fmt.Println("请输入数组长度")
		var len int = 0
		fmt.Scanln(&len)
		if len > 20000 {
			fmt.Println("数组长度不大于20000")
			return
		}

		for i := 0; i< len; i++ {
			fmt.Println("请输入nums[i],-2^31 <= nums[i] <= 2^31 - 1")
			fmt.Scanln(&temp)
			//输入的temp若超出int64表示的范围，值变为0，没能想到排除这种情况的办法
			if limit > temp && temp >= -limit {
				nums = append(nums, temp)

			} else {
				//输入数大于int32的表示范围小于int64的表示范围,那么退出循环（如：连续输入10个2，2222222222）
				break
			}
		}
		fmt.Println("请输入k值")
		fmt.Scanln(&k)

		if k < 0 || k > 100000 {
			return
		}
	}

	//交换函数
	move(nums, k%len(nums))
	// rotate(nums, k)
	//输出部分
	fmt.Print("[")
	for i, t := range nums {
		fmt.Print(t)
		if i < len(nums)-1 {
			fmt.Print(",")
		}
	}
	fmt.Print("]")

}

func move(nums []int64, k int) []int64 {
	if k == 0 {
		return nums
	}
	var temp int64
	//var flag int;
	var length = len(nums)
	//剩余无法成一组的元素个数
	var left int = length % k
	//(length-left)/k:可分成的组的数量
	var gnum int = (length - left) / k

	/*	for i := 0; i < k&&flag<length; i++ {
		temp=nums[i+k];
		nums[i+k]=nums[i];
		nums[i]=temp;
		for n := 1; ; n++ {
			if (i+n*k)%length>  {
				nums[i]=nums[(i+(n+1)*k)%length];
				nums[(i+(n+1)*k)%length]=temp;
				temp=nums[i];
				flag++;
				if flag==length {
					break;
				}
			}else {break;}
		}
	}*/

	//将可能后续产生连锁移位的元素全部预先移动好
	for i := 0; i < gnum-1; i++ {
		for n := 0; n < k; n++ {
			temp = nums[(i+1)*k+n]
			nums[(i+1)*k+n] = nums[n]
			nums[n] = temp
		}
	}

	var flag int = 0
	var t int64
	temp = nums[0]
	for i := 0; flag < k+left && left != 0; {
		//逻辑上将未分组的元素和被移到第一组去的最后一组元素拼接在一起
		if i < gnum*k {
			i = (i + gnum*k) % length
		} else {
			i = (i + k) % length
		}
		//交换元素
		t = nums[i]
		nums[i] = temp
		temp = t
		flag++
	}
	return nums
}

/*
 * 空间复杂度为O(1)的改进算法如下。 Add by Max Yu 2021.10.15
   思路：
   1. 我们从位置 00 开始, 最初令 temp=nums[0], 根据规则,位置00的元素会放至 (0+k) mod n的位置.
      令 x=(0+k) mod n, 此时交换temp和nums[x], 完成位置x的更新. 然后,我们考察位置x, 并交换temp和nums[(x+k) mod n], 从而完成下一个位置的更新.
       不断进行上述过程，直至回到初始位置 00。
 */

func rotate(nums []int64, k int) {
	n := len(nums)
	k %= n
	for start, count := 0, gcd(k, n); start < count; start++ {
		pre, cur := nums[start], start
		for ok := true; ok; ok = cur != start {
			next := (cur + k) % n
			nums[next], pre, cur = pre, nums[next], next
		}
	}
}

func gcd(a, b int) int {
	for a != 0 {
		a, b = b%a, a
	}
	return b
}

