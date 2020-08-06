package cn.itcast.sort;

import java.util.Arrays;

/**
 * @Author xujie
 * @Date 2020/6/3 15:19
 *
 * 基数排序
 */
public class RadisSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
        int[] arr = {53, 3, 542, 748, 14, 214,545,5124,21,2,54646};
        radisSort(arr);
    }

    //基数排序方法：
    public static void radisSort(int[] arr) {

        /**
         * 定义一个二维数组，表示十个桶，每个桶就是一个一位数组
         * 1.二维数组包含十个一位数组
         * 2.为了防止数组放数据时数据溢出，每一个数组的的大小，定位arr.length
         * 3.显然，基数排序是空间换时间的经典算法
         */
        int[][] bucket = new int[10][arr.length];

        //定义一个数组，代表第n个桶最终存放数据的个数
        int[] elementsCountOfBucket = new int[10];//初始化数字均为0，即每一个桶中均没有存放数据

        /**
         * 如何知道做几次循环呢？
         * 先找出数组最大值，看看是几位数即可
         */
        int max = arr[0];
        for (int m = 1; m < arr.length; m++) {
            if (arr[m] > max) {
                max = arr[m];
            }
        }
        int length = (max + "").length();//数字加上空字符串，新的字符创的长度就是数字的长度，妙！
        System.out.println(length);

        //用循环开始排序
        int index;
        for (int j = 0,n=1; j < length; j++,n*=10) {
            for (int i = 0; i < arr.length; i++) {
                int digit = (arr[i] / n) % 10;
                //System.out.println("digit:"+digit);
                bucket[digit][elementsCountOfBucket[digit]] = arr[i];
                elementsCountOfBucket[digit]++;
            }
            index = 0;
            for (int y = 0; y < 10; y++) {
                //加入判断，减少工作量：!!!!!!!!!!!!!!!!!!!!!!!
                if (elementsCountOfBucket[y] != 0) {
                    for (int x = 0; x < elementsCountOfBucket[y]; x++) {
                        arr[index] = bucket[y][x];
                        index++;
                    }
                    elementsCountOfBucket[y] = 0;
                }
            }
            //第j+1次排序之后的数组为：
            System.out.println("第"+(j+1)+"次排序之后的数组为："+Arrays.toString(arr));
            /**
             * {542, 53, 3, 14, 214, 748}
             * arr = {3, 14, 214, 542, 748, 53}
             * arr = {3, 14, 53, 214, 542, 748}
             */
        }

        /*
        //第一轮排序：
        //遍历数组，取出每一个数字按照数字的个位数，放入相应的桶中
        for (int i = 0; i < arr.length; i++) {
            int ge = arr[i] % 10;
            bucket[ge][elementsCountOfBucket[ge]] = arr[i];
            elementsCountOfBucket[ge]++;//第ge位数的桶中存放了一个数据，个数+1
        }
        //依次取出十个桶中的数据
        int index = 0;//原数组arr的索引
        for (int j = 0; j < 10; j++) {
            for (int x = 0; x < elementsCountOfBucket[j]; x++) {
                arr[index] = bucket[j][x];
                index++;
            }
            elementsCountOfBucket[j]=0;//注意：第二轮排序之前，要将elementsCountOfBucket初始化为0
        }
        //第一次排序之后的数组为：
        for (int i : arr) {
            System.out.println(i);
        }

        //第二轮排序
        for (int i = 0; i < arr.length; i++) {
            int shi = (arr[i] / 10) % 10;
            bucket[shi][elementsCountOfBucket[shi]] = arr[i];
            elementsCountOfBucket[shi]++;
        }
        index = 0;
        for (int j = 0; j < 10; j++) {
            for (int x = 0; x < elementsCountOfBucket[j]; x++) {
                arr[index] = bucket[j][x];
                index++;
            }
            elementsCountOfBucket[j] = 0;
        }
        //第二次排序之后的数组为：
        System.out.println(Arrays.toString(arr));

        //第三轮排序
        for (int i = 0; i < arr.length; i++) {
            int bai = (arr[i] / 100) % 10;
            bucket[bai][elementsCountOfBucket[bai]] = arr[i];
            elementsCountOfBucket[bai]++;
        }
        index = 0;
        for (int j = 0; j < 10; j++) {
            for (int x = 0; x < elementsCountOfBucket[j]; x++) {
                arr[index] = bucket[j][x];
                index++;
            }
            elementsCountOfBucket[j] = 0;
        }
        //第三次排序之后的数组为：
        System.out.println(Arrays.toString(arr));
        */
    }
}
