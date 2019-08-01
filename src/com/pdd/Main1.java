package com.pdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-28. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String AStr = input.nextLine();
        String BStr = input.nextLine();
        String[] AArray = AStr.split(" ");
        String[] BArray = BStr.split(" ");

        System.out.println(getAToSort(AArray, BArray));
    }

    public static String getAToSort(String[] A, String[] B) {
        String result = "NO";

        if (A.length == 0 || A.length == 1 || B.length == 0)
            return result;
        Arrays.sort(B);

        if (A.length == 2) {
            if (Integer.valueOf(B[B.length - 1]) < Integer.valueOf(A[1]))
                return result;
            if (Integer.valueOf(A[0]) > Integer.valueOf(A[1])) {
                for (int i = B.length - 1; i >= 0; i--) {
                    if (Integer.valueOf(B[i]) > Integer.valueOf(A[1])) {
                        A[1] = B[i];
                        break;
                    }

                }
            }
        } else {
            List<String> ANoSortList = new ArrayList<>();
            int AIndex = 0;
            for (int i = 1; i < A.length - 1; i++) {
                if (A[i].compareTo(A[i - 1]) < 0) {
                    ANoSortList.add(A[i - 1]);
                    ANoSortList.add(A[i]);
                    ANoSortList.add(A[i + 1]);
                    AIndex = i;
                    break;
                }
            }
            System.out.println(AIndex);
            String maxFromB = "0";
            if (Integer.valueOf(B[B.length - 1]) < Integer.valueOf(ANoSortList.get(0)))
                return result;
            for (int i = B.length - 1; i >= 0; i--) {
                if (Integer.valueOf(B[i]) > Integer.valueOf(ANoSortList.get(0)) && Integer.valueOf(B[i]) < Integer.valueOf(ANoSortList.get(2))) {
                    maxFromB = B[i];
                    break;
                }
            }
            A[AIndex] = maxFromB;
        }

        StringBuilder resultBuilder = new StringBuilder();

        for (String singleA : A) {
            resultBuilder.append(singleA);
            resultBuilder.append(" ");
        }
        result = resultBuilder.toString().trim();


        return result;
    }
}
