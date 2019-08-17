package com.tencent;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-16. <br>
 * 画家小Q
 * 时间限制：1秒
 * <p>
 * 空间限制：32768K
 * <p>
 * 画家小Q又开始他的艺术创作。小Q拿出了一块有NxM像素格的画板, 画板初始状态是空白的,用'X'表示。
 * 小Q有他独特的绘画技巧,每次小Q会选择一条斜线, 如果斜线的方向形如'/',即斜率为1,小Q会选择这条斜线中的一段格子,都涂画为蓝色,用'B'表示;如果对角线的方向形如'\',即斜率为-1,小Q会选择这条斜线中的一段格子,都涂画为黄色,用'Y'表示。
 * 如果一个格子既被蓝色涂画过又被黄色涂画过,那么这个格子就会变成绿色,用'G'表示。
 * 小Q已经有想画出的作品的样子, 请你帮他计算一下他最少需要多少次操作完成这幅画。
 * <p>
 * 输入描述:
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含两个正整数N和M(1 <= N, M <= 50), 表示画板的长宽。
 * 接下来的N行包含N个长度为M的字符串, 其中包含字符'B','Y','G','X',分别表示蓝色,黄色,绿色,空白。整个表示小Q要完成的作品。
 * <p>
 * 输出描述:
 * 输出一个正整数, 表示小Q最少需要多少次操作完成绘画。
 * <p>
 * 输入例子1:
 * 4 4
 * YXXB
 * XYGX
 * XBYY
 * BXXY
 * <p>
 * 输出例子1:
 * 3
 * <p>
 * 例子说明1:
 * XXXX
 * XXXX
 * XXXX
 * XXXX
 * ->
 * YXXX
 * XYXX
 * XXYX
 * XXXY
 * ->
 * YXXB
 * XYBX
 * XBYX
 * BXXY
 * ->
 * YXXB
 * XYGX
 * XBYY
 * BXXY
 **/
public class Drawer {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String tem = scanner.nextLine();
        int n = Integer.valueOf(tem.split(" ")[0]);
        int m = Integer.valueOf(tem.split(" ")[1]);
        char color[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            tem = scanner.nextLine();
            for (int j = 0; j < tem.length(); j++) {
                color[i][j] = tem.charAt(j);
            }
        }

        // 获取最小步数
        getMinStep(n, m, color);

        scanner.close();

    }

    /**
     * 获取最小步数 每画一个线直到颜色不同为止
     *
     * @param n
     * @param m
     * @param color
     */
    private static void getMinStep(int n, int m, char color[][]) {

        int step = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (color[i][j] == 'Y') {
                    dfs_y(i, j, n, m, color); // 画y
                    step++;
                } else if (color[i][j] == 'B') {
                    dfs_b(i, j, n, m, color); // 画B
                    step++;
                } else if (color[i][j] == 'G') {
                    dfs_y(i, j, n, m, color); // 先画y
                    step++;
                    dfs_b(i, j, n, m, color); // 在画B
                    step++;
                }
            }
        }

        System.out.println(step);
    }

    /**
     * 当前位置画y,是否在后续位置继续画y
     *
     * @param x
     * @param y
     */
    private static void dfs_y(int x, int y, int n, int m, char color[][]) {
        // 根据要求决定当前位置是否能画y
        if (x >= 0 && x < n && y >= 0 && y < m && (color[x][y] == 'Y' || color[x][y] == 'G')) {
            if (color[x][y] == 'G') {
                color[x][y] = 'B'; // 如果当前位置要求画的是G,那么画了Y之后下一次只能画B
            } else {
                color[x][y] = 'X'; // 如果当前位置要求画的是Y,那么画了Y之后下一次不需要再画
            }

            // 是否连笔继续画，Y对应的是画\，即左上或者右下
            dfs_y(x - 1, y - 1, n, m, color);
            dfs_y(x + 1, y + 1, n, m, color);

        }

    }

    /**
     * 当前位置画B,是否在后续位置继续画B
     *
     * @param x
     * @param y
     */
    private static void dfs_b(int x, int y, int n, int m, char color[][]) {
        // 根据要求决定当前位置是否能画B
        if (x >= 0 && x < n && y >= 0 && y < m && (color[x][y] == 'B' || color[x][y] == 'G')) {
            if (color[x][y] == 'G') {
                color[x][y] = 'Y'; // 如果当前位置要求画的是G,那么画了Y之后下一次只能画Y
            } else {
                color[x][y] = 'X'; // 如果当前位置要求画的是B,那么画了B之后下一次不需要再画
            }

            // 是否连笔继续画，B对应的是画/，即左下或者右上
            dfs_b(x + 1, y - 1, n, m, color);
            dfs_b(x - 1, y + 1, n, m, color);

        }
    }

}
