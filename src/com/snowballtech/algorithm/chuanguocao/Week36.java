package com.snowballtech.algorithm.chuanguocao;

import java.util.*;

/**
 * @author:CCGuo
 * @date:2021/9/2 13:07
 * ClassName:Week36
 * Package:com.snowballtech.algorithm.chuanguocao
 * Description:
 */
public class Week36 {

    public static void main(String[] args) {

        //单链表长度
        int length;
        //起始位置
        int left;
        //结束位置
        int right;
        //头节点
        SingleLinkedNode head = null;
        //当前节点
        SingleLinkedNode currNode = null;
        //新节点空间
        SingleLinkedNode newNode = null;
        //新节点的值
        Integer nodeData;

        Scanner scanner = new Scanner(System.in);

        //限制length大小
        System.out.println("请输入链表长度length:");
        length = scanner.nextInt();
        while (length < 1 || length > 500) {
            System.out.println("length取值范围应为0 ~ 500，请重新输入：");
            length = scanner.nextInt();
        }

        //限制left大小
        System.out.println("请输入left的值:");
        left = scanner.nextInt();
        while (left < 1 || left > length) {
            System.out.println("left应大于0且小于length，请重新输入：");
            left = scanner.nextInt();
        }

        //限制right大小
        System.out.println("请输入right的值:");
        right = scanner.nextInt();
        while (right < 1 || right > length) {
            System.out.println("right应大于0且小于length，请重新输入：");
            right = scanner.nextInt();
        }

        //限制left <= right
        while (left > right) {
            System.out.println("left应小于right，请重新输入left的值:");
            left = scanner.nextInt();
            while (left < 1 || left > length) {
                System.out.println("left应大于0且小于length，请重新输入：");
                left = scanner.nextInt();
            }
            System.out.println("请输入right的值:");
            right = scanner.nextInt();
            while (right < 1 || right > length) {
                System.out.println("right应大于0且小于length，请重新输入：");
                right = scanner.nextInt();
            }
        }

        //链表初始化
        for (int i = 0; i < length; i++) {

            //输入节点值 限制其取值范围
            System.out.println("输入节点值data:");
            nodeData = scanner.nextInt();
            while (nodeData < -500 || nodeData > 500) {
                System.out.println("data取值范围应为-500 ~ 500，请重新输入");
                nodeData = scanner.nextInt();
            }

            //判断链表是否为空 是则init
            if (head == null) {
                //init新节点
                newNode = new SingleLinkedNode(nodeData);
                head = newNode;
                currNode = newNode;
                continue;
            }
            //init新节点 尾插法进入单链表
            newNode = new SingleLinkedNode(nodeData);
            currNode.nextNode = newNode;
            currNode = newNode;
        }

        //调用反转函数进行处理 并打印
        reverseByIndex2(left, right, head);

        // Add by Max Yu 2021.09.08
        printSingleLinked(head);

    }


    /**
     * Description: 反转节点 left到结点 right
     * <p>
     * 将 left到 right的数据入栈 再将其出栈
     * 利用栈结构LIFO特点  重新插入单链表中实现反转
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static void reverseByIndex(int left, int right, SingleLinkedNode head) {

        //栈结构 用于存放数据
        Stack<Integer> stack = new Stack<>();
        //指向起始节点位置
        SingleLinkedNode startNode = null;
        //指向结束节点位置
        SingleLinkedNode endNode = null;
        //指向当前节点位置
        SingleLinkedNode currNode = null;
        //新节点
        SingleLinkedNode newNode = null;

        //定位起始节点位置
        for (int i = 1; i <= left; i++) {
            if (i == 1) {
                startNode = head;
                continue;
            }
            //循环后移 直到找到起始节点
            startNode = startNode.nextNode;
        }
        //令当前节点也指向起始节点 便于后面寻找结束节点
        currNode = startNode;

        // Add by Max Yu 2021.09.08 创建stack对象并利用其后进先出的特点来反转链表节点是一个方案,代码可读性也可以,但会增加空间复杂度,
        // 参考后续方法reverseByIndex2(使用遍历反转节点的算法)
        //将需要反转的节点依次入栈 并定位结束节点位置
        for (int i = left; i <= right; i++) {
            //当到达结束节点时 记录节点位置
            if (i == right) {
                stack.push(currNode.data);
                //记录结束节点位置
                endNode = currNode;
                break;
            }
            //将节点值依次入栈
            stack.push(currNode.data);
            currNode = currNode.nextNode;
        }

        //节点值出栈 重新加回单链表中
        for (int i = left; i <= right; i++) {
            //第一个出栈数据填充起始节点的值
            if (i == left) {
                startNode.data = stack.pop();
                currNode = startNode;
                continue;
            }
            //最后一个出栈数据填充结束节点的值
            if (i == right) {
                newNode = new SingleLinkedNode(stack.pop());
                currNode.nextNode = newNode;
                newNode.nextNode = endNode.nextNode;
                break;
            }
            //其余数据依次尾插 进入链表
            newNode = new SingleLinkedNode(stack.pop());
            currNode.nextNode = newNode;
            currNode = newNode;
        }
    }

    /**
     * 使用遍历反转链表区间节点
     * Add by Max Yu 2021.09.08
     * @param left
     * @param right
     * @param head
     * @return
     */
    public static SingleLinkedNode reverseByIndex2(int left, int right, SingleLinkedNode head) {

        // 查找preM&nextN
        SingleLinkedNode preM = null;
        SingleLinkedNode nextN = null;
        // 用于遍历head指针
        SingleLinkedNode node1 = head;
        int k = 0;
        while (node1 != null) {
            k++;
            preM = k == (left - 1) ? node1 : preM;
            nextN = k == (right + 1) ? node1 : nextN;
            node1 = node1.nextNode;
        }

        // 若由上面的式子得出preM为空,则局部链表开头即为head;不为空,则preM存在
        node1 = preM == null ? head : preM.nextNode;
        // 代表局部链表的第二个节点，因为要从第二个节点开始向局部链表开始的节点插
        SingleLinkedNode cur = node1.nextNode;
        // 这个操作就是将局部链表的开始节点与局部链表最后节点的下一个节点相连接
        node1.nextNode = nextN;
        // 用于保存下一个节点
        SingleLinkedNode next = null;
        while (cur != nextN) {
            // 保存cur的下一个节点
            next = cur.nextNode;
            // 将cur.next指向node1
            cur.nextNode = node1;
            // 将cur赋给node1,此时cur已经成功成为node的头节点
            node1 = cur;
            // 继续向后遍历，将一开始保存的节点重新赋给cur
            cur = next;
        }
        if (preM != null) {
            // 如果我们发现preM不为空，即局部链表开头不是整体链表的开头，就将preM与记录反转后链表的node1相连接;
            preM.nextNode = node1;
            return head;
        }

        // 若局部链表开头即是整体链表的开头，直接返回node1
        return node1;
    }


    /**
     * 打印函数
     * 将单链表统一以 List格式输出
     */
    public static void printSingleLinked(SingleLinkedNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.data);
            head = head.nextNode;
        }
        System.out.println(list);

    }


}


/**
 * 单链表结构实现
 */
class SingleLinkedNode {

    SingleLinkedNode nextNode;
    Integer data;

    public SingleLinkedNode() {

    }

    public SingleLinkedNode(Integer data) {
        this.data = data;
    }
}
