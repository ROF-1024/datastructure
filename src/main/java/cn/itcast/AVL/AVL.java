package cn.itcast.AVL;


/**
 * @Author xujie
 * @Date 2020/6/27 10:37
 */
public class AVL {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 7, 6, 8, 5};
//        int[] arr = {10, 11, 7, 6, 8, 9};
        int[] arr = {2,1,6,5,7,3};
//        int[] arr = {4, 3, 6, 5, 7, 8};
        AVLTree avlTree = new AVLTree();

        for (int i : arr) {
            avlTree.add(new Node(i));
        }

        System.out.println("中序遍历:");
        avlTree.infixOrder();
        System.out.println("AVLTree的根节点为：" + avlTree.getRoot());
        System.out.println("树的高度为：" + avlTree.getRoot().heightOfTree());
        System.out.println("------------------");
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    //查找目标节点targetNode
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找目标节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

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
            //1.如果要删除的是叶子结点：
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

    /**
     * 找到给定节点的右子树中的最小值所在节点，返回该节点的值，并删除该节点
     *
     * @param node
     * @return
     */
    private int minOfRightTree(Node node) {
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp.value;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回该节点的左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.heightOfTree();
        }
    }

    //返回该节点的右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.heightOfTree();
        }
    }

    //返回以该节点为根结点的树的高度
    public int heightOfTree() {
        int height1, height2;
        if (left == null) {
            height1 = 0;
        } else {
            height1 = left.heightOfTree();
        }
        if (right == null) {
            height2 = 0;
        } else {
            height2 = right.heightOfTree();
        }
        return Math.max(height1, height2) + 1;//只要是叶子结点，就会返回1
     /* 精简写法：
        return Math.max(left == null ? 0 : left.heightOfTree(),
                right == null ? 0 : right.heightOfTree()) + 1;*/
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

        //添加完一个结点后，如果：右子树的高度-左子树的高度 > 1，左旋转
        if (rightHeight() - leftHeight() > 1) {
//            leftRotate();
            /**
             * 同理：例如数组：{2,1,6,5,7,3}
             * 1.当，当前结点符合左旋转的条件时，
             * 2.先判断，当前结点的右结点的左子树高度 > 当前结点的右结点的右子树的高度，则：
             *          先对当前结点的右结点进行右旋转
             * 3.再对当前结点的右结点进行右旋转
             */
            if (right != null && this.right.leftHeight() > this.right.rightHeight()) {
                this.right.rightRotate();
                this.leftRotate();
            } else {
                this.leftRotate();
            }
            return;
        }

        if (leftHeight() - rightHeight() > 1) {
//            rightRotate();
            /**
             * 问题分析：例如数组{10,11,7,6,8,9}，只进行一次有旋转，仍然无法完成平衡二叉树的转换
             * 1.当，当前结点符合右旋转的条件
             * 2.先判断：如果当前节点的左结点的右子树高度 > 当前节点的左结点的左子树高度，则：
             *              先对当前结点的左结点进行左旋转
             * 3.再对当前结点进行右旋转
             */
            if (left != null && this.left.rightHeight() > this.left.leftHeight()) {
                this.left.leftRotate();
                this.rightRotate();
            } else {
                this.rightRotate();
            }
        }
    }

    //左旋转：
    public void leftRotate() {
        /**
         * 1.创建新结点，新结点的值等于当前结点的值
         * 2.新结点的左结点设置为当前结点的左结点，新结点的右结点设置为当前借点的右结点的左结点
         * 3.当前结点的值设置为当前结点的右结点的值
         * 4.当前结点的左结点指向新结点，当前结点的右结点指向当前结点的右结点的右结点
         */
        Node newNode = new Node(this.value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.value = this.right.value;
        this.left = newNode;
        this.right = this.right.right;
    }

    //右旋转：
    public void rightRotate() {
        /**
         * 1.创建新结点，新结点的值等于当前结点的值
         * 2.新结点的右结点设置为当前结点的右结点，新结点的左结点设置为当前借点的左结点的右结点
         * 3.当前结点的值设置为当前结点的左结点的值
         * 4.当前结点的右结点指向新结点，当前结点的左结点指向当前结点的左结点的左结点
         */
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.right = newNode;
        this.left = this.left.left;
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

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}