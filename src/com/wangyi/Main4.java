package com.wangyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-03. <br>
 **/
public class Main4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String NQ = input.nextLine();
        int N = Integer.parseInt(NQ.split(" ")[0]);
        int Q = Integer.parseInt(NQ.split(" ")[1]);
        int[] nums = new int[N];
        String[] numStr = input.nextLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(numStr[i]);
        }
        List<Integer> questionList = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            questionList.add(input.nextInt());
        }
        List<Integer> resultList = new ArrayList<>();
        getCount(nums, questionList, 0, resultList);
        for (Integer result : resultList) {
            System.out.println(result);

        }

    }

    //牛客网有说加try catch
    private static void getCount(int[] nums, List<Integer> questionList, int index, List<Integer> resultList) {
        if (index == questionList.size())
            return;
        Arrays.sort(nums);
        int count = 0;
        //换成二分能加速，但注意两边都有的情况
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] >= questionList.get(index)) {
                count++;
                nums[i]--;
            } else {
                break;
            }
        }
        resultList.add(count);
        getCount(nums, questionList, index + 1, resultList);

    }
}
