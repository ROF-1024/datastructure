package cn.itcast.linkedlist;

/**
 * @author xujie
 * @date 2020/4/23-10:40
 *
 * 约瑟夫问题
 */
public class Josephus {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(5);
        circleLinkedList.showBoy();
        //测试小孩出圈
        circleLinkedList.countBoy(1, 2, 5);
    }
}

//创建单向环形链表
class CircleLinkedList {
    private Boy first = null;

    //添加节点，构成环形链表
    public void addBoy(int nums) {
        //先做一些数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确，不能小于1");
            return;
        }
        //用for循环构建环形链表
        Boy curBoy = null;//辅助构建
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前链表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空。。。");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩编号：" + curBoy.getNo());
            if (curBoy.getNext() == first) {
                System.out.println("遍历完成");
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * Josephus问题代码实现：
     * @param startNo：从第几个小孩开始数
     * @param countNum：表示数几下
     * @param nums：表示最初有几个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入...");
        }
        //创建辅助指针，指向first前一个节点，帮助小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //报数之前，让helper和first同时移动startNo-1步
        for (int i = 1; i < startNo;i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始出圈
        while (true) {
            if (helper == first) {
                System.out.println();
                break;
            }
            for (int j = 1; j < countNum; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("出圈小孩标号：" + first.getNo());
            //删除出圈小孩所在节点
            first = first.getNext();//将指向出圈小孩节点的指针往后移动一下
            helper.setNext(first);
        }
        System.out.println("最后一个出圈小孩标号：" + first.getNo());
    }
}

//Boy类，表示节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
