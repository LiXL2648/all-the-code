package com.li.algorithm.dav;

/**
 * @author LiXL
 * @date 2023/12/11
 */
public class HanoitowerDemo {

    public static void main(String[] args) {
        hanoitower(64, 'A', 'B', 'C');
    }

    public static void hanoitower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println(a + "->" + c);
        } else {
            // 把a上面所有的盘从 a 移动到 b 借助 c
            hanoitower(num - 1, a, c, b);
            // 把最下面的盘从 a 移动到 c
            System.out.println(a + "->" + c);
            // 把b所有的盘从 b 移动到 c 借助 a
            hanoitower(num - 1, b, a, c);
        }
    }
}