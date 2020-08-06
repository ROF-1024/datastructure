package cn.itcast.BinaryTree;

/**
 * @Author xujie
 * @Date 2020/6/15 11:08
 */
public class HeapSort {
    public static void main(String[] args) {
        /*int[] arr = {4, 10, 8, 6, 5, 13, 2, 9, 1, 3, 17};
        adjustHeap(arr.length, arr, 4);
        adjustHeap(arr.length,arr,3);
        adjustHeap(arr.length,arr,2);
        adjustHeap(arr.length,arr,1);*/
        int[] arr = {4, 13, 17, 9, 10, 8, 2, 6, 1, 3, 4};
        adjustHeap(arr.length,arr,0);
    }

    //堆排序方法：
    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
        //分步完成

    }

    /**
     *
     * @param length 待调整的数组长度
     * @param arr 待调整的数组
     * @param i 需要调整的非叶子节点
     *
     * 先将待调整数组转化为顺序存储二叉树，i就是顺序存储二叉树对应的非叶子节点，
     * 我们将i对应的非叶子节点（也就是局部子树的根节点）所在的局部子树，进行调整，使这棵子树成为大顶堆
     *          例如：arr={4,6,8,5,9},对应的顺序存储二叉树为4.6.8.5.9
     *          第一个非叶子节点对应的数据是6，我们先对第一个非叶子节点所在的子树（6.5.9）进行调整，
     *          使其成为9.5.6
     *
     */
    public static void adjustHeap(int length, int[] arr, int i) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //先比较i节点的左右两子节点的大小
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            /*
            * 如果左右两节点中的最大值，*/
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                //如果左右两节点中的大的节点的值小于i节点，说明i节点就是这个局部子树的最大值（注意：此时，以i节点的
                // 左右两节点为根节点的两个局部二叉树应该满足大顶堆的特点，说明大顶堆的调整是从最后一个非叶子结点倒着调整的）
                //直接break，跳出for循环，以i节点为根节点的二叉树已经调整为大顶堆
                break;
            }
        }
        //for循环结束之后，我们将以i为根节点的子树的最大值，放在了根节点处
        arr[i] = temp;//相当于最大最小值互换
    }
}