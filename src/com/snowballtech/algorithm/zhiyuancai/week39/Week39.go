package main

import (
	"fmt"
	"math"
)

/*
	规律：最大乘积的组成是由不大于3的数字组成的，
		 大于4的数应该尽可能的拆成3或2,
		 等于4的数理论上应该差分为2和2，但由于结果一样，可以不做处理

	结论：应将数字尽可能拆为3的次方，同时避免1的出现
*/
func main() {
	var number int
	fmt.Scanf("%d", &number)

	//和3进行相除
	count := number / 3
	//和3相除取余
	remainder := number % 3

	//如果余数为1，则拆为2+2
	if remainder == 1 {
		count -= 1
		remainder += 3
	}

	//经验证，拆为3的次方成绩最大
	max := math.Pow(3, float64(count))
	max *= float64(remainder)

	if count < 1 {
		fmt.Println(1)
	} else {
		fmt.Println(max)
	}

}
