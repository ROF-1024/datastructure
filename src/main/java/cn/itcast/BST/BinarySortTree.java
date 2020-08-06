package cn.itcast.BST;


/**
 * @Author xujie
 * @Date 2020/6/24 13:59
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BST binarySortTree = new BST();
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }
        binarySortTree.infixOrder();
        System.out.println("----------");

        binarySortTree.delNode(7);
        binarySortTree.infixOrder();
        System.out.println("----------");

        binarySortTree.delNode(3);
        binarySortTree.infixOrder();
        System.out.println("----------");

        binarySortTree.delNode(1);
        binarySortTree.infixOrder();
        System.out.println("----------");

        binarySortTree.delNode(12);
        binarySortTree.infixOrder();
        System.out.println("----------");

        binarySortTree.delNode(2);
        binarySortTree.infixOrder();
        System.out.println("----------");

        binarySortTree.delNode(5);
        binarySortTree.infixOrder();
        System.out.println("----------");

        binarySortTree.delNode(10);
        binarySortTree.infixOrder();
        System.out.println("----------");


    }
}

//二叉排序树
class BST {
    Node root;

    public BST() {
    }

    public BST(Node root) {
        this.root = root;
    }

    //添加节点：
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历二叉排序树
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空，无法遍历");
        } else {
            root.infixOrder();
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 叶子结点的删除
     * 1.先找出目标结点targetNode，
     * 2.再找出目标结点的父节点parentNode
     * 3.1如果目标结点是父节点的左节点：parentNode.left=null
     * 3.2果目标结点是父节点的右节点：parentNode.right=null
     * 只有一颗子树的非叶子节点的删除
     * 1.先找出目标结点targetNode，
     * 2.再找出目标结点的父节点parentNode
     * 3.1 如果目标节点是父节点的左节点
     * 3.1.1 那一颗子树是目标节点的左子树，那么parentNode.left=targetNode.left
     * 3.1.2 那一颗子树是目标节点的右子树，那么parentNode.left=targetNode.right
     * 3.2 如果目标节点是父节点的右节点
     * 3.1.1 那一颗子树是目标节点的左子树，那么parentNode.right=targetNode.left
     * 3.1.2 那一颗子树是目标节点的右子树，那么parentNode.right=targetNode.right
     * 有两颗子树的非叶子结点的删除
     * 1.先找出目标结点targetNode，
     * 2.再找出目标结点的父节点parentNode
     * 3.找到目标节点【右子树的最小值】！
     * 4.用一个临时变量temp，将最小值保存
     * 5.删除该最小节点
     * 6.targetNode.value=temp
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //能够找到targetNode，说明二叉树尚有结点存在，又root左右为空，说明只剩一个root
            //结点且root结点就是targetNode
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //查找targetNode的父节点：
            Node parent = searchParent(value);
            //1.叶子结点的删除：
            if (targetNode.left == null && targetNode.right == null) {
                //如果叶子结点是父节点的左子节点
                if (parent.left != null && targetNode == parent.left) {
                    parent.left = null;
                }
                if (parent.right != null && targetNode == parent.right) {
                    parent.right = null;
                }

            } else if (targetNode.left != null && targetNode.right != null) {
                //3.有两颗子树的目标节点的删除
                //找到目标节点的右子树的最小值
                int minOfRightTree = minOfRightTree(targetNode.right);
                targetNode.value = minOfRightTree;

            } else {
                //2.只有一颗子树的目标节点的删除
                //如果目标节点是父节点的左节点
                if (parent != null) {
                     /**
                      * 如果此时root是targetNode，但是此时root有一颗子树，那么此时parent就是null
                      * 因此要加上parent！=null的判断
                      */
                    if (parent.left != null && targetNode == parent.left) {
                        if (targetNode.left != null) {
                            parent.left = targetNode.left;
                        } else {
                            parent.left = targetNode.right;
                        }
                    }
                    //如果目标节点是父节点的右节点
                    if (parent.right != null && targetNode == parent.right) {
                        if (targetNode.left != null) {
                            parent.right = targetNode.left;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }
                } else {
                    if (targetNode.left != null) {
                        root = targetNode.left;
                    } else {
                        root = targetNode.right;
                    }
                }
            }

        }
    }

    private int maxOfLeftTree(Node node) {
        Node temp = node;
        while (temp.right != null) {
            temp = temp.right;
        }
        delNode(temp.value);
        return temp.value;
    }

    private int minOfRightTree(Node node) {
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp.value;
    }
}

//节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void add(Node node) {
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //查找目标节点：
    public Node search(int value) {
        if (this.value == value) {
            return this;
        }
        if (value < this.value && this.left != null) {
            return this.left.search(value);
        }
        if (value > this.value && this.right != null) {
            return this.right.search(value);
        }
        return null;
    }

    //查找父节点：
    public Node searchParent(int value) {
        if ((this.left != null && value == this.left.value)
                || (this.right != null && value == this.right.value)) {
            return this;
        }
        if (value < this.value && this.left != null) {
            return this.left.searchParent(value);
        }
        if (value > this.value && this.right != null) {
            return this.right.searchParent(value);
        }
        return null;
    }
}
