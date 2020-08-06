package cn.itcast.stack;

/**
 * @author xujie
 * @date 2020/4/23-16:53
 */
public class Calculator {
    public static void main(String[] args) {
        String exp = "7*2*2-5+1-5+3-4";
        //创建两个栈：数栈和符号栈
        ArrayStack2 numberStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义索引，用于扫描表达式
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描的char保存到ch中
        String keepNum = "";//用于拼接多位数
        //开始循环扫描
        while (true) {
            //依次得到表达式的字符
            ch = exp.substring(index, index + 1).charAt(0);
            //判断ch是否为字符
            if (operStack.isOperator(ch)) {
                //是字符，判断字符栈是否为空
                if (!operStack.isEmpty()) {
                    //当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，
                    // 再从符号栈中pop出一个符号，进行运算，将得到的结果push入数栈，
                    // 然后将当前的操作符入符号栈。
                    if (operStack.priority(ch) <= operStack.priority((char) operStack.peek())) {
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        oper = operStack.pop();
                        res = numberStack.cal(num1, num2, (char) oper);
                        numberStack.push(res);
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //字符栈为空，直接存入字符栈
                    operStack.push(ch);
                }
            } else {
                /**
                 * 当处理多位数时，不能一发现是数字就立即入栈
                 * 那么需要向exp表达式的index后再看一位，
                 *      1.如果是符号才入栈，
                 *      2.如果是数字，继续扫描
                 */
                keepNum += ch;
                //如果ch是表达式最后一位，直接入栈
                if (index == exp.length() - 1) {
                    numberStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOperator(exp.substring(index + 1, index + 2).charAt(0))) {
                        //后一位是字符
                        numberStack.push(Integer.parseInt(keepNum));
                        //重要：记得清空keepNum
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= exp.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //符号栈为空，则计算结束，数栈中只有一个数值
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            oper = operStack.pop();
            res = numberStack.cal(num1, num2, (char) oper);
            numberStack.push(res);
        }
        System.out.println("表达式：" + exp + "=" + numberStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
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
            System.out.println("stack[" + i + "]=" + stack[i]);
        }
    }

    //返回运算符的优先级，优先级由程序员自己确定，数字越大，优先级越高
    public int priority(char operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;//假定目前的计算表达式只有加减乘除
        }
    }

    //判断是不是一个运算符
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, char operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回栈顶值，但不是真正的pop弹出
    public int peek() {
        return stack[top];
    }
}
