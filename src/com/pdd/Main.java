package com.pdd;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-28. <br>
 **/
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String AStr = input.nextLine();
        String BStr = input.nextLine();
        String[] AArrayStr = AStr.split(" ");
        String[] BArrayStr = BStr.split(" ");

        int[] AArray = new int[AArrayStr.length];
        int index = 0;
        for (String A : AArrayStr)
            AArray[index++] = Integer.valueOf(A);
        index = 0;
        int[] BArray = new int[BArrayStr.length];
        for (String B : BArrayStr)
            BArray[index++] = Integer.valueOf(B);
        getAToSort(AArray, BArray);

    }

    private static void getAToSort(int[] aArray, int[] bArray) {
        Arrays.sort(bArray);
        Boolean result = false;
        for (int i = bArray.length - 1; i >= 0; i--) {
            for (int j = 0; j < aArray.length; j++) {
                int temp = aArray[j];
                aArray[j] = bArray[i];
                Boolean ans = true;
                for (int k = 0; k < aArray.length - 1; k++) {
                    if (aArray[k] > aArray[k + 1]) {
                        ans = false;
                        break;
                    }
                }
                if (ans) {
                    for (int k = 0; k < aArray.length - 1; k++) {
                        System.out.print(aArray[k] + " ");
                    }
                    System.out.println(aArray[aArray.length - 1]);
                    result = true;
                    return;

                }
                aArray[j] = temp;
            }
        }
    }
}
