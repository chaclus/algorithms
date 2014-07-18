package com.vainly.algorithms.sequence;

/**
 * CREATE BY INTELLIJ IDEA
 * user: chl
 * Date: 14-7-18 下午12:50
 */
public class Sequence {

    /**
     * 给定数组求最大子序列和
     * @param arr 数组
     * @param len 数组大小 arr.length
     * @return sum
     */
    public int maxSubSum(int[] arr, int len) {
        int result = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if ((sum + arr[i]) > arr[i]) {
                sum += arr[i];
            } else {
                sum = arr[i];
            }
            result = Math.max(result, sum);
        }
        return result;
    }
}
