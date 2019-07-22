package com.lynch;

/**
 * Created by lynch on 2019-04-03. <br>
 * 64.求1+2+...+n
 * <p>
 * 题目要求：
 * 求1+2+...+n，要求不能使用乘除法，for，while，if，else，switch，case等关键词及条件判断语句？：
 **/
public class Accumulate_64 {
    /**
     * 替代if的一种方式：boolean b=判断条件&&(t=递归执行语句)>0
     * @param num
     * @return
     */
    private static int getSum(int num){
        int t=0;
        boolean b = (num>0)&&((t=num+getSum(num-1))>0);
        return t;
    }
    public static void main(String[] args){
        System.out.println(getSum(10));
    }
}
