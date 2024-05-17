package com.li.algorithm.kmp;

import java.util.Arrays;

/**
 * @author LiXL
 * @date 2023/12/12
 */
public class KMPDemo {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 =                "ABCDABD";
        int index = violenceMatch(str1, str2);
        System.out.println(index);

        int[] next = knpNext(str2);
        System.out.println(Arrays.toString(next));
        int inddex2 = kmpSearch(str1, str2, next);
        System.out.println(inddex2);
    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    public static int[] knpNext(String dest) {
        int[] next = new int[dest.length()];

        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }

    /**
     * 暴力匹配
     */
    public static int violenceMatch(String str1, String str2) {
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int len1 = c1.length;
        int len2 = c2.length;
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        if (j == len2) {
            return i - j;
        } else {
            return -1;
        }
    }
}
