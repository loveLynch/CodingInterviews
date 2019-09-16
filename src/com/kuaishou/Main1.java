package com.kuaishou;

import java.util.Scanner;

/**
 * Created by lynch on 2019-09-16. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String ip = input.nextLine();
        System.out.println(getTypeOfIP(ip));
    }

    private static String getTypeOfIP(String ip) {
        if (ip.contains(".")) {
            String ipArr[] = ip.split("\\.");
            if (ipArr.length != 4) {
                return "Neither";
            } else {
                for (int i = 0; i < ipArr.length; i++) {
                    if (Integer.valueOf(ipArr[i]) > 255 || ipArr[i].length() > 3 || (ipArr[i].length() > 2 && ipArr[i].charAt(0) == '0')) {
                        return "Neither";
                    }
                }
                return "IPv4";
            }

        } else if (ip.contains(":")) {
            String ipArr[] = ip.split(":");
            if (ipArr.length != 8) {
                return "Neither";
            } else {

                for (int i = 0; i < ipArr.length; i++) {
                    if (ipArr[i].length() > 4) {
                        return "Neither";
                    }
                }
                return "IPv6";
            }
        } else {
            return "Neither";
        }
    }
}
