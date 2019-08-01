package com.bytedance.string;

import java.util.Scanner;

/**
 * @author Lynch
 * @date 2019/8/1 17:10
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class MultipyString {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] strings = input.nextLine().split(" ");
        System.out.println(multiply(strings[0], strings[1]));
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        int[] result = new int[num1.length() + num2.length() + 2];
        StringBuilder resultBuilder = new StringBuilder();
        char[] resNum1 = new StringBuilder(num1).reverse().toString().toCharArray();
        char[] resNum2 = new StringBuilder(num2).reverse().toString().toCharArray();
        for (int i = 0; i < resNum1.length; i++) {
            for (int j = 0; j < resNum2.length; j++) {
                int temp = (resNum1[i] - '0') * (resNum2[j] - '0');
                result[i + j] = result[i + j] + temp % 10;
                if (result[i + j] > 9) {
                    result[i + j + 1]++;
                    result[i + j] = result[i + j] % 10;
                }
                result[i + j + 1] = result[i + j + 1] + temp / 10;
                if (result[i + j + 1] > 9) {
                    result[i + j + 2]++;
                    result[i + j + 1] = result[i + j + 1] % 10;
                }
            }
        }
        for (int r : result)
            resultBuilder.append(r);
        String resultStr = resultBuilder.reverse().toString();
        int index = 0;
        while (resultStr.charAt(index) == '0') {
            index++;
        }

        return resultStr.substring(index);
    }
}
