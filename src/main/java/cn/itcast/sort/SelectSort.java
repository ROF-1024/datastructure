package cn.itcast.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xujie
 * @date 2020/4/26-20:38
 *
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
/*        int[] arr = {9, 5, 6, 13, 29, 20, 56, 468, 46, 46, 4, 64, 6};
        System.out.println("排序前："+ Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后："+Arrays.toString(arr));*/
        int[] arr = new int[100000];
        for (int x = 0; x < 100000; x++) {
            arr[x] = (int) (Math.random() * 100000);
        }
//        System.out.println("排序前：" + Arrays.toString(arr));
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = simpleDateFormat.format(date1);
        System.out.println(time1);
        selectSort(arr);
        Date date2 = new Date();
        String time2 = simpleDateFormat.format(date2);
        System.out.println(time2);
    }

    public static void selectSort(int[] arr) {
        int min ;
        int index ;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    index = j;
                    min = arr[j];
                }
            }
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = min;
            }
        }
    }
}
