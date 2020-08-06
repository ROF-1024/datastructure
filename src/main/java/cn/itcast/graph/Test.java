package cn.itcast.graph;

import java.util.LinkedList;

/**
 * @Author xujie
 * @Date 2020/6/30 23:43
 */
public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);

        System.out.println(queue);
        Integer first = queue.removeFirst();
        System.out.println(first);
        System.out.println(queue);
    }
}
