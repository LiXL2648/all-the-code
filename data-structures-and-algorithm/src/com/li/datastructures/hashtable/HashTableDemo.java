package com.li.datastructures.hashtable;

import java.util.HashMap;

/**
 * @author LiXL
 * @date 2023/2/28
 */
public class HashTableDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab();
        hashTab.add(new Emp(1, "LiXL"));
        hashTab.list();
        System.out.println(hashTab.get(1));
        System.out.println(hashTab.get(2));
    }

    static class HashTab {
        private final EmpLinkedList[] elements;

        public HashTab() {
            this.elements = new EmpLinkedList[16];
            for (int i = 0; i < elements.length; i++) {
                elements[i] = new EmpLinkedList();
            }
        }

        public void add(Emp emp) {
            elements[emp.id % elements.length].add(emp);
        }

        public Emp get(int id) {
            return elements[id % elements.length].get(id);
        }

        public void list() {
            for (int i = 0; i < elements.length; i++) {
                elements[i].list(i);
            }
        }
    }

    static class EmpLinkedList {
        Emp head;

        public void add(Emp emp) {
            if (head == null) {
                head = emp;
                return;
            }
            Emp cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = emp;
        }

        public Emp get(int id) {
            if (head == null) {
                return null;
            }

            Emp cur = head;
            while (cur!= null && cur.id != id) {
                cur = cur.next;
            }
            return cur;
        }

        public void list(int index) {
            if (head == null) {
                System.out.printf("第%d条链表为空\n", index + 1);
                return;
            }
            System.out.printf("第%d条链表：\n", index + 1);
            Emp cur = head;
            while (cur != null) {
                System.out.println(cur);
                cur = cur.next;
            }
        }
    }

    static class Emp {
        int id;
        String name;
        Emp next;

        public Emp(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Emp{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
