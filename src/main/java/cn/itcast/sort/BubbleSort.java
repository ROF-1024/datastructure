package cn.itcast.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xujie
 * @date 2020/4/26-19:38
 */
public class BubbleSort {
    public static void main(String[] args) {
/*        int[] arr = {9, 5, 6, 13, 29, 20, 56, 468, 46, 46, 4, 64, 6};
        System.out.println("排序前："+Arrays.toString(arr));
        bubbleSort(arr);
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
        bubbleSort(arr);
        Date date2 = new Date();
        String time2 = simpleDateFormat.format(date2);
        System.out.println(time2);
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] >=arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
