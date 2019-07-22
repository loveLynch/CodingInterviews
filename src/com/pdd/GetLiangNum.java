package com.pdd;

/**
 * A 国的手机号码由且仅由 N 位十进制数字(0-9)组成。一个手机号码中有至少 K 位数字相同则被定义为靓号。A 国的手机号可以有前导零，比如 000123456 是一个合法的手机号。
 * 小多想花钱将自己的手机号码修改为一个靓号。修改号码中的一个数字需要花费的金额为新数字与旧数字之间的差值。比如将 1 修改为 6 或 6 修改为 1 都需要花 5 块钱。
 * 给出小多现在的手机号码，问将其修改成一个靓号，最少需要多少钱？
 * Created by lynch on 2019-07-22. <br>
 **/

import java.util.ArrayList;
import java.util.Scanner;

public class GetLiangNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String NK = input.nextLine();
        String originalNum = input.nextLine();
        Integer N = Integer.valueOf(NK.split(" ")[0]);
        Integer K = Integer.valueOf(NK.split(" ")[1]);
        if (K <= N) {
            if (originalNum.length() == N) {
                ArrayList<String> greatNum = getSpeedAndNum(originalNum, K);
                System.out.println(greatNum.get(0));
                System.out.println(greatNum.get(1));
            } else {
                System.out.println("the input num length is error!");
            }
        } else {
            System.out.println("the great num length over the sum");
        }
    }

    private static ArrayList<String> getSpeedAndNum(String originalNum, Integer k) {
        ArrayList<String> result = new ArrayList<>();
        int[] countNum = getNumCount(originalNum);
        int res = Integer.MAX_VALUE;
        String ans = "A";
        for (int i = 0; i < 10; i++) {
            String tmpNum = originalNum;
            int need = k - countNum[i];
            int cost = 0;
            int gap = 1;
            while (need > 0) {
                if (i + gap <= 9) {
                    if (countNum[i + gap] < need) {
                        tmpNum = tmpNum.replace(String.valueOf(i + gap), String.valueOf(i));
                        cost += countNum[i + gap] * gap;
                        need -= countNum[i + gap];
                    } else {
                        int needCount = 0; //限定替换次数，用replaceFirst()只替换第一次出现的
                        while (needCount < need) {
                            tmpNum = tmpNum.replaceFirst(String.valueOf(i + gap), String.valueOf(i));
                            needCount++;
                        }
                        cost += need * gap;
                        break;
                    }
                }
                if (i - gap >= 0) {
                    if (countNum[i - gap] < need) {
                        tmpNum = tmpNum.replace(String.valueOf(i - gap), String.valueOf(i));
                        cost += countNum[i - gap] * gap;
                        need -= countNum[i - gap];
                    } else {
                        StringBuilder builder1 = new StringBuilder(tmpNum);
                        builder1.reverse();
                        tmpNum = builder1.toString();
                        int needCount = 0;//限定替换次数，用replaceFirst()只替换第一次出现的
                        while (needCount < need) {
                            tmpNum = tmpNum.replaceFirst(String.valueOf(i - gap), String.valueOf(i));
                            needCount++;
                        }
                        StringBuilder builder2 = new StringBuilder(tmpNum);
                        builder2.reverse();
                        tmpNum = builder2.toString();
                        cost += need * gap;
                        break;
                    }
                }
                gap += 1;

            }
            if (cost < res) {
                ans = tmpNum;
                res = cost;

            } else if (cost == res && tmpNum.compareTo(ans) < 0) {
                ans = tmpNum;
            }
        }
        result.add(String.valueOf(res));
        result.add(ans);
        return result;
    }

    /**
     * 统计字符串号码中0～9出现的次数
     * tong
     *
     * @param originalNum
     * @return
     */
    public static int[] getNumCount(String originalNum) {
        int[] countNum = new int[10];
        char[] numArray = originalNum.toCharArray();
        for (int i = 0; i < numArray.length; i++) {
            countNum[numArray[i] - '0'] = countNum[numArray[i] - '0'] + 1;

        }
        return countNum;
    }
}

