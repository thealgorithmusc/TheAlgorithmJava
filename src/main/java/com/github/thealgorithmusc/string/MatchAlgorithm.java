package com.github.thealgorithmusc.string;

public class MatchAlgorithm {

    // region Naive match algorithm

    /**
     * Search pattern string in main string by scanning the main string from left to right.
     *
     * @param main
     * @param pattern
     * @return
     */
    public static int naiveMatch(String main, String pattern) {
        int i = 0;
        int j = 0;
        while (i < main.length() && j < pattern.length()) {
            // 如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
            if (main.charAt(i) == pattern.charAt(j)) {

                i++;
                j++;
            }
            // 如果失配（即S[i]! = P[j]），令i回溯到i - (j - 1)的位置，且令j = 0
            else {

                i = i - j + 1;
                j = 0;
            }
        }

        // 若匹配成功，返回模式串p在文本串s中的位置，否则返回-1
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    // endregion

    // region KMP match algorithm

    /**
     * Search pattern string in main string using KMP algorithm.
     *
     * @param main
     * @param pattern
     * @return
     * @see <a href="http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html">
     * 阮一峰的网络日志 - 字符串匹配的KMP算法</a>
     * @see <a href="https://blog.csdn.net/v_july_v/article/details/7041827">CSDN - 从头到尾彻底理解KMP</a>
     */
    public static int kmpMatch(String main, String pattern) {
        int[] next = getNext(pattern);

        int i = 0;
        int j = 0;
        while (i < main.length() && j < pattern.length()) {
            // 如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || main.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            // 如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
            else {
                j = next[j];
            }
        }
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * Get next array for KMP algorithm.
     *
     * @param pattern
     * @return
     * @see <a href="https://blog.csdn.net/v_july_v/article/details/7041827">CSDN - 从头到尾彻底理解KMP</a>
     */
    private static int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];

        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pattern.length() - 1) {
            // pattern[k]表示前缀，pattern[j]表示后缀
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                ++j;
                ++k;
                next[j] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }

    // endregion

}
