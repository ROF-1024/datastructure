package cn.itcast.BinaryTree;

/**
 * @Author xujie
 * @Date 2020/6/8 22:04
 *
 * 前中后序遍历、前中后序查找、删除节点
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "林冲");
        HeroNode heroNode5 = new HeroNode(5, "关胜");

        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);
        BinaryTree binaryTree = new BinaryTree(root);

        //测试遍历：
        /*binaryTree.preOrder();
        System.out.println("-------------");
        binaryTree.infixOrder();
        System.out.println("-------------");
        binaryTree.postOrder();*/

        //测试查找：
       /* HeroNode preOrderSearch = binaryTree.preOrderSearch(5);
        if (preOrderSearch != null) {
            System.out.println("节点信息为：" + preOrderSearch);
        } else {
            System.out.println("未查找到");
        }
        System.out.println("-----------------------------------------");
        HeroNode infixOrderSearch = binaryTree.infixOrderSearch(5);
        if (infixOrderSearch != null) {
            System.out.println("节点信息为：" + infixOrderSearch);
        } else {
            System.out.println("未查找到");
        }
        System.out.println("-----------------------------------------");
        HeroNode postOrderSearch = binaryTree.postOrderSearch(5);
        if (postOrderSearch != null) {
            System.out.println("节点信息为：" + postOrderSearch);
        } else {
            System.out.println("未查找到");
        }*/

        //测试删除：
        System.out.println("删除前：");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("---------------");
        System.out.println("删除后：");
        binaryTree.preOrder();
    }
}

/**
 * @Author xujie
 * @Date 2020/6/10 14:18
 */
//创建二叉树
class BinaryTree{
    //只要有根节点，皆可以构成一颗二叉树
    private HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    //前序遍历：
    public void preOrder() {
        if (root != null) {//这里的root就是构建binaryTree时传进来的root，等同于this.root
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无需遍历");
        }
    }
    //中序遍历：
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，无需遍历");
        }
    }
    //后序遍历：
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空，无需遍历");
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

/**
 * @Author xujie
 * @Date 2020/6/10 14:12
 */ //创建节点：
class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private HeroNode resNode = null;//辅助查找


    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //前序遍历：
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    //中序遍历：
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //后序遍历：
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
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
