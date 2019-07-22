package com.lynch;

/**
 * Created by lynch on 2019-04-03. <br>
 * 61.扑克牌中的顺子
 * <p>
 * 抽取5张牌，判断是不是一个顺子。2-10为数字本身，A为1，J为11，Q为12，K为13，大小王可堪称任意数字。
 * 思路：
 * 将1-10,J,Q,K记作1-13，大小王记作0，0可以转化为任意的1-13的数字，
 * 判断输入的5个数字是否能组成连续的5个数字即可
 **/
public class ContinousCards_61 {
    /**
     * @param data
     * @return
     */
    private static boolean isContinous(int[] data) {
        if (data == null || data.length != 5)
            return false;
        int[] table = new int[14];//默认数组中均为0
        for (int i = 0; i < data.length; i++) {
            if (data[i] > 13 || data[i] < 0)
                return false;
            //将data中的数字作为table下标，并将其值加1，
            // 若有多个king，则table[0]将大于1，若有重复数table[data[i]]大于1
            table[data[i]]++;
        }
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + "\t");
        }
        int start = 1;
        //计算table[i]中1的起点下标
        while (table[start] == 0)
            start++;
        int king = table[0];//得到king的个数
        //判断连续table[i]中是否有连续5个1
        for (int i = start; i < start + 5; i++) {
            if (i > 13)
                break;
            //存在重复数
            if (table[i] > 1 || table[i] < 0)
                return false;
            else if (table[i] == 0) {
                if (king == 0)
                    return false;
                else {
                    king--;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{4, 2, 7, 12, 1}; //false
        int[] data2 = new int[]{0, 5, 6, 12, 0}; //false
        int[] data3 = new int[]{6, 5, 8, 7, 4};  //true
        int[] data4 = new int[]{0, 5, 6, 9, 8};  //true
        int[] data5 = new int[]{0, 13, 0, 12, 0}; //true
//        System.out.println(isContinous(data1));
//        System.out.println(isContinous(data2));
        System.out.println(isContinous(data3));
        System.out.println(isContinous(data4));
//        System.out.println(isContinous(data5));
    }
}
