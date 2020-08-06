package cn.itcast.resursion;

/**
 * @author xujie
 * @date 2020/4/25-14:37
 *
 * 八皇后问题
 */
public class EightQueues {
    //定义一个max表示有多少个皇后
    int max = 8;
    //定义一个数组array，保存皇后后放置位置的结果
    int[] array = new int[max];
    static int count = 0;
    static int resolution = 0;
    public static void main(String[] args) {
        EightQueues eightQueues = new EightQueues();
        eightQueues.check(0);
        System.out.println();
        System.out.println("共judge：" + count+"次");
        System.out.println("共有：" + resolution+"种解法");
    }

    //将第n+1个皇后依次放在第1到第8列，调用judge方法判断是否冲突
    public void check(int n) {
        if (n == max) {
            resolution++;
            print();
            return;
        }
        for (int j = 0; j < 8; j++) {
            array[n] = j;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    //放置第n+1个皇后时，检测该皇后是否与前面已经摆放的皇后冲突
    public boolean judge(int n) {
        count++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //将皇后摆放的位置输出
    public void print() {
        for (int i : array) {
            System.out.print(i);
        }
        System.out.println();
    }
}
