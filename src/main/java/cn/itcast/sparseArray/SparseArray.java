package cn.itcast.sparseArray;

import java.io.*;
import java.util.Arrays;

/**
 * @author xujie
 * @date 2020/4/19-11:04
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //二维数组转为稀疏数组
        //1.遍历二维数组，得到sum
        int sum = 0;
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                if (chessArray[x][y] != 0) {
                    sum++;
                }
            }
        }
        //2.创建稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //初始化稀疏数组中第一行数据
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //3.将二维数组中的非零数据存入稀疏数组中
        int count = 1;//计数器：计数稀疏数组的行
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                if (chessArray[x][y] != 0) {
                    sparseArray[count][2] = chessArray[x][y];
                    sparseArray[count][0] = x;
                    sparseArray[count][1] = y;
                    count++;
                }
            }
        }
        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组转为二维数组
        //1.读取稀疏数组第一行的行列数，创建二维数组
        int[][] chessArray1 = new int[sparseArray[0][0]][sparseArray[0][1]];
        //2.遍历稀疏数组，将稀疏数组的值赋给二维数组
        for (int x = 1; x <= sum; x++) {
            chessArray1[sparseArray[x][0]][sparseArray[x][1]] = sparseArray[x][2];
        }

        //输出恢复后的二维数组
        for (int[] row : chessArray1) {
            for (int data : row) {
                System.out.print(data+"\t");
            }
            System.out.println();
        }

        /*

        //序列化
        StringBuilder sb = new StringBuilder();
        for (int[] ints : chessArray) {
            String s = Arrays.toString(ints);
            sb.append(s);
            sb.append("\r\n");
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("map.data"));
            oos.writeObject(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //反序列化
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map.data"));
            StringBuilder stringBuilder= (StringBuilder)ois.readObject();
            System.out.println(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */

    }
}
