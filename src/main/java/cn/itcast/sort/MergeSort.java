package cn.itcast.sort;

import java.util.Arrays;

/**
 * @author xujie
 * @date 2020/4/27-15:46
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 分解+合并
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左边递归分解
            mergeSort(arr, left, mid, temp);
            //向右边递归分解
            mergeSort(arr, mid + 1, right, temp);

            //分解后合并
            merge(arr, left, right, mid, temp);
        }
    }
    /**
     * 合并的方法
     * @param arr 待排序数组
     * @param left 左边有序数列的初始索引
     * @param right 右边索引
     * @param mid 中间索引
     * @param temp 临时中转数组
     */
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int i = left;//左边有序数列的初始索引
        int j = mid + 1;//右边有序数列的初始索引
        int tempIndex = 0;//中转数组的初始索引

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[tempIndex] = arr[i];
                tempIndex++;
                i++;
            } else {
                temp[tempIndex] = arr[j];
                tempIndex++;
                j++;
            }
        }
        while (i<=mid) {
            temp[tempIndex] = arr[i];
            tempIndex++;
            i++;
        }
        while (j <= right) {
            temp[tempIndex] = arr[j];
            tempIndex++;
            j++;
        }

        //将temp数组拷贝到arr数组中，注意：不是每一次合并之后的拷贝都是拷贝8个
        int tempLeft = left;
        tempIndex = 0;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[tempIndex];
            tempLeft++;
            tempIndex++;
        }
    }
}
