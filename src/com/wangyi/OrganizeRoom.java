package com.wangyi;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-02. <br>
 * 整理房间
 * 时间限制：1秒
 * <p>
 * 空间限制：262144K
 * <p>
 * 又到了周末，小易的房间乱得一团糟。
 * 他希望将地上的杂物稍微整理下，使每团杂物看起来都紧凑一些，没有那么乱。
 * 地上一共有n团杂物，每团杂物都包含4个物品。第i物品的坐标用(ai,bi)表示，小易每次都可以将它绕着(xi,yi)逆时针旋转，这将消耗他的一次移动次数。如果一团杂物的4个点构成了一个面积不为0的正方形，我们说它是紧凑的。
 * 因为小易很懒，所以他希望你帮助他计算一下每团杂物最少需要多少步移动能使它变得紧凑。
 * <p>
 * 输入描述:
 * 第一行一个数n(1 <= n <= 100)，表示杂物的团数。
 * 接下来4n行，每4行表示一团杂物，每行4个数ai, bi，xi, yi, (-104 <= xi, yi, ai, bi <= 104)，表示第i个物品旋转的它本身的坐标和中心点坐标。
 * <p>
 * 输出描述:
 * n行，每行1个数，表示最少移动次数。
 * <p>
 * 输入例子1:
 * 4
 * 1 1 0 0
 * -1 1 0 0
 * -1 1 0 0
 * 1 -1 0 0
 * 1 1 0 0
 * -2 1 0 0
 * -1 1 0 0
 * 1 -1 0 0
 * 1 1 0 0
 * -1 1 0 0
 * -1 1 0 0
 * -1 1 0 0
 * 2 2 0 1
 * -1 0 0 -2
 * 3 0 0 -2
 * -1 1 -2 0
 * <p>
 * 输出例子1:
 * 1
 * -1
 * 3
 * 3
 * <p>
 * 例子说明1:
 * 对于第一团杂物，我们可以旋转第二个或者第三个物品1次。
 **/

class Point {
    int x1;
    int y1;
    int x;
    int y;

    Point(int x1, int y1, int x, int y) {
        this.x1 = x1;
        this.y1 = y1;
        this.x = x;
        this.y = y;
    }
}

public class OrganizeRoom {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[][] points = new Point[n][4];
        int a, b, c, d;
        int[] reult = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextInt();
                d = sc.nextInt();
                points[i][j] = new Point(a, b, c, d);
            }
            reult[i] = moveIimes(points, i);
        }
        for (int i = 0; i < reult.length; i++) {
            System.out.println(reult[i]);
        }
    }

    //每个点有4中情况旋转0，1,2,3次，穷举
    private static int moveIimes(Point[][] pints, int i) {
        Point p1, p2, p3, p4;
        int count = 16;
        for (int j = 0; j < 4; j++) {
            //第一个点的
            p1 = rotate(pints[i][0], j);
            for (int k = 0; k < 4; k++) {
                p2 = rotate(pints[i][1], k);
                for (int l = 0; l < 4; l++) {
                    p3 = rotate(pints[i][2], l);
                    for (int m = 0; m < 4; m++) {
                        p4 = rotate(pints[i][3], m);
                        if (isSq(p1, p2, p3, p4)) {
                            count = Math.min(count, j + k + l + m);
                        }
                    }
                }
            }
        }
        return count == 16 ? -1 : count;
    }

    /**
     * @param p     原始点
     * @param times 旋转次数
     * @return 返回旋转后的点
     */
    private static Point rotate(Point p, int times) {
        int x = p.x1;
        int y = p.y1;
        int a = p.x;//中心点
        int b = p.y;
        for (int i = 1; i <= times; i++) {
            int tem = x;
            x = (b - y + a);
            y = (tem - a + b);
        }
        return new Point(x, y, a, b);
    }

    //判断四个点是否是正方形
    private static boolean isSq(Point p1, Point p2, Point p3, Point p4) {
        boolean rx = ((p1.x1) ^ (p2.x1) ^ (p3.x1) ^ (p4.x1)) == 0;//四个点的 x 坐标是否是两两相等
        boolean ry = ((p1.y1) ^ (p2.y1) ^ (p3.y1) ^ (p4.y1)) == 0;//四个点的 y 坐标是否是两两相等
        //是否是矩形
        if (!rx || !ry || rx && ry && (p1.x1 == p2.x1 && p1.x1 == p3.x1) ||
                rx && ry && (p1.y1 == p2.y1 && p1.y1 == p3.y1)) return false;
        //判断正方形
        int dx = Math.abs((p1.x1 - p2.x1) != 0 ? (p1.x1 - p2.x1) : (p1.x1 - p3.x1));
        int dy = Math.abs((p1.y1 - p2.y1) != 0 ? (p1.y1 - p2.y1) : (p1.y1 - p3.y1));
        return dx == dy;
    }
}
