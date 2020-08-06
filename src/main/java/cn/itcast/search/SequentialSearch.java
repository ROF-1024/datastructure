package cn.itcast.search;

/**
 * @Author xujie
 * @Date 2020/6/3 21:52
 *
 * 线性查找
 */
public class SequentialSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int value = 11;
        int result = seqSearch(arr, value);
        if (result == -1) {
            System.out.println("没有查找到");
        }
        System.out.println("查找的数据在数组的下标为：" + result);
    }

    /**
     * 此处实现的线性查找是找到 一个 满足条件的值，就返回
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr,int value) {
        //线性查找：逐一比对，发现有相同的值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
