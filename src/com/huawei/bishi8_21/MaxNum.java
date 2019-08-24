package com.huawei.bishi8_21;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-22. <br>
 **/
public class MaxNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        //用小数点代替字母
        str = str.replaceAll("[a-zA-Z]", ".");
        String[] strArray = str.split("\\.");
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals(""))
                numList.add(0);//用0代替空，防止字母隔开的数组合
            else {
                numList.add(Integer.valueOf(strArray[i]));
            }
        }
        if (str.length() == 1)
            System.out.println(str);
        else {
            double max = getMaxNum(numList);
            if (String.valueOf(max).endsWith(".0")) {
                System.out.println(String.valueOf(max).split("\\.")[0]);
            } else {
                System.out.println(max);
            }
        }

    }

    public static double getMaxNum(List<Integer> numList) {
        double max = numList.get(0);
        for (int i = 0; i < numList.size() - 1; i++) {
            if (numList.get(i) > max)
                max = numList.get(i);
            double combinationNum = combinationNum(numList.get(i), numList.get(i + 1));
            if (combinationNum > max)
                max = combinationNum;


        }
        return Math.max(max, numList.get(numList.size() - 1));
    }

    /**
     * 组合小数点前后数字
     *
     * @param a
     * @param b
     * @return
     */
    public static double combinationNum(int a, int b) {
        return Double.parseDouble(a + "." + b);
    }
}
