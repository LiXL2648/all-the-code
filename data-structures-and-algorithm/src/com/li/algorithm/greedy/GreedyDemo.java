package com.li.algorithm.greedy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiXL
 * @date 2023/12/13
 */
public class GreedyDemo {

    public static void main(String[] args) {
        Map<String, Set<String>> broadCasts = new HashMap<>();

        Set<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        Set<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        Set<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        Set<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        Set<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        broadCasts.put("k1", set1);
        broadCasts.put("k2", set2);
        broadCasts.put("k3", set3);
        broadCasts.put("k4", set4);
        broadCasts.put("k5", set5);

        List<String> select = greedy(broadCasts);
        System.out.println(select);
    }

    public static List<String> greedy(Map<String, Set<String>> broadCasts) {
        // 获取所有地区
        Set<String> allAreas = broadCasts.entrySet().stream().flatMap(entry -> entry.getValue().stream()).collect(Collectors.toSet());
        // 存储电台
        List<String> select = new ArrayList<>();
        // 指向最多未覆盖地区的电台
        String maxKey;
        // 存放未覆盖地区
        Set<String> maxSet;
        // 临时集合，存放电台覆盖地区和还没有覆盖地区的交集
        Set<String> tempSet;
        while (!allAreas.isEmpty()) {
            maxKey = null;
            maxSet = null;
            for (Map.Entry<String, Set<String>> entry : broadCasts.entrySet()) {
                // 当前电台能覆盖的地区
                tempSet = new HashSet<>(entry.getValue());
                // 求 tempSet 和 allAreas 的交集，并赋给 tempSet
                tempSet.retainAll(allAreas);
                // 如果当前电台对应未覆盖地区，比 maxKey 对应的未覆盖地区多，则重置 maxKey 和 maxSet
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > maxSet.size())) {
                    maxKey = entry.getKey();
                    maxSet = tempSet;
                }
            }
            if (maxKey != null) {
                select.add(maxKey);
                // 将 maxKey 对应的电台覆盖的地区，从 allAreas 中去除
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }

        return select;
    }
}
