package cn.itcast.search;

import java.util.Arrays;

/**
 * @Author xujie
 * @Date 2020/6/4 16:48
 *
 * 斐波那契查找
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000};
        int resIndex = fibSearch(arr, 1000);
        System.out.println("index:" + resIndex);
    }

    //得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     *
     * @param arr 数组
     * @param key 需要查找的关键值
     * @return 返回对应的下标，没有则返回-1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int mid = 0;
        int f[] = fib();//得到斐波那契数列

        //首先找到 k ，使得 f[k] >= arr.length ,也即 f[k]-1 >= high
        //注意：这里的 f[k] 是扩容之后的数组的长度，因此 f[k]比较的对象是arr.length
        while (f[k] - 1 < high) {
            k++;
        }
        //获得扩容后的数组
        int[] temp = Arrays.copyOf(arr, f[k]);

        //如果，f[k] > arr.length, 那么需要将arr[high]之后的数据全部初始化为arr[high]
        for (int i = high + 1; i < f[k]; i++) {
            temp[i] = arr[high];
        }
        System.out.println(Arrays.toString(temp));

        //开始对扩展之后的新数组 temp 进行查找
        while (low <= high) {
            //先确定mid的值
            mid = low + f[k - 1] - 1;
            if (key > temp[mid]) {
                //向右查找
                low = mid + 1;
                k -= 2;
                /**
                 *
                 */
                if (k <= 1) {
                    if (temp[low] == key) {
                        return low;
                    }
                    return -1;
                }
            } else if (key < temp[mid]) {
                //向左进行查找
                high = mid - 1;
                k--;

                if (k <= 1) {
                    if (temp[high] == key) {
                        return high;
                    }
                    return -1;
                }
            } else {
//                return mid;
                /**
                 * 注意：这里不能直接返回mid，要考虑到 mid> high的情况，
                 * 因为这是在扩展后的数组进行查找，如果查找到的mid是扩容后的数组的最右边，返回下标为temp.length-1
                 * 实际上对应数组arr的下标应该是 arr.length-1
                 */
                if (mid > high) {
                    return high;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

}
