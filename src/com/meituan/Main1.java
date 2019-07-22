package com.meituan;


import java.util.*;

/**
 * Created by lynch on 2019-04-23. <br>
 * 题目描述：
 * 我们称一个矩阵为黑白矩阵，当且仅当对于该矩阵中每一个位置如(i,j),其上下左右四个方向的数字相等，
 * 即(i-1,j),(i+1,j),(i,j+1),(i,j-1)四个位置上的数字两两相等且均不等于(i,j)位置上的数字。(超出边界的格子忽略)
 * <p>
 * 现在给出一个n*m的矩阵，我们想通过修改其中的某些数字，使得该矩阵成为黑白矩阵，问最少修改多少个数字。
 * <p>
 * 输入
 * 第一行包含两个整数n,m,表示矩阵的长宽。(1≤n,m≤100000,1≤n*m≤100000)。
 * <p>
 * 接下来有n行，每行包含m个整数，中间用空格隔开，表示n*m的矩阵。
 * <p>
 * 输出
 * 输出仅包含一个数字，表示该矩阵想修改为黑白矩阵最少修改的数字数量。
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        String[] lineArray = nextLine.split(" ");
        int n = Integer.parseInt(lineArray[0]);
        int m = Integer.parseInt(lineArray[1]);
        int data[][] = new int[n][m];
        int i = 0;
        while (nextLine != null && !nextLine.equals("")) {
            nextLine = scanner.nextLine();
            lineArray = nextLine.split(" ");
            for (int j = 0; j < lineArray.length; j++) {
                data[i][j] = Integer.parseInt(lineArray[j]);
            }
            i++;
            if (i == n)
                break;
        }

        System.out.println(splitBlackAndWhite(data));
    }


    /**
     * 假设data[0][0]为黑棋
     * 最终需如下图所示
     * bab
     * aba
     * bab
     * 先将黑白两种棋子放入两个不同数组，此时不管两个的具体数值，只是针对其位置
     * 然后求黑白纵数
     *
     * @param data
     */
    private static int splitBlackAndWhite(int data[][]) {
        int minChange = 0;
        if (data.length == 0 || data == null )
            return minChange;
        ArrayList<Integer> blackList = new ArrayList<>();
        ArrayList<Integer> whiteList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (((i + j) & 1) == 0)
                    blackList.add(data[i][j]);
                else {
                    whiteList.add(data[i][j]);
                }

            }
        }
        //分别求出黑子和白子中的众数和次数
        ArrayList<Integer> blackMaxCountList = candidate(blackList);
        int blackMax = blackMaxCountList.get(0);
        int blackMaxCount = blackMaxCountList.get(1);

        ArrayList<Integer> whiteMaxCountList = candidate(whiteList);
        int whiteMax = whiteMaxCountList.get(0);
        int whiteMaxCount = whiteMaxCountList.get(1);

        //各棋子中与纵数不同的数量，即判断是否完全相等
        int blackToChange = blackList.size() - blackMaxCount;
        int whiteToChange = whiteList.size() - whiteMaxCount;


        //判断黑子和白子是否相等，
        if (blackMax == whiteMax) {
            //1.黑白等:黑子和白子完全相等
            if (blackToChange == 0 && whiteToChange == 0) {
                minChange = Math.min(blackMaxCount, whiteMaxCount);
            } else {
                //找出黑子和白子第二或和第一个次数相等的数字及其次数
                //分别求出黑子和白子中的纵数和次数
                //先复制原来的黑子与白子
                ArrayList<Integer> blackCopyList = (ArrayList<Integer>) blackList.clone();
                ArrayList<Integer> whiteCopyList = (ArrayList<Integer>) blackList.clone();
                //先剔除黑子和白子中出现次数最多的数字
                ArrayList<Integer> blackRemoveMaxList = listRemoveValue(blackCopyList, blackMax);
                ArrayList<Integer> whiteRemoveMaxList = listRemoveValue(whiteCopyList, whiteMax);
                int blackSecondCount = 0;
                if (blackRemoveMaxList.size() != 0) {
                    ArrayList<Integer> blackSecondCountList = candidate(blackRemoveMaxList);
                    blackSecondCount = blackSecondCountList.get(1);
                }

                int whiteSecondCount = 0;
                if (whiteRemoveMaxList.size() != 0) {
                    ArrayList<Integer> whiteSecondCountList = candidate(whiteRemoveMaxList);
                    whiteSecondCount = whiteSecondCountList.get(1);
                }
                //2.黑等白不等:黑子相等，白子有不等,将第二多的白子作白子
                if (blackToChange == 0 && whiteToChange != 0) {
                    minChange = whiteList.size() - whiteSecondCount;
                    //3.黑不等白等:白子相等，黑子有不等，将第二多的黑子作黑子,
                } else if (blackToChange != 0 && whiteToChange == 0) {
                    minChange = blackList.size() - blackSecondCount;
                    //4.黑白不等:均有不等
                } else {
                    minChange = Math.min(blackToChange + (whiteList.size() - whiteSecondCount), whiteToChange + (blackList.size() - blackSecondCount));
                }
            }


        } else {
            //不相等，判断白子和黑子最少需变化的棋子
            minChange = blackToChange + whiteToChange;
        }

        return minChange;

    }

    /**
     * 找出数组中某个出现次数最多的数及其次数
     * 列表中第一个为数list.get(0)
     * 第二个为出现的次数list.get(1)
     *
     * @param list
     * @return
     */
    public static ArrayList<Integer> candidate(ArrayList<Integer> list) {
        if (list == null)
            return null;
        // map的key存放数组中的数字，value存放该数字出现的次数
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> maxAndSecond = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (map.containsKey(list.get(i))) {
                int formerValue = map.get(list.get(i));
                map.put(list.get(i), formerValue + 1);    // 该数字出现的次数加1
            } else {
                map.put(list.get(i), 1);    // 该数字第一次出现
            }
        }
        Collection<Integer> count = map.values();
        // 找出map的value中最大值，也就是数组中出现最多的数字所出现的次数
        int maxCount = Collections.max(count);
        int maxNumber = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //得到value为maxCount的key，也就是数组中出现次数最多的数字
            if (entry.getValue() == maxCount) {
                maxNumber = entry.getKey();
            }
        }
        maxAndSecond.add(maxNumber);
        maxAndSecond.add(maxCount);
        return maxAndSecond;
    }

    /**
     * 删除list中所有value
     *
     * @param list
     * @param value
     * @return
     */
    public static ArrayList<Integer> listRemoveValue(ArrayList<Integer> list, int value) {
        for (int i = 0, len = list.size(); i < len; i++) {
            if (list.get(i) == value) {
                list.remove(i);
                len--;
                i--;
            }
        }
        return list;
    }
//    ArrayList<Integer> list = new ArrayList<>();
//    HashMap<Integer,Integer> numCount = new HashMap<>();
//        for(int i=0;i<array.length;i++){
//        if(numCount.get(array[i])==null){
//            numCount.put(array[i],1);
//        }else{
//            numCount.put(array[i],2);
//        }
//    }
//
//           for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
//        //得到value为maxCount的key，也就是数组中出现次数最多的数字
//        if (entry.getValue() == 1) {
//            list.add(entry.getKey());
//        }
//    }
//    num1[0]=list.get(0);
//    num2[0]=list.get(1);
}
