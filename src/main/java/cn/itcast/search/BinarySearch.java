package cn.itcast.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xujie
 * @Date 2020/6/3 21:59
 *
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        /*int resultIndex = binarySearch(arr, 0, arr.length-1, 1000);
        System.out.println(resultIndex);*/
        List<Integer> resultIndexList = binarySearch2(arr, 0, arr.length-1, 1000);
        if (resultIndexList.size() > 0) {
            for (Integer integer : resultIndexList) {
                System.out.println("下标为：" + integer);
            }
        } else {
            System.out.println("未找到相关数据");
        }
    }

    public static int binarySearch(int[] arr, int left, int right, int value) {
        int mid = (left + right) / 2;
        if (value > arr[mid]) {
            //向右递归
            binarySearch(arr, mid + 1, right, value);
        } else if (value < arr[mid]) {
            //向左递归
            binarySearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
        return -1;
    }
    /**
     * 上述方法没有考虑到查找的数字不在数组中的情况，会形成死循环，无法结束递归，最终造成内存溢出
     * Exception in thread "main" java.lang.StackOverflowError
     * 	at cn.itcast.search.BinarySearch.binarySearch(BinarySearch.java:18)
     *
     * 	因此要加上结束递归的条件：left > right,
     * 	问题 1：能取整吗：
     * 	问题 2：如果查找的数据在数组中出现多次，要求返回所有结果的下标，应该怎么解决？
     *
     * 	问题 1.
     */

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        if (left > right) {
//            return null;
            return new ArrayList<Integer>();//返回空集合，则可以通过集合的size判断是否查找到数据
        }
        int mid = (left + right) / 2;
        if (value > arr[mid]) {
            //向右递归
            return binarySearch2(arr, mid + 1, right, value);
        } else if (value < arr[mid]) {
            //向左递归
            return binarySearch2(arr, left, mid - 1, value);
        } else {
            /**
             * 问题 2：
             *      (1) 找到mid索引值后，不要马上返回，
             *      (2) 向mid索引值的的左边扫描，将所有满足1000元素的下标加入集合arraylist中
             *      (3) 向mid索引值的的右边扫描，将所有满足1000元素的下标加入集合arraylist中
             *      (4) 返回ArrayList
             *
             *      注意：是扫描，不是向左右两边递归，因为二分查找的时候，数组已经是有序数组了，
             *      如果出现多个相等的元素，那一定是相邻的位置的，如：
             *      int[] arr = {1, 3, 5, 7, 7, 7, 7, 9, 52};
             */
            ArrayList<Integer> resIndexList = new ArrayList<>();
            resIndexList.add(mid);
            //向左扫描：
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != value) {
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }

            //向右扫描：
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
