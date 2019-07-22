package com.lynch;

/**
 * Created by lynch on 2019-04-03. <br>
 * 65.不用加减乘除做加法
 * <p>
 * 写一个函数，求两个正数之和，要求在函数体内不能使用四则运算符号。
 **/
public class AddTwoNumbers_65 {
    /**
     * 位运算
     * 1.两数进行异或：  0011^0101=0110 这个数字其实是把原数中不需进位的二进制位进行了组合
     * 2.两数进行与：    0011&0101=0001 这个数字为1的位置表示需要进位，而进位动作是需要向前一位进位
     * 3.左移一位：      0001<<1=0010
     *
     * 此时我们就完成0011 + 0101 = 0110 + 0010的转换
     *
     * 如此转换下去，直到其中一个数字为0时，另一个数字就是原来的两个数字的和
     * @param a
     * @param b
     * @return
     */
    private static int add(int a,int b){
        int sum = a^b;
        int carry = (a&b)<<1;
        int temp;
        while (carry!=0){
            temp = sum;
            sum = sum^carry;
            carry = (carry&temp)<<1;
        }
        return sum;
    }
    public static void main(String[] args){
        System.out.println(add(3,5)); //8
        System.out.println(add(3,-5)); //-2
        System.out.println(add(0,1));  //1
    }
}
