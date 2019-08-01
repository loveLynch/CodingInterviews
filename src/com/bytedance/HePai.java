package com.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-02. <br>
 * 总共有36张牌，每张牌是1~9。每个数字4张牌。
 * 你手里有其中的14张牌，如果这14张牌满足如下条件，即算作和牌
 * 14张牌中有2张相同数字的牌，称为雀头。
 * 除去上述2张牌，剩下12张牌可以组成4个顺子或刻子。顺子的意思是递增的连续3个数字牌（例如234,567等），
 * 刻子的意思是相同数字的3个数字牌（例如111,777）
 **/
public class HePai {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String pai = input.nextLine();
        String[] paiArray = pai.split(" ");
        ArrayList<Integer> paiList = new ArrayList<>();
        for (String singlePai : paiArray)
            paiList.add(Integer.valueOf(singlePai));
        System.out.println(getHe(paiList));
    }

    private static int getHe(ArrayList<Integer> paiList) {
        if (paiList == null || paiList.size() < 14)
            return 0;
        HashMap<Integer, Integer> paiCount = new HashMap<>();
        for (Integer pai : paiList) {
            if (!paiCount.containsKey(pai)) {
                paiCount.put(pai, 1);
            } else {
                paiCount.put(pai, paiCount.get(pai) + 1);
            }
        }
        return 0;
    }

    private static boolean isHe(ArrayList<Integer> paiList) {
        return true;

    }
}
