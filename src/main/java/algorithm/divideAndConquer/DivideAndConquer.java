package algorithm.divideAndConquer;

/**
 * @Author xujie
 * @Date 2020/7/1 16:33
 */
public class DivideAndConquer {
    public static void main(String[] args) {
        HanoiTower(3, 'a', 'b', 'c');
    }

    //汉诺塔的移动方法：分治算法
    public static void HanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            /**
             * 如果有超过两个盘，则我们总是可以看做两个盘，最下面一个盘，上面一个盘
             * 1.将上面一个盘从a移动到b
             * 2.将下面一个盘从a移动到c
             * 3.将上面一个盘从b移动到c
             */
            //1.上面的num-1个盘，从a移动到b，其中要用到c
            HanoiTower(num - 1, a, c, b);
            //2.将下面一个盘从a移动到c
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.将上面num-1个盘从b移动到c，其中要用到a
            HanoiTower(num - 1, b, a, c);
        }
    }
}
