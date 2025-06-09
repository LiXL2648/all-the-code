package com.li.strategy.src;

import java.util.Arrays;
import java.util.Comparator;

public class StrategyDemo {

    public static void main(String[] args) {
        Integer[] arrays = {9, 1, 2, 8, 4, 3};
        // 实现升序排序，返回-1放左边，1放右边，0保持不变

        // 说明
        // 实现了 Comparator 接口
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer i1, Integer i2) {
                if (i1 > i2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        Arrays.sort(arrays, comparator);
        System.out.println(Arrays.toString(arrays));
    }
}
