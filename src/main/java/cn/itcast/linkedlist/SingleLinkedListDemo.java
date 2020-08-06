package cn.itcast.linkedlist;

import java.util.Stack;

/**
 * @author xujie
 * @date 2020/4/21-16:52
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.add(heroNode4);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode3);
/*        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode3);*/
        singleLinkedList.list();
        System.out.println("--------------------");
       /* HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();
        System.out.println("--------------------");
        singleLinkedList.delete(3);

        //显示
        singleLinkedList.list();
        singleLinkedList.delete(1);
        System.out.println("--------------------");
        singleLinkedList.list();

        System.out.println("--------------------");
        singleLinkedList.delete(2);
        singleLinkedList.delete(4);
        singleLinkedList.list();*/
        //测试反转单链表
//        reverseList(singleLinkedList.getHead());
        reversePrint(singleLinkedList.getHead());
    }

    /**
     * 反转链表（带头节点的单链表）
     * @param head
     */
    public static void reverseList(HeroNode head) {
        //链表为空或只有一个节点时，无需反转，直接返回
        if (head.next == null||head.next.next==null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点cur的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，将其放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            System.out.println("空链表，不能打印");
            return;
        }
        //创建一个栈，将各节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur !=null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//栈的特点先进后出
        }
    }
}

//定义SingleLinkedList，管理HeroNode
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode heroNode) {
        /*当不考虑编号的顺序时，
            1.找到当前链表的最后节点
            将最后这个节点的next指向新节点
         */
        //因为head节点不能动，需要一个辅助节点
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //当temp.next==null,那么就找到的了链表的最后节点
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种方式添加：按照no顺序添加节点
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;//标识新节点的编号是否存在
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {//不能添加，编号已经存在
            System.out.println("编号已经存在,无法再加入" + heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点信息:根据heroNode的编号修改即可
     * @param heroNode
     */
    public void update(HeroNode heroNode) {
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表无数据...");
            return;
        }
        while (true) {
            if (temp == null) {//链表已经遍历结束
                System.out.println("链表中无此编号:"+heroNode.no);
                break;
            }
            if (temp.no == heroNode.no) {
                System.out.println("执行修改");
                temp.name = heroNode.name;
                temp.nickName = heroNode.nickName;
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 根据编号删除节点
     * @param no
     */
    public void delete(int no) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("无此编号"+no);
                break;
            }
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    //通过遍历显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空...");
                return;
        }
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出该节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

}

//定义一个HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int hNo,String hName,String hNickName) {
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}