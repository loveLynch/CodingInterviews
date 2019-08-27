package com.alibaba;

/**
 * Created by lynch on 2019-08-26. <br>
 * 输入：
 * <p>
 * 1,1
 * 0,0,0,2,2,2,2,0
 * <p>
 * 输出：
 * <p>
 * yes,0
 * <p>
 * <p>
 * <p>
 * 输入：
 * <p>
 * 2,2
 * 0,0,0,2,2,2,2,0
 * <p>
 * 输出：
 * <p>
 * yes,0
 * <p>
 * <p>
 * <p>
 * 输入：
 * <p>
 * 3,0
 * 0,0,0,2,2,2,2,0
 * <p>
 * 输出：
 * <p>
 * no,1
 * <p>
 * <p>
 * <p>
 * 输入：
 * <p>
 * 3,4
 * 0,0,0,2,2,2,2,0
 * <p>
 * 输出：
 * <p>
 * no,2
 **/

import java.util.*;
import java.util.List;

public class Main {
/** 请完成下面这个函数，实现题目要求的功能 **/
    /**
     * 当然，你也可以不按照这个模板来作答，完全按照自己的想法来
     **/
    public static String measureDistance(List<Double> xList, List<Double> yList, double x, double y) {

        int N = xList.size();
        int intersectCount = 0;//cross points count of x
        double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
        double px1, px2;//neighbour bound vertices
        double py1, py2;//neighbour bound vertices
        double px = x; //当前点
        double py = y; //当前点

        px1 = xList.get(0);//left vertex
        py1 = yList.get(0);//left vertex
        for (int i = 1; i <= N; ++i) {//check all rays
            if (px == px1 && py == py1) {
                return "yes,0";
            }

            px2 = xList.get(i % N);//right vertex
            py2 = yList.get(i % N);//right vertex
            if (px < Math.min(px1, px2) || px > Math.max(px1, px2)) {//ray is outside of our interests
                px1 = px2;
                py1 = py2;
                continue;//next ray left point
            }

            if (px > Math.min(px1, px2) && px < Math.max(px1, px2)) {//ray is crossing over by the algorithm (common part of)
                if (py <= Math.max(py1, py2)) {//x is before of ray
                    if (px1 == px2 && py >= Math.min(py1, py2)) {//overlies on a horizontal ray
                        return "yes,0";
                    }

                    if (py1 == py2) {//ray is vertical
                        if (py1 == py) {//overlies on a vertical ray
                            return "yes,0";
                        } else {//before ray
                            ++intersectCount;
                        }
                    } else {//cross point on the left side
                        double xinters = (px - px1) * (py2 - py1) / (px2 - px1) + py1;//cross point of y
                        if (Math.abs(py - xinters) < precision) {//overlies on a ray
                            return "yes,0";
                        }

                        if (py < xinters) {//before ray
                            ++intersectCount;
                        }
                    }
                }
            } else {//special case when ray is crossing through the vertex
                if (px == px2 && py <= py2) {//p crossing over p2
                    double px3 = xList.get((i + 1) % N); //next vertex
                    double py3 = yList.get((i + 1) % N); //next vertex
                    if (px >= Math.min(px1, px3) && px <= Math.max(px1, px3)) {//px lies between px1 & p3.x
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            px1 = px2;//next ray left point
            py1 = py2;//next ray left point
        }

        if (intersectCount % 2 == 0) {//偶数在多边形外
            int cha = (int) Math.sqrt(Math.pow((x - px1), 2) + Math.pow(y - py1, 2));
            return "no," + cha;
        } else { //奇数在多边形内
            return "yes,0";
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        //(x,y)为小广所在的位置
        double x = Double.parseDouble(line.split(",")[0]);
        double y = Double.parseDouble(line.split(",")[1]);

        line = in.nextLine();
        //xList记录了多边形n个点的x坐标,yList记录了多边形n个点的y坐标
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        String[] array = line.split(",");
        for (int i = 0; i < array.length; i++) {
            xList.add(Double.parseDouble(array[i]));
            yList.add(Double.parseDouble(array[i + 1]));
            i++;
        }
        in.close();
        System.out.println(measureDistance(xList, yList, x, y));
    }
}