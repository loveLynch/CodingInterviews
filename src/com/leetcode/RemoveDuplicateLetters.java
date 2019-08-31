package com.leetcode;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by lynch on 2019-08-31. <br>
 * 给定一个仅包含小写字母的字符串，去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "bcabc"
 * 输出: "abc"
 * 示例 2:
 * <p>
 * 输入: "cbacdcbc"
 * 输出: "acdb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        System.out.println(removeDuplicateLetters(s));
    }

    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (stack.contains(c))
                continue;
            //有小字符的且满足其前面的字符在小字符后还有同样字符的，则出栈
            while (!stack.isEmpty() && stack.peek() > c && s.indexOf(stack.peek(), i) != -1)
                stack.pop();
            stack.push(c);
        }
        int size = stack.size();
        char letters[] = new char[size];
        while (!stack.isEmpty()) {
            letters[--size] = stack.pop();
        }
        return String.valueOf(letters);

    }
}
