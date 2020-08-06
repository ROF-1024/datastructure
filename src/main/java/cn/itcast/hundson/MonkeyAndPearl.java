package cn.itcast.hundson;

/**
 * @Author xujie
 * @Date 2020/6/21 11:46
 */
public class MonkeyAndPearl {
    public static void main(String[] args) {
        System.out.println(1%5);
        System.out.println(2%5);
        System.out.println(3%5);
        System.out.println(4%5);
        /**
         * 第一次分之前：桃子总数total是5的倍数加1
         * 第一次分之后：桃子是总数的(total-1)*4/5
         * 第二次分之前，桃子总数是5的倍数加1
         * 第二次分之后：桃子是第二次分之前的（总数-1）*0.8
         * ......
         * 假设总数total个桃子
         * 判断total是否是5的倍数＋1
         *      不是，退出循环
         *      是，进入分桃子循环
         *          1.分完一次后，剩下（total-1）*0.8
         *          2.判断剩下是不是5的倍数加1，是的话，继续分桃子循环，不是的话，跳出分桃子循环
         */
        int total = 0;
        int count = 0;//最少有6个桃子，不然不够五只猴子分（默认分配桃子个数至少为1）
        for (total = 6; total < 10000; total++) {
            int temp = total;
            for (int i = 0; i <5; i++) {
                if (total < 0 || total % 5 != 1) {
                    total = temp;
                    break;
                }
                count++;
                total = (total - 1) * 4 / 5;
            }
            if (count == 4) {
                break;
            }
        }
        System.out.println(total);
    }
}
