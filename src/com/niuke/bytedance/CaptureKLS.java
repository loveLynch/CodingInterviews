package com.niuke.bytedance;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-02. <br>
 **/
public class CaptureKLS {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String DN = input.nextLine();
        String[] DNArray = DN.split(" ");
        Integer D = Integer.valueOf(DNArray[0]);
        Integer N = Integer.valueOf(DNArray[1]);
        String location = input.nextLine();
        String[] locationArray = location.split(" ");
        ArrayList<Integer> locationList = new ArrayList<>();
        for (int i = 0; i < D; i++)
            locationList.add(Integer.valueOf(locationArray[i]));
        System.out.println(getMethod2(locationList, N));
    }

    public static int getMethod(ArrayList<Integer> locationList, int maxLocation) {
        int countMethod = 0;
        if (locationList == null || locationList.size() < 3)
            return countMethod;
        int startIndex = 0;
        while (startIndex < locationList.size() - 2) {
            int middleIndex = startIndex + 1;

            if (locationList.get(middleIndex) - locationList.get(startIndex) > maxLocation) {
                startIndex = middleIndex;
            } else {
                for (; middleIndex < locationList.size() - 1; middleIndex++) {
                    int endIndex = middleIndex + 1;
                    if (endIndex < locationList.size() && locationList.get(middleIndex) - locationList.get(startIndex) <= maxLocation) {
                        while (endIndex < locationList.size() && locationList.get(endIndex) - locationList.get(startIndex) <= maxLocation) {
                            countMethod++;
                            System.out.println("[" + locationList.get(startIndex) + "," + locationList.get(middleIndex) + "," + locationList.get(endIndex) + "]");
                            endIndex++;
                        }
                    }

                }

            }
            startIndex++;
        }
        return countMethod%99997867;

    }

    public static int getMethod2(ArrayList<Integer> locationList, int maxLocation) {
        int countMethod = 0;
        if (locationList == null || locationList.size() < 3)
            return countMethod;
        int startIndex = 0;
        while (startIndex < locationList.size() - 2) {
            int middleIndex = startIndex + 1;
            if (locationList.get(middleIndex) - locationList.get(startIndex) > maxLocation) {
                startIndex++;
            } else {
                for (; middleIndex < locationList.size() - 1; middleIndex++) {
                    int endIndex = middleIndex + 1;
                    while (endIndex < locationList.size() && locationList.get(endIndex) - locationList.get(startIndex) <= maxLocation) {
                        countMethod++;
                        System.out.println("[" + locationList.get(startIndex) + "," + locationList.get(middleIndex) + "," + locationList.get(endIndex) + "]");
                        endIndex++;
                    }
                }

            }
            startIndex++;
        }
        return countMethod%99997867;

    }
}
