package com.wangyi;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-08-03. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = Integer.parseInt(input.nextLine());
        List<int[]> numList = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(input.nextLine());
            int[] nums = new int[N];
            for (int j = 0; j < N; j++) {
                nums[j] = input.nextInt();
            }
            numList.add(nums);
            input.nextLine();
        }
        for (int[] nums : numList) {
            System.out.println(getCircle(nums));
        }

    }

    private static String getCircle(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] < nums[i - 1] + nums[i + 1])
                count++;
        }
        if (count == nums.length - 2 && nums[0] < nums[1] + nums[nums.length - 1] && nums[nums.length - 1] < nums[nums.length - 2] + nums[0])
            return "YES";
        return "NO";
    }
}
