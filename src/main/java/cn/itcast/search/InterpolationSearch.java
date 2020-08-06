package cn.itcast.search;

import java.util.Arrays;

/**
 * @Author xujie
 * @Date 2020/6/4 14:05
 *
 * 插值查找
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
//        System.out.println(Arrays.toString(arr));
        int resIndex = interpolationSearch(arr, 0, 99, 100);
        System.out.println(resIndex);
    }


    //插值查找算法：
    public static int interpolationSearch(int[] arr, int left, int right, int value) {
        if (left > right || value > arr[right] || value < arr[left]) {

            return -1;
        }
        int valueIndex = (left + right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        if (value > arr[valueIndex]) {
            //向右插值
            return interpolationSearch(arr, valueIndex + 1, right, value);
        } else if (value < arr[valueIndex]) {
            //向左插值
            return interpolationSearch(arr, left, valueIndex - 1, value);
        } else {
            return valueIndex;
        }
    }
}
