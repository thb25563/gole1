package main

import (
	"container/list"
	"fmt"
)

/*
思路：获取每次的局部最优解，考虑使用贪心算法的思想
	遍历所有字符，判定是否有在结果列表出现过，若没有出现过，则放入result中；
	若后续遍历中出现了比之前更小的字符，且之前的字符在后续也有出现过
	则将之前的字符删除，将更小的字符插入列表，循环该操作，直到列表为空或没有比该字符大的字符
*/
func main() {
	//记录每个字符出现的次数
	var keyCount map[string]int
	keyCount = make(map[string]int)

	//记录每次插入或删除的过程
	//temp := list.New()

	//记录结果
	result := list.New()

	//输入字符串
	var s string
	fmt.Scanf("%s", &s)

	//计算每个字符出现的次数
	for i := 0; i < len(s); i++ {
		ch := s[i]
		keyCount[string(ch)] += 1
	}

	/*
		//测试用，打印各字符出现的次数
		for s2 := range keyCount {
			fmt.Print(s2+":")
			fmt.Println(keyCount[s2])
		}
	*/

	//循环字符串
	for i := 0; i < len(s); i++ {
		//当前字符
		ch := string(s[i])

		//标识：当前字符是否存在于结果列表中
		tag := true
		for i := result.Front(); i != nil; i = i.Next() {
			if i.Value == ch {
				tag = false
			}
		}

		//若不存在于结果列表中
		if tag {
			//while
			for true {
				/*
					循环条件：结果列表不为空，当前字符的ascii小于结果列表中的最后一个字符 且结果列表中最后一个字符在后续字符串中有出现
							否则直接将当前字符放入结果列表并结束循环
				*/
				if result.Len() > 0 && []rune((result.Back().Value).(string))[0] > []rune(ch)[0] && keyCount[(result.Back().Value).(string)] >= 1 {
					//temp.Remove(temp.Back())
					result.Remove(result.Back())
				} else {
					//temp.PushBack(ch)
					result.PushBack(ch)
					keyCount[ch] -= 1
					break
				}
			}
		}
	}

	fmt.Print("输出：")
	for i := result.Front(); i != nil; i = i.Next() {
		fmt.Print(i.Value.(string))
	}
}
