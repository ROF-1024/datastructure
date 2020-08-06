package algorithm.binarySearch;

import java.util.Arrays;

/**
 * @Author xujie
 * @Date 2020/7/2 10:29
 */
//没有递归的二分查找
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {10,58,7,8,888,25,9,100};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int indexOfTarget = binarySearch(arr, 9);
        System.out.println(indexOfTarget);
    }

    //无递归的二分查找
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
