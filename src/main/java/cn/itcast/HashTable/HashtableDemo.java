package cn.itcast.HashTable;

import java.util.Scanner;

/**
 * @Author xujie
 * @Date 2020/6/5 22:37
 */
public class HashtableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);

        Scanner scanner = new Scanner(System.in);
        String key = "";
        while (true) {
            System.out.println("请输入你的选择：");
            System.out.println("a:" + "添加雇员");
            System.out.println("add:" + "根据id大小添加雇员");
            System.out.println("l:" + "查看全部雇员");
            System.out.println("f:" + "根据id查找雇员");
            System.out.println("e:" + "退出");
            key = scanner.next();
            switch (key) {
                case "a":
                    System.out.println("请输入雇员id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入雇员姓名：");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    System.out.println("添加完成");
                    break;
                case "add":
                    System.out.println("请输入雇员id：");
                    int id2 = scanner.nextInt();
                    System.out.println("请输入雇员姓名：");
                    String name2 = scanner.next();
                    Emp emp2 = new Emp(id2, name2);
                    hashTable.addByOrder(emp2);
                    System.out.println("添加完成");
                    break;
                case "l":
                    hashTable.list();
                    break;
                case "f":
                    System.out.println("请输入您要查找的雇员id：");
                    id = scanner.nextInt();
                    Emp empById = hashTable.findEmpById(id);
                    if (empById != null) {
                        System.out.println("您要查找的雇员信息为：" + empById.id + ":" + empById.name);
                    } else {
                        System.out.println("您要查找的雇员不存在");
                    }
                    break;
                case "e":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("请输入正确的选择");
                    break;
            }
        }
    }
}
//哈希表是元素为链表的数组
class HashTable {
    private EmpLinkedList[] empLinkedLists;

    //构造器
    public HashTable(int size) {
        //初始化empLinkedListArray
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
        /**
         * 注意：此处要将EmpLinkedList逐一初始化
         * 虽然数组empLinkedLists已经初始化了，但是数组里面的每一个EmpLinkedList都是null
         * 如果不初始化，会出现 空指针异常NullPointThreadException
         * 就像定义一个数组学生数组，Student[] students=new Student[10];里面包含十个学生
         * students已经初始化了，但是里面的每一个Student对象都没有初始化，
         * 如果调用第一个学生的名字：students[0].name，就会出现空指针异常
         * 假定学生类定义如下：
         *  class Student{
         *      int id;
         *      String name;
         *  }
         */
    }

    //添加雇员
    public void add(Emp emp) {
        int index = hashFunction(emp.id);
        empLinkedLists[index - 1].add(emp);
    }

    //按照id大小从小到大的顺序添加雇员
    public void addByOrder(Emp emp) {
        int index = hashFunction(emp.id);
        empLinkedLists[index - 1].addByOrder(emp);
    }

    //编写散列函数，使用一个简单取模法
    public int hashFunction(int id) {
        return id % 7;
    }

    //遍历emplinkedlists
    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            System.out.print("第"+(i+1)+"组链表信息为： ");
            empLinkedLists[i].list();
        }
    }

    //根据id查找雇员
    public Emp findEmpById(int id) {
        int index = hashFunction(id);
        Emp empById = empLinkedLists[index - 1].findEmpById(id);
        if (empById != null) {//找到
            return empById;
        } else {//未找到
            return null;
        }
    }
}

class Emp {
    int id;
    String name;
    public Emp next;

    public Emp() {}

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

//构造存储工作人员的链表
// LinkedList底层就是链表，不代表此处EmpLinkedList直接用链表存储，而是我们在通过代码模拟链表存储自定义对象
class EmpLinkedList {

    private Emp head;//头节点，默认为空

    //EmpLinkedList添加工作人员的功能
    public void add(Emp emp) {
        //将Emp添加到链表末尾
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;//若链表不为空，则使用辅助指针，帮助指向到链表的最后，再添加雇员
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void addByOrder(Emp emp) {
        boolean flag = false;
        Emp curEmp = head;
        if (curEmp == null) {
            head = emp;
            return;
        }
        if (curEmp.next == null) {
            if (curEmp.id == emp.id) {
                System.out.println("无法添加，id已存在");
                return;
            }
            if (curEmp.id > emp.id) {
                Emp temp = head;//引入临时变量
                head = emp;
                emp.next = temp;
                return;
            }
            head.next = emp;
            return;
        } else {
            while (true) {
                if (curEmp.next.id > emp.id) {
                    break;
                }
                if (curEmp.next.id == emp.id) {
                    flag = true;
                    break;
                }
                curEmp = curEmp.next;
            }
            if (flag) {
                System.out.println("id已存在，无法添加");
            } else {
                emp.next = curEmp.next;
                curEmp.next = emp;
            }
        }
    }

    //EmpLinkedList遍历已存储的工作人员的功能
    public void list() {
        if (head == null) {
            System.out.println("链表信息为空~");
            return;
        }

        Emp curEmp = head;//同样用辅助指针，遍历链表
        while (true) {
            System.out.print(curEmp.id+": "+curEmp.name+"  ");
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据输入的id查找雇员
    public Emp findEmpById(int id) {
        if (head == null) {
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                return curEmp;
            }
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        return null;
    }
}


