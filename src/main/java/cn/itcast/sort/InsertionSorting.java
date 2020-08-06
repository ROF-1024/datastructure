package cn.itcast.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xujie
 * @date 2020/4/26-20:59
 */
public class InsertionSorting {
    public static void main(String[] args) {
        /*int[] arr = {9, 5, 6, 13, 29, 20, 56, 68, 37, 46, 4, 64};
        System.out.println("排序前："+ Arrays.toString(arr));
        insertionSorting(arr);
        System.out.println("排序后："+Arrays.toString(arr));*/
        int[] arr = new int[100000];
        for (int x = 0; x < 100000; x++) {
            arr[x] = (int) (Math.random() * 100000);
        }
//        System.out.println("排序前：" + Arrays.toString(arr));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = simpleDateFormat.format(date);
        System.out.println(time1);
        insertionSorting(arr);
        Date date2 = new Date();
        String time2 = simpleDateFormat.format(date2);
        System.out.println(time2);
    }

    public static void insertionSorting(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && arr[insertIndex] >= insertValue) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
        }
        /*//第一轮
        int insertValue = arr[1];
        int insertIndex = 1 - 1;
        while (insertIndex >= 0 && arr[insertIndex] >= insertValue) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertValue;


        //第二轮
        insertValue = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && arr[insertIndex] >= insertValue) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertValue;

        ...
        */

    }
}
