package com.bytedance.arrayandsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-04. <br>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 **/
public class SumOfThree {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] numStr = input.nextLine().split(" ");
        int[] nums = new int[numStr.length];
        for (int i = 0; i < numStr.length; i++)
            nums[i] = Integer.valueOf(numStr[i]);

        List<List<Integer>> resultList = getArrayCollections2(nums);
        System.out.println(resultList);

    }

    /**
     * 超出时间限制
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> getArrayCollections1(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        int startIndex = 0;
        Arrays.sort(nums);
        while (startIndex < nums.length - 2) {
            int middleIndex = startIndex + 1;
            for (; middleIndex < nums.length - 1; middleIndex++) {
                int endIndex = middleIndex + 1;
                while (endIndex < nums.length) {
                    if (nums[startIndex] + nums[middleIndex] + nums[endIndex] == 0) {
                        List<Integer> singleResult = new ArrayList<>();
                        singleResult.add(nums[startIndex]);
                        singleResult.add(nums[middleIndex]);
                        singleResult.add(nums[endIndex]);
                        if (!resultList.contains(singleResult))
                            resultList.add(singleResult);
                        break;
                    }
                    endIndex++;
                }
            }
            startIndex++;


        }
        return resultList;
    }

    /**
     * 改进
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> getArrayCollections2(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        for (int startIndex = 0; startIndex < nums.length; startIndex++) {
            if (startIndex == 0 || (startIndex > 0 && nums[startIndex] != nums[startIndex - 1])) {
                //升序排完第一个还是正数，则不可能三数和为0
                if (nums[startIndex] > 0) break;
                //去重
                if (startIndex > 0 && nums[startIndex] == nums[startIndex - 1])
                    continue;
                int middleIndex = startIndex + 1;
                int endIndex = nums.length - 1;
                while (middleIndex < endIndex) {
                    if (nums[startIndex] + nums[middleIndex] + nums[endIndex] == 0) {
                        List<Integer> singleResult = new ArrayList<>();
                        singleResult.add(nums[startIndex]);
                        singleResult.add(nums[middleIndex]);
                        singleResult.add(nums[endIndex]);
                        if (!resultList.contains(singleResult))
                            resultList.add(singleResult);
                        //移动下标，找下一组解
                        while (endIndex > middleIndex && nums[middleIndex] == nums[middleIndex + 1])
                            middleIndex++;
                        while (endIndex > middleIndex && nums[endIndex] == nums[endIndex - 1])
                            endIndex--;
                        middleIndex++;
                        endIndex--;
                    } else if (nums[middleIndex] + nums[endIndex] < -nums[startIndex]) middleIndex++;
                    else endIndex--;
                }
            }

        }
        return resultList;
    }
}
