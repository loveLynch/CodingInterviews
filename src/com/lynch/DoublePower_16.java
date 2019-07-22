package com.lynch;

/**
 * Created by lynch on 2019/3/12. <br>
 * 16.数值的整数次方
 * 实现函数Math.pow(double base,int exponent)
 **/
public class DoublePower_16 {
    /**
     * a^n/2*a^n/2 ,  n为偶数
     * a^n ={
     * a^(n-1)/2*a^(n-1)/2,  n为奇数
     *
     * @param base
     * @param exponent
     * @return
     */
    private static double powerWithExponent(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        double result = powerWithExponent(base, exponent >> 1); //右移代替除以2的运算，效率更高
        result *= result;
        if ((exponent & 1) == 1) { //与运算代替求余运算符（%）判断一个数是奇数还是偶数
            result *= base;
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(powerWithExponent(0.8, 5));
        System.out.println(Math.pow(0.8, 5));
    }
}
