package cn.itcast.resursion;

import java.io.*;

/**
 * @author xujie
 * @date 2020/4/25-11:18
 *
 * 迷宫问题
 */
public class Labyrinth {
    public static void main(String[] args) {
        //创建一个二维数组，来模拟迷宫地图
        int[][] map = new int[8][7];
        //使用1表示墙，
        for (int j = 0; j < 7; j++) {
            map[0][j] = 1;
            map[7][j] = 1;
        }
        for (int i = 1; i < 7; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板，1表示
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"    ");
            }
            System.out.println();
        }

        boolean way = setWay(map, 1, 1);
        System.out.println(way);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"    ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归为小球找迷宫路线（走到右下角）
     * 约定：
     *      map[i][j]=0时，表示此点未走过，当为1表示墙，为2表示通路，可以走，
     *      3表示该位置已经走过，但走不通
     * 策略：
     *      路线选择顺序：下-> 右 -> 上 -> 左
     * @param map 迷宫地图，1表示不能通行
     * @param i 小球初始行，2，i=1
     * @param j 小球初始列，2，j=2
     * @return 找到通路，返回true
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }

            } else {
                return false;
            }
        }
    }
}
