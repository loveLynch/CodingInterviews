package com.huawei;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-04-10. <br>
 * 目标：有多组整数数组，将它们合并成一个新的数组。
 * 规则：每个组按顺序取出固定长度的内容合并到新的数组中，取完的内容
 * 会删除掉，如果该行不足固定长度或者为空，则取出剩余部分的内容到新
 * 的数组中，继续下一行。
 * 输入描述：第一行是每次读取的固定长度
 * 第2-n行是需要合并的数组，不同的数组用换行符分隔，数组
 * 内用逗号分隔。
 * 输出描述：输出一个新的数组，用逗号分隔。
 * 样例
 * 输入：
 * 3
 * 2,5,6,7,9,5,7
 * 1,7,4,3,4
 * 输出：
 * 2，5，6，1，7，4，7，9，5，3，4，7
 **/
public class Online_4_3_1 {
    public static void main(String[] args) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        do {
            List<Integer> singleList = new ArrayList<>();
            String str = in.nextLine();
            if (str.isEmpty())
                break;
            String[] line = str.split(",");
            for (String singNum : line) {
                singleList.add(Integer.valueOf(singNum));
            }
            list.add(singleList);
        } while (true);
        printInput(list);
        List<Integer> outList = splitCombination(list, n);
        for (int i = 0; i < outList.size() - 1; i++) {
            System.out.print(outList.get(i) + ",");
        }
        System.out.println(outList.get(outList.size() - 1));

    }

    public static void printInput(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<Integer> singleList = list.get(i);
            for (int j = 0; j < singleList.size() - 1; j++) {
                System.out.print(singleList.get(j) + ",");
            }
            System.out.println(singleList.get(singleList.size() - 1));
        }

    }

    public static int getMaxLength(List<List<Integer>> list) {
        int maxLength = list.get(0).size();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).size() > maxLength)
                maxLength = list.get(i).size();
        }
        return maxLength;

    }

    public static List<Integer> splitCombination(List<List<Integer>> list, int splitNum) {
        List<Integer> outList = new ArrayList<>();
        int maxLength = getMaxLength(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() >= splitNum) {
                for (int j = 0; j < splitNum; j++) {
                    outList.add(list.get(i).get(j));
                }
                for (int j = 0; j < splitNum; j++) {
                    list.get(i).remove(j);
                }

            } else {

            }

        }
        return outList;
    }

}
