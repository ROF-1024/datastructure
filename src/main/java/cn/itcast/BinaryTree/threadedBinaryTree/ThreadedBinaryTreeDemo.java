package cn.itcast.BinaryTree.threadedBinaryTree;

/**
 * @Author xujie
 * @Date 2020/6/10 14:22
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode heroNode1 = new HeroNode(3, "jack");
        HeroNode heroNode2 = new HeroNode(6, "smith");
        HeroNode heroNode3 = new HeroNode(8, "zhangsan");
        HeroNode heroNode4 = new HeroNode(10, "jerry");
        HeroNode heroNode5 = new HeroNode(14, "lisi");
        root.setLeft(heroNode1);
        root.setRight(heroNode2);
        heroNode1.setLeft(heroNode3);
        heroNode1.setRight(heroNode4);
        heroNode2.setLeft(heroNode5);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNode();
//        threadedBinaryTree.threadedBinaryTreeList();
        HeroNode right = heroNode2.getRight();
        HeroNode node4Left = heroNode4.getLeft();
        HeroNode node4Right = heroNode4.getRight();
        System.out.println(node4Left);
        System.out.println(node4Right);
        System.out.println(right);

    }
}
//定义threadedBinaryTree,实现线索化功能的二叉树
class ThreadedBinaryTree{

    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    private HeroNode pre = null;//辅助线索化

    public void threadedNode() {
        this.threadedNode(this.root);
    }
    /**
     * 编写对二叉树中序线索化的方法：
     * @param node：当前需要线索化的节点
     */
    public void threadedNode(HeroNode node) {
        //node==null,不能线索化
        if (node == null) {
            return;
        }
        /**
         * 步骤思路：
         * 1.先线索化当前节点的左节点
         * 2.线索化当前节点
         * 3.线索化当前节点的右节点
         */
        //1.线索化当前节点的左节点：
        if (node.getLeft() != null) {
            threadedNode(node.getLeft());
        }

        //2.线索化当前节点
        //2.1 先线索化当前节点的前驱节点
        if (node.getLeft() == null) {
            //node（当前节点）的左节点为空时，让node的左节点指向前驱节点，并leftType的值设置为1，表示指向前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //2.2 再线索化当前节点的后继节点，不过此处的线索化，是等到下一轮，即pre指到当前的node，node此时已经指到了
        //node的rightNode，rightNode进入线索化递归，只要此时pre的rightNode为空，再讲pre的rightNode指向现在的node
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;


        //3.线索化当前节点的右节点
        if (node.getRight() != null) {
            threadedNode(node.getRight());
        }
    }

    /**
     * 对中序线索化二叉树进行遍历：
     */
    public void threadedBinaryTreeList() {
        HeroNode node = root;
        /*
        while (true) {
            if (node.getLeft() == null) {
                break;
            }
            node = node.getLeft();
        }
        System.out.println(node);
        while (true) {
            if (node.getRight() == null) {
                break;
            }
            System.out.println(node.getRight());
            //无法输出最后的的节点6 smith，因为最后Smith节点的后继节点没有设置为null
            node = node.getRight();
        }*/
        //利用leftType和rightType遍历
        //先找到leftType==1的节点，就是线索化二叉树最开始的节点：
        while (node != null) {
            //找到开始节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //输出这个节点
            System.out.println(node);
            //只要当前节点的右指针指向后继节点，就一直输出
            while (node.getRightType() == 1) {
                System.out.println(node.getRight());
                node = node.getRight();
            }
            //直到节点的右指针指向右子树为止，
            node = node.getRight();
        }
    }


    //删除节点：
    public void delNode(int id) {
        if (root == null) {
            System.out.println("二叉树为空");
        } else {
            if (this.root.getId() == id) {
                root = null;
            }
            root.delNode(id);
        }
    }
    //前序遍历查找：
    public HeroNode preOrderSearch(int id) {
        if (root != null) {
            return root.preOrderSearch(id);
        } else {
            return null;
        }
    }
    //中序遍历查找：
    public HeroNode infixOrderSearch(int id) {
        if (root != null) {
            return root.infixOrderSearch(id);
        } else {
            return null;
        }
    }
    //后序遍历查找：
    public HeroNode postOrderSearch(int id) {
        if (root != null) {
            return root.postOrderSearch(id);
        } else {
            return null;
        }
    }
}

class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    private HeroNode resNode = null;//辅助查找

    /**
     * 说明：
     * 1.如果leftType==0,表示指向的是左子树，如果是1表示指向前驱节点
     * 2.如果rightType==0,表示指向右子树，如果是1表示指向后继节点
     */
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 前序遍历查找：（中序和后序思路类似）
     * 1.判断当前节点是否是我们要查找的节点，若是，则返回this，不是，进行第二步
     * 2.判断左子节点是否为空，不为空，递归前序查找，判断查找的结果，若结果不为空，返回递归结果，结果为空
     * 3.判断右子节点是否为空，若不为空，递归前序查找，判断查找的结果，结果不为空，返回递归结果，结果为空
     * 4.返回null
     * @param id
     * @return
     */
    public HeroNode preOrderSearch(int id) {
        System.out.println("开始前序查找");
        if (this.id == id) {
            return this;
        }
        if (this.left != null) {
            resNode = this.left.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(id);
        }
        return resNode;
    }
    //中序遍历查找：
    public HeroNode infixOrderSearch(int id) {
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(id);
        }

        if (resNode != null) {
            return resNode;
        }
        System.out.println("开始中序查找");
        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(id);
        }
        return resNode;
    }
    //后序遍历查找：
    public HeroNode postOrderSearch(int id) {
        System.out.println("进入后序查找~~~~");
        if (this.left != null) {
            resNode = this.left.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("开始后序查找");
        if (this.id == id) {
            return this;
        }
        return null;
    }

    /**
     * 删除二叉树节点规定:
     *      1.如果删除的是叶子节点，则删除该节点
     *      2.如果删除的不是叶子节点，则删除该子树
     * 思路：
     *      1.因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否是我们需要删除的节点，
     *      而不能判断当前节点是否是需要删除的节点
     *      2.二叉树为空，没有需要删除的节点，若二叉树为空，考虑root是不是要删除的节点，是，root置空，不是，递归第3步
     *          步骤2在BinaryTree中实现
     *      3.如果当前节点的左子节点不为空，并且左子节点就是需要删除的节点，直接将this.left==null即可，结束递归
     *      4.如果当前节点的右子节点不为空，并且左右子节点就是需要删除的节点，直接将this.right==null即可，结束递归
     *      5.若第3.4步没有删除节点，那么我们需要向左子树进行递归删除
     *      6.若第5步也没有删除节点，那么我们需要向右子树进行递归删除
     */
    public void delNode(int id) {
        //第3步：
        if (this.left != null && this.left.id == id) {
            this.left = null;
            return;
        }
        //第4步：
        if (this.right != null && this.right.id == id) {
            this.right = null;
            return;
        }
        //第5步：
        if (this.left != null) {
            this.left.delNode(id);
        }
        //第6步：
        if (this.right != null) {
            this.right.delNode(id);
        }
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

}


