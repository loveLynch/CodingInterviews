package com.bytedance;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-31. <br>
 * 一定要用long数据类型
 **/
public class CatchKLS {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String DN = input.nextLine();
        String[] DNArray = DN.split(" ");
        Integer D = Integer.valueOf(DNArray[0]);
        Integer maxDis = Integer.valueOf(DNArray[1]);
        String location = input.nextLine();
        String[] locationArray = location.split(" ");
        ArrayList<Integer> locationList = new ArrayList<>();
        for (int i = 0; i < D; i++)
            locationList.add(Integer.valueOf(locationArray[i]));
        System.out.println(getSolution(locationList, maxDis));
    }

    private static long getSolution(ArrayList<Integer> locationList, int maxDis) {
        if (locationList.size() < 3 || maxDis < 0)
            return 0;
        long count = 0;
        for (int startIndex = 0, endIndex = 0; endIndex < locationList.size(); endIndex++) {
            while (endIndex >= 2 && locationList.get(endIndex) - locationList.get(startIndex) > maxDis)
                startIndex++;
            count += computeCn(endIndex - startIndex);

        }
        return count % 99997867;
    }

    private static long computeCn(long n) {
        return n * (n - 1) / 2;
    }
}
