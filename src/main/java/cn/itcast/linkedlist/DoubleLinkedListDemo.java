package cn.itcast.linkedlist;

/**
 * @author xujie
 * @date 2020/4/22-22:45
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //双向链表的测试
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.add(heroNode2);

        doubleLinkedList.list();
        System.out.println("-------------------------");
        //测试修改
        HeroNode2 newHeroNode = new HeroNode2(2, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();
        System.out.println("-------------------------");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }
    //添加节点到双向链表最后
    public void add(HeroNode2 heroNode) {
        /*当不考虑编号的顺序时，
            1.找到当前链表的最后节点
            将最后这个节点的next指向新节点
         */
        //因为head节点不能动，需要一个辅助节点
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //当temp.next==null,那么就找到的了链表的最后节点
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //形成双向链表
        temp.next= heroNode;
        heroNode.pre = temp;
    }


    /**
     * 修改节点信息:根据heroNode的编号修改即可
     * @param heroNode
     */
    public void update(HeroNode2 heroNode) {
        HeroNode2 temp = head.next;
        if (temp == null) {
            System.out.println("链表无数据...");
            return;
        }
        boolean flag = false;
        while (true) {
            if (temp == null) {//链表已经遍历结束
                System.out.println("链表中无此编号:"+heroNode.no);
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                System.out.println("执行修改");
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.println("没有找到对应的编号");
        }
    }

    /**
     * 根据编号删除双向链表中的一个节点
     * 直接找到要删除的节点，找到后，自我删除即可
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("无此编号"+no);
                break;
            }
            if (temp.no == no) {
                temp.pre.next = temp.next;
                //下面这句代码有问题，若temp是最后一个节点，则会出现空指针异常
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
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
        HeroNode2 temp = head.next;
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
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;

    public HeroNode2(int hNo,String hName,String hNickName) {
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

