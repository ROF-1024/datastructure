package cn.itcast.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xujie
 * @date 2020/4/27-14:15
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
        int[] arr = new int[100000];
        for (int x = 0; x < 100000; x++) {
            arr[x] = (int) (Math.random() * 100000);
        }
//        System.out.println("排序前：" + Arrays.toString(arr));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = simpleDateFormat.format(date);
        System.out.println(time1);
        quickSort(arr, 0, arr.length - 1);
        Date date2 = new Date();
        String time2 = simpleDateFormat.format(date2);
        System.out.println(time2);
//        System.out.println();

//        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        int l = left;
        int r = right;
        int temp = 0;
        while (l < r) {
            while (arr[l] < pivot) {
                l+=1;
            }
            while (arr[r] > pivot) {
                r-=1;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (l >= r) {
                break;
            }

            if (arr[l] == pivot) {
                r-=1;
            }

            if (arr[r] == pivot) {
                l+=1;
            }
        }

        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(arr,left,r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
