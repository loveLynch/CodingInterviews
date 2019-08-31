package com.leetcode;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by lynch on 2019-08-31. <br>
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class RemoveKOfNums {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String num = input.nextLine();
        int k = input.nextInt();
        System.out.println(removeKdigits(num, k));

    }

    public static String removeKdigits(String num, int k) {
        if (k >= num.length() || num.length() == 0)
            return "0";
        Stack<Integer> stack = new Stack<>();
        //栈顶始终是最大值
        stack.push(num.charAt(0) - '0');
        for (int i = 1; i < num.length(); i++) {
            int number = num.charAt(i) - '0';
            while (k > 0 && !stack.isEmpty() && stack.peek() > number) {
                stack.pop();
                k--;
            }
            stack.push(number);
        }
        while (k > 0) {
            k--;
            stack.pop();
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            if (result.toString().isEmpty() && stack.get(i) == 0)
                continue;
            result.append(stack.get(i));
        }
        return result.toString().equals("") ? "0" : result.toString();

    }
}
