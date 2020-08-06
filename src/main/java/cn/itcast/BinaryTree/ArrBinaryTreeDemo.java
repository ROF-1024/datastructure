package cn.itcast.BinaryTree;

/**
 * @Author xujie
 * @Date 2020/6/10 14:10
 *
 * 将二叉树的节点以数组的方式存储
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder(0);
//        arrBinaryTree.infixOrder(0);
        arrBinaryTree.postOrder(0);
    }
}

class ArrBinaryTree{
    private int[] arr;//存储二叉树节点

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 顺序存储二叉树的特点：
     * 		1. 顺序存储二叉树通常只考虑完全二叉树
     * 		2. 第n个元素的左子节点为：2*n+1
     * 		3. 第n个元素的右子节点为：2*n+2
     * 		4. 第n个元素的父节点为：(n-1)/2
     * 		5. n表示二叉树中的第几个元素（按0开始编号）
     * @param index：数组的下标
     *
     * 顺序存储二叉树的前序遍历
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法对数组按照二叉树进行前序遍历");
        }
        //输出当前元素：
        System.out.println(arr[index]);
        //向左遍历递归：
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右遍历递归：
        if (2 * index + 2< arr.length) {
            preOrder(2 * index + 2);
        }
    }
    //顺序存储二叉树的中序遍历
    public void infixOrder(int index) {
        if (this.arr == null || arr.length == 0) {
            System.out.println("数组为空，无法对数组按照二叉树进行前序遍历");
        }
        //向左遍历递归：
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        //输出当前元素：
        System.out.println(this.arr[index]);
        //向右遍历递归：
        if (2 * index + 2< arr.length) {
            infixOrder(2 * index + 2);
        }
    }
    //顺序存储二叉树的后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法对数组按照二叉树进行前序遍历");
        }
        //向左遍历递归：
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右遍历递归：
        if (2 * index + 2< arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前元素：
        System.out.println(arr[index]);
    }
}

