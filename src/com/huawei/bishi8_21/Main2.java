package com.huawei.bishi8_21;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-21. <br>
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int low = input.nextInt();
        int high = input.nextInt();
        int min1 = 0;
        int min10 = 0;
        for (int i = low; i < high; i++) {
            if (isPrime(i)) {
                int temp = i % 10;
                min1 += temp;
                min10 += (i % 100 - temp) / 10;

            }
        }
        System.out.println(Math.min(min1, min10));


    }

    public static boolean isRightNum(int num) {
        if (num == 2 || num == 3)
            return true;
        if (num % 6 != 1 && num % 6 != 5)
            return false;
        int temp = (int) Math.sqrt(num);
        for (int i = 5; i <= temp; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        }
        return true;
    }

    public static boolean isPrime(int a) {

        boolean flag = true;

        if (a < 2) {// 素数不小于2
            return false;
        } else {

            for (int i = 2; i <= Math.sqrt(a); i++) {

                if (a % i == 0) {// 若能被整除，则说明不是素数，返回false

                    flag = false;
                    break;// 跳出循环
                }
            }
        }
        return flag;
    }

}
