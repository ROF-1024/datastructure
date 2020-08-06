package cn.itcast.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xujie
 * @date 2020/4/24-10:04
 */
public class PolandNotation {
    public static void main(String[] args) {
        //定义逆波兰表达式，为方便说明，用空格隔开
        //String suffixExpression = "30 4 + 5 * 6 - ";
        /*
        思路：
            1.将表达式放入ArrayList中
            2.将ArrayList传递给一个方法，遍历ArrayList。配合栈完成计算
         */
        /*List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int result = calculate(rpnList);
        System.out.println(result);*/
        String exp = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(exp);
        System.out.println("中缀表达式对应的list："+infixExpressionList);
        List<String> toSuffixExp = infixExpToSuffixExp(infixExpressionList);
        System.out.println("后缀表达式对应的list：" + toSuffixExp);
        int ree = calculate(toSuffixExp);
        System.out.println(ree);
    }

    /**
     * 将中缀表达式转为相应的list
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;//指针，用于遍历中缀表达式字符串
        String str;//用于对多位数的拼接
        char c;//每遍历到一个字符，就放入c中
        do {
            //如果c不是数字，加入ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(String.valueOf(c));
                i++;
            } else {
                //c是数字
                str = "";
                while (i < (s.length()) && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2) 从左至右扫描中缀表达式；
     * 3) 遇到操作数时，将其压s2；
     * 4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     *      1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *      2.s1不为空，若优先级比栈顶运算符的高，将运算符压入s1；
     *      3.s1不为空，优先级比栈顶运算符低，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
     * 5) 遇到括号时：
     *      1. 如果是左括号“(”，则直接压入s1
     *      2. 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤2至5，直到表达式的最右边
     * 7) 将s1中剩余的运算符依次弹出并压入s2
     * 8) 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * @param infixExpList
     * @return
     */
    public static List<String> infixExpToSuffixExp(List<String> infixExpList) {
        Stack<String> s1 = new Stack<>();//符号栈
        List<String> s2 = new ArrayList<>();//存放中间结果的集合，为什么不用栈，因为最后还要用逆序输出，
                                            // 并且s2只有存储操作，没有取数据的操作
        for (String item : infixExpList) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {//如果是左括号“(”，则直接压入s1
                s1.push(item);
            } else if (")".equals(item)) {//如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();//消除小括号，将 "(" 弹出s1栈
            } else {
                //只要当前item的优先级小于或等于s1栈顶符号的优先级，将s1栈顶的运算符弹出并加入s2中，并持续此操作
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);//知道当前扫描到的符号优先级比s1栈顶的运算符优先级高，则将当前的运算符压入s1
            }
        }
        //将s1中剩余的运算符依次弹出并压入s2
        while (s1.size() > 0) {
            s2.add(s1.pop());
        }
        return s2;//因为是存到list中，所以正常输出即可
    }

    /**
     * 将表达式装入集合中，方便遍历
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    /**
     * 计算逆波兰表达式
     */
    public static int calculate(List<String> ls) {
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String s : ls) {
            //使用正则表达式
            if (s.matches("\\d+")) {
                //如果是数字，则直接入栈
                stack.push(s);
            } else {
                //pop出两个数并运算，运算符号即为当前扫描到的符号
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if (s.equals("-")) {
                    res = num1 - num2;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类，返回运算符对应的优先级
class Operation{
    private static int add = 1;
    private static int sub = 1;
    private static int mul = 2;
    private static int div = 3;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = add;
                break;
            case "-":
                result = sub;
                break;
            case "*":
                result = mul;
                break;
            case "/":
                result = div;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
