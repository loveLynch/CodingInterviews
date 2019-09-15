package com.huawei;

import java.util.*;

/**
 * Created by lynch on 2019-09-11. <br>
 * input
 * 3
 * CZ7132 A1 ZHANGSAN
 * CZ7132 A2 ZHAOSI
 * CZ7156 A2 WANGWU
 * 2
 * CZ7132 A1 CZ7156 A2
 * CZ7156 A2 CZ7156 A3
 * output
 * CZ7132 A2 ZHAOSI
 * CZ7156 A2 ZHANGSAN
 * CZ7156 A3 WANGWU
 **/
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        input.nextLine();
        TreeMap<String, String> peoplePlane = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj2.compareTo(obj1);
            }
        });
        for (int i = 0; i < N; i++) {
            String[] infoArray = input.nextLine().split(" ");
            String planeInfo = infoArray[0] + " " + infoArray[1];
            peoplePlane.put(infoArray[2], planeInfo);

        }
        int M = input.nextInt();
        input.nextLine();
        HashMap<String, String> planeReplace = new HashMap<>();
        for (int i = 0; i < M; i++) {
            String[] plane = input.nextLine().split(" ");
            String oldInfo = plane[0] + " " + plane[1];
            String newInfo = plane[2] + " " + plane[3];
            planeReplace.put(oldInfo, newInfo);
        }
        replacePlaneInfo(peoplePlane, planeReplace);
        for (Iterator it = peoplePlane.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            String people = (String) entry.getKey();
            String planeInfo = peoplePlane.get(people);
            System.out.println(planeInfo + " " + people);
        }

    }

    private static void replacePlaneInfo(TreeMap<String, String> peoplePlane, HashMap<String, String> planeReplace) {
        for (Map.Entry<String, String> entry : peoplePlane.entrySet()) {
            String people = entry.getKey();
            String planeInfo = peoplePlane.get(people);
            if (planeReplace.containsKey(planeInfo)) {
                peoplePlane.put(people, planeReplace.get(planeInfo));
            }
        }
    }
}
