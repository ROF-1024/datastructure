package cn.itcast.stack;

import java.util.Scanner;

/**
 * @author xujie
 * @date 2020/4/23-14:23
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试栈
        ArrayStack stack = new ArrayStack(4);
/*        stack.push(10);
        stack.push(20);
        System.out.println(stack.pop());
        stack.list();*/
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加数据到栈");
            System.out.println("pop:从栈中弹出一个数据");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入你要添加的数据：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int result = stack.pop();
                        System.out.println("出栈数据为" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了");
    }
}
//用数组模拟栈
class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (this.isFull()) {
            System.out.println("栈已满，无法存入数据。。。");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空,无数据");
        }
        int temp = stack[top];
        top--;
        return temp;
    }

    //显示栈：遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空，无数据。。。。。。。");
        }
/*        while (top>=0) {
            System.out.println("stack["+top+"]=" + stack[top]);
            top--;
        }*/
        //此处不能用while循环，否则top的数值会发生改变，从而影响后续操作
        for (int i = top; i >= 0; i--) {
            System.out.println("stack["+i+"]=" + stack[i]);
        }
    }
}
