package com.pdd.bishi91;


import java.util.*;

/**
 * Created by lynch on 2019-09-01. <br>
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int k = input.nextInt();
        System.out.println(getKthMaxNum(n, m, k));
    }

    private static int getKthMaxNum(int n, int m, int k) {
        int curNum = n * m;
        int curIndex = 1;
        int i = n;
        int j = m;
        while (i >= 0 && j >= 0 && curIndex < k) {
            List<Integer> result = calculation(i, j, k, curIndex);
            curIndex = result.get(0);
            if (result.get(4) == 0) {
                List<Integer> result1 = calculation(i - 1, j, k, curIndex);
                List<Integer> result2 = calculation(i, j - 1, k, curIndex);
                List<Integer> result3 = calculation(i - 1, j - 1, k, curIndex);
                curIndex = result1.get(1);
            }

        }

        return curNum;
    }

    public static List<Integer> calculation(int curI, int curJ, int k, int curIndex) {
        List<Integer> result = new ArrayList<>();
        int flag = 1;
        List<Integer> temp = new ArrayList<>(3);
        int left = (curI - 1) * curJ;
        int up = curI * (curJ - 1);
        int diagonal = (curI - 1) * (curJ - 1);
        temp.add(left);
        temp.add(up);
        temp.add(diagonal);
        System.out.println("three: " + left + " " + up + " " + diagonal);
        Collections.sort(temp);
        if (k - curIndex == 1) {
            curIndex++;

        } else if (k - curIndex == 2) {
            curIndex += 2;

        } else if (k - curIndex == 3) {
            curIndex += 3;
        } else {
            System.out.println("+3+");
            flag = 0;
            curIndex += 3;
        }
        result.add(curIndex);
        result.add(left);
        result.add(up);
        result.add(diagonal);
        result.add(flag);

        System.out.println("curIndex " + curIndex);

        return result;
    }

    private static int getKthMaxNum1(int n, int m, int k) {
        List<Integer> all = new ArrayList<>();
        int nums[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = (i + 1) * (j + 1);
                all.add(nums[i][j]);
            }
        }
        Collections.sort(all);
        return all.get(all.size() - k);
    }


}
