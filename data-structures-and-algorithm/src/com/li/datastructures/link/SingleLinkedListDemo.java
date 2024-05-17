package com.li.datastructures.link;

import java.util.Stack;

/**
 * @author LiXL
 * @date 2022/9/28
 * 单链表的应用
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江","及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义","玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用","智多星");
        HeroNode node4 = new HeroNode(4, "林冲","豹子头");
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(node4);
        linkedList.add2(node1);
        linkedList.add2(node3);
        linkedList.add2(node2);
        linkedList.add2(node2);
        HeroNode node5 = new HeroNode(6, "林冲","豹子头1");
        linkedList.update(node5);
        linkedList.delete(node1);
        linkedList.add2(node1);
        linkedList.list();
        System.out.println(linkedList.size());
        System.out.println(linkedList.lastIndex(1));
        System.out.println();
        linkedList.reverse();
        linkedList.list();

        linkedList.reverseList();
    }
}

/**
 * 定义一个链接，来管理节点
 */
class SingleLinkedList {
    // 初始化头节点，用于指向第一个节点，不存放任何数据
    private final HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到链表中
     * 找到当前链表的最末尾的节点
     * 将最末尾的节点的next指向新节点
     */
    public void add(HeroNode node) {
        HeroNode temp = head;
        // 遍历链表，找到末尾节点
        while (true) {
            // 找到末尾节点
            if (temp.next == null) {
                temp.next = node;
                break;
            }
            // 如果没有找到就后移动
            temp = temp.next;
        }
    }

    /**
     * 插入节点到指定位置
     * 如果该位置已经有值了，则添加失败，并给出提示
     */
    public void add2(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false; // 判断添加的节点编号是否村子

        while (true) {
            if (temp.next == null) {
                // 说明 temp 已经在链表的最后
                break;
            } else if (temp.next.no > node.no) {
                // 找到位置，就在temp的后面
                break;
            } else if (temp.next.no == node.no) {
                // 找到相同编号的节点
                flag = true;
                break;
            } else {
                // 后移，遍历当前链表
                temp = temp.next;
            }
        }
        if (flag) {
            System.out.println("该节点已经存在，添加失败");
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 删除节点
     */
    public void delete(HeroNode node) {
        //  判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，不能删除");
            return;
        }
        // 需要一个辅助节点找到待删除节点的前一个节点
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                // 已经到链表的最后
                break;
            } else if (temp.next.no == node.no) {
                // 找到待删除节点的前一个节点 temp
                flag = true;
                break;
            } else {
                // 后移，遍历当前链表
                temp = temp.next;
            }
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号为 %d 的节点\n", node.no);
        }
    }

    /**
     * 修改节点的编号，根据 no 编号来修改
     */
    public void update(HeroNode node) {
        // 判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，不能修改");
            return;
        }
        // 表示是否找到改节点
        boolean flag = false;
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                // 已经遍历完整个链表
                break;
            } else if (temp.no == node.no) {
                // 找到要修改的节点
                flag = true;
                break;
            } else {
                // 后移，遍历当前链表
                temp = temp.next;
            }
        }
        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            System.out.printf("没有找到编号为 %d 的节点\n", node.no);
        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        HeroNode temp = head.next;
        // 判断链表是否为空
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        do {
            System.out.println(temp);
            // 如果不为空就后移
            temp = temp.next;
        } while (temp != null);
    }

    /**
     * 获取链表的有效节点
     */
    public int size() {
        if (head.next == null) {
            return 0;
        }
        int size = 0;
        HeroNode cur = head;
        while ((cur = cur.next) != null) {
            size++;
        }
        return size;
    }

    /**
     * 获取倒数第k个节点
     */
    public HeroNode lastIndex(int index) {
        if (head.next == null) {
            return null;
        }
        // 获取链表的有效节点个数
        int size = size();
        if (index <= 0 || index > size) {
            return null;
        }

        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }

        return cur;
    }

    /**
     * 将链表反转
     */
    public void reverse() {
        // 如果当前链表没有或者只有一个节点，不需要反转
        int size = size();
        if (size == 0 || size == 1) {
            return;
        }

        HeroNode reverseHead = new HeroNode(0, "", null);
        HeroNode temp;
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前面
        while ((temp = head.next) != null) {
            // 将遍历的节点从旧的链表取出
            head.next = temp.next;
            // 将节点放在新链表的最前面
            temp.next = reverseHead.next;
            reverseHead.next = temp;
        }

        // 得到新的额链表
        head.next = reverseHead.next;
    }

    /**
     * 逆序打印
     */
    public void reverseList() {
        if (head.next == null) {
            return;
        }
        // 使用栈数据结构
        Stack<HeroNode> stack = new Stack<>();

        // 将各个节点压入栈中
        HeroNode temp = head;
        while ((temp = temp.next) != null) {
            stack.push(temp);
        }

        // 然后利用栈的后进先出的特点，实现逆序打印的效果
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}

/**
 * 定义 HeroNode，每个 HeroNode 对象是一个节点
 */
class HeroNode {
    int no;
    String name;
    String nickName;
    HeroNode next; // 指向下一个节点

    public HeroNode(Integer no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
