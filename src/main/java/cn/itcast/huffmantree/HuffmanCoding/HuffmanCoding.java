package cn.itcast.huffmantree.HuffmanCoding;


import java.util.*;

/**
 * @Author xujie
 * @Date 2020/6/18 21:33
 */
public class HuffmanCoding {
    public static void main(String[] args) {
        String str = "i like like like like java do you like java";
        byte[] bytes = str.getBytes();
        System.out.println(bytes.length);
        List<Node> nodes = getNodes(bytes);
        System.out.println("nodes:"+nodes);
        System.out.println("-----------------");
        Node rootOfHuffmanTree = createHuffmanTree(nodes);
        preOrder(rootOfHuffmanTree);
    }

    /**
     * 思路：
     * 1.将赫夫曼编码放在Map<Byte,String>中
     * 2.在生成赫夫曼编码时，不要拼接路径，定义一个StringBuilder，存储某个节点的路径
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    /**
     * 生成赫夫曼树对应赫夫曼编码：将传入的node节点的所有叶子结点的赫夫曼编码得到，并放在，huffmanCodes集合中
     * @param node 传入的节点
     * @param code 传入节点的路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //code加入stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            if (node.value == null) {//非叶子结点
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//说明是叶子结点
                huffmanCodes.put(node.value, stringBuilder2.toString());

            }
        }

    }

    //接收字节数组，统计各字符出现的次数，返回返回List<Node>
    public static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        //遍历bytes，存储字节数组中字符出现的次数 ->Map
        Map<Byte,Integer> counts = new HashMap<Byte, Integer>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //将每一个键值对转化为Node对象，存储在list中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list构建赫夫曼树,返回根节点
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空，无法遍历！");
        }
    }
}

class Node implements Comparable<Node> {
    Byte value;
    int weight;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    public Node(Byte value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}