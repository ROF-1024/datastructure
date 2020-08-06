package cn.itcast.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author xujie
 * @Date 2020/6/30 13:51
 */
public class Graph {
    public static void main(String[] args) {
        int n = 8;
        Graph graph = new Graph(n);
        /*String[] vertexes = {"A", "B", "C", "D", "E"};
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);*/
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();
        System.out.println("深度搜索算法：");
        graph.dfs();
        System.out.println("------------");
        System.out.println("广度搜索算法：");
        graph.bfs();
    }

    private ArrayList<String> vertexList;//存储图的顶点
    private int[][] edges;//邻接矩阵，表示图
    private int numOfEdges;//存储边的数量

    public Graph(int n) {
        vertexList = new ArrayList<String>(n);
        edges = new int[n][n];
        numOfEdges = 0;
//        isVisited = new boolean[n];放在算法的开头再初始化，这样算法可以多次调用，
//        避免在类的初始化时初始化，只能用一次结果就全部改为true
    }

    /**
     * 图的遍历：深度优先搜索（DFS:depth first search）
     * 步骤：
     * 1.访问初始节点v1，并标记v1为已访问
     * 2.查找v1的第一个邻接结点v2，
     * 3.1 如果v2存在，执行4
     * 3.2 如果v2不存在，将v1的下一个结点视为新的v1，回溯到第1步，
     * 4.如果v2存在，且没有被访问，标记v2为已访问，将v2视为v1，递归到第1步，
     * 5.如果v2存在，但是v2已经被访问，获取v1除了v2之外的邻接结点，也记为v2，只要v2存在，循环4.5步
     */
    private boolean[] isVisited;//存储各节点是否已被访问，初始化放在构造方法中,初始值是false

    //查找v1的第一个邻接点
    public int getFirstNeighbor(int v1) {
        for (int j = v1 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //查找v1是否存在另外的邻接点v3
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    public void dfs(boolean[] isVisited, int v1) {
        //输出结点信息
        System.out.println(getValueByIndex(v1));
        //标记为已访问
        isVisited[v1] = true;

        int v2 = getFirstNeighbor(v1);
        while (v2 != -1) {//v2存在
            if (!isVisited[v2]) {//v2没有被访问
                dfs(isVisited, v2);
            }
            //v2已经被访问
            v2 = getNextNeighbor(v1, v2);
        }
        //v2不存在，就回到dfs(),对v1的下一个点进行v1的操作
    }

    /**
     * 图的遍历：广度优先搜索（BFS:broad first search）
     * 需要一个队列，以保持访问过的结点的顺序，以便按照这个顺序来访问这些结点的邻接结点
     * 步骤：
     * 1.访问初始节点v1，并标记v1为已访问
     * 2.结点v1加入队列，
     * 3.若队列非空，则继续执行步骤4，否则，对v1结点的算法结束
     * 4.出队列，取得头结点，u，
     * 5.查找u的第一个邻接结点w，
     * 6 若结点u的邻接结点w不存在，则转到步骤3，若w存在，执行以下三个步骤
     * （1）结点w尚未被访问，将w标记为已访问，
     * （2）结点w加入队列
     * （3）查找u除了w之外的邻接节点，返回步骤6
     */
    private LinkedList queue = new LinkedList();

    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    private void bfs(boolean[] isVisited, int v1) {
        int u;//队列头结点对应下标
        int w;//队列头结点的邻接结点
        //1.访问初始节点v1，并标记v1为已访问
        System.out.println(getValueByIndex(v1));
        // 2.结点v1加入队列，(注意加入LinkedList最后，队列是先进先出)
        queue.addLast(v1);
        //3.若队列非空，则继续执行步骤4，否则，对v1结点的算法结束
        while (!queue.isEmpty()) {
            //4.出队列，取得头结点，u，
            u = (Integer) queue.removeFirst();
            //5.查找u的第一个邻接结点w
            w = getFirstNeighbor(u);
            //6.如果w存在
            while (w != -1) {
                //6.1 w没有被访问，输出w对应的value，标记为已访问
                if (!isVisited[w]) {
                    System.out.println(getValueByIndex(w));
                    isVisited[w] = true;
                    //6.2 并将w加入队列
                    queue.addLast(w);
                }
                //如果w存在，但是已经被访问，就看看u除了w还有没有其他邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }


    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     第一个顶点的下标
     * @param v2     第二个顶点的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回下标i对应的顶点值
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回顶点数量
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //获取边的数量
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
