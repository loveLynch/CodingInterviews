package com.lynch;

/**
 * Created by lynch on 2019-04-03. <br>
 * 66.构建乘积数组
 * <p>
 * 给定数组A[0,1...n-1]，求B[0,1...n-1]，
 * 要求B[i] = A[0]*A[1]...A[i-1]*A[i+1]...A[n-1]，不能使用除法。
 * 思路：
 * 定义C[i]=A[0]*A[1]...A[i-1]，那么C[i]=C[i-1]*A[i-1]；
 * 定义D[i]=A[i+1]*...A[n-2]*A[n-1],那么D[i] =D[i+1]*A[i+1]；
 * 因此C[i],D[i]都可以递推地求出来，且B[i]=C[i]*D[i]。
 **/
public class ConstructArray_66 {
    /**
     * 1.计算C数组；
     * 2.依次求得D[0],D[1]...D[n-1]，同时完成B[i]=C[i]\*D[i]的计算。
     *
     * 如果最终结果存放于result[]中，第一步的C的值就可以先放到result中，
     * 而D的元素可以存放于一个变量temp中，通过result[i] = temp*result[i]求得B。

     * @param data
     * @return
     */
    public static int[] multiply(int[] data){
        if(data==null||data.length<2)
            return null;
        int[] result = new int[data.length];
        //求得数组C，存于result中
        result[0] = 1;
        for(int i=1;i<result.length;i++)
            result[i] = result[i-1]*data[i-1];
        //先求得数组D中元素，再与C中元素相乘，存于result中
        int temp = 1;
        for(int i=data.length-2;i>=0;i--){
            //数组D中的元素值
            temp = temp * data[i+1];
            //计算B[i]=C[i]*D[i]
            result[i] = result[i] * temp;
        }
        return result;
    }
    public static void main(String[] args){
        int[] data = new int[]{1,2,3,4,5};
        int[] result = multiply(data);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]);
            System.out.print("  ");
        }
    }
}
