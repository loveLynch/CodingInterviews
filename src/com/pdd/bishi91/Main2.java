package com.pdd.bishi91;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-09-01. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = Integer.parseInt(input.nextLine());
        List<String> xiaoMeiList = new ArrayList<>();
        List<String> xiaoBaiList = new ArrayList<>();
        for (int i = 0; i < 2 * N; i++) {
            String xiaoMei = input.nextLine();
            xiaoBaiList.add(xiaoMei);
            String xiaoBai = input.nextLine();
            xiaoBaiList.add(xiaoBai);

        }
        System.out.println(getSoulution(xiaoMeiList, xiaoBaiList));

    }

    private static String getSoulution(List<String> xiaoMei, List<String> xiaoBai) {
        return null;
    }
}
