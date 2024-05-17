package com.li.datastructures.link;

/**
 * @author LiXL
 * @date 2022/10/2
 * 双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 node1 = new HeroNode2(1, "宋江","及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义","玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用","智多星");
        HeroNode2 node4 = new HeroNode2(4, "林冲","豹子头");
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(node1);
        linkedList.add2(node4);
        linkedList.add2(node2);
        linkedList.add2(node3);
        System.out.println("添加");
        linkedList.list();

        HeroNode2 node5 = new HeroNode2(4, "林冲","豹子头1");
        linkedList.update(node5);
        System.out.println("修改");
        linkedList.list();
        linkedList.delete(1);
        System.out.println("删除");
        linkedList.list();
    }
}

class DoubleLinkedList {

    // 初始化头节点，用于指向第一个节点，不存放任何数据
    HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 插入节点到指定位置
     */
    public void add2(HeroNode2 node) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }
        if (flag) {
            System.out.printf("编号为 %d 的节点已经存在\n", node.no);
        } else {
            // 判断是否为最后一个节点
            // 先处理当前位置的下一个节点，再处理当前位置的节点
            if (temp.next != null) {
                temp.next.pre = node;
            }
            node.next = temp.next;
            node.pre = temp;
            temp.next = node;
        }
    }

    /**
     * 添加节点到链表中
     */
    public void add(HeroNode2 node) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = node;
                node.pre = temp;
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 修改节点的编号，根据 no 编号来修改
     */
    public void update(HeroNode2 node) {
        if (head.next == null) {
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            } else if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        }
    }

    public void delete(int no) {
        if (head.next == null) {
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            } else if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        HeroNode2 temp = head.next;
        // 判断链表是否为空
        if (temp == null) {
            return;
        }
        do {
            System.out.println(temp);
            // 如果不为空就后移
            temp = temp.next;
        } while (temp != null);
    }
}

class HeroNode2 {
    int no;
    String name;
    String nickName;
    HeroNode2 next; // 指向下一个节点
    HeroNode2 pre; // 指向下一个节点

    public HeroNode2(Integer no, String name, String nickName) {
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
