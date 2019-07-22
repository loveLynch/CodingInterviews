package com.lynch;

/**
 * Created by lynch on 2019-04-01. <br>
 * 51.数组中的逆序对
 * <p>
 * 如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对总数。
 * 如：输入{7,5,6,4}，一共有5个逆序对，分别是（7,6），（7,5），（7,4），（6,4），（5,4）。
 * 基于归并排序
 **/
public class InversePairs_51 {
    private static int inversePair(int[] data) {
        if (data == null || data.length < 0) {
            return 0;
        }
        int[] copy = data.clone();
        int count = inversePairCore(data, copy, 0, data.length - 1);
        return count;

    }

    /**
     * 先把数组逐步分隔成长度为1的子数组，统计出子数组内部的逆序对个数，
     * 然后再将相邻两个子数组合并成一个有序数组并统计数组之间的逆序对数目，
     * 直至合并成一个大的数组。其实，这是二路归并的步骤，只不过在归并的同事要多进行一步统计。
     * 因此时间复杂度o(nlogn)，空间复杂度o(n)，如果使用原地归并排序，可以将空间复杂度降为o(1)。
     *
     * @param data
     * @param copy
     * @param start
     * @param end
     * @return
     */
    private static int inversePairCore(int[] data, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = data[start];
            return 0;
        }
        int length = (end - start) / 2;
        //统计前半数组中逆序对个数
        int left = inversePairCore(copy, data, start, start + length);
        //统计后半数组中逆序对个数
        int right = inversePairCore(copy, data, start + length + 1, end);

        //统计前后两个子数组之间的逆序对个数
        //初始化i为前半段最后一个数字的下标
        //初始化j为后半段最后一个数字的下标
        int i = start + length;
        int j = end;
        int indexCopy = end;
        int count = 0;
        while (i >= start && j >= start + length + 1) {
            //若前半段数组最后的数字大于后半段最后一个数字
            //并将前半段数组中的这个数的下标向前移动并将这个数复制到辅助数组
            //并且第一组逆序数个数为后半段数组的长度
            if (data[i] > data[j]) {
                copy[indexCopy--] = data[i--];
                count += j - start - length;
            } else {
                //否则移动后半段数组中的这个数的下标向前移动并将这个数复制到辅助数组
                copy[indexCopy--] = data[j--];
            }
        }
        //依次向前遍历前后两个数组
        for (; i >= start; i--) {
            copy[indexCopy--] = data[i];
        }
        for (; j >= start + length + 1; j--) {
            copy[indexCopy--] = data[j];
        }
        return left + right + count;

    }

    public int InversePairs2(int [] array) {
        if (array == null || array.length < 0) {
            return 0;
        }
        int count = InversePairsCore2(array, 0, array.length - 1);
        return count;
    }
    //用归并排序思想
    private int InversePairsCore2(int [] array,int low, int high){
        if(low < high){
            int mid = (low+high)/2;
            int leftCount = InversePairsCore2(array,low, mid)%1000000007;
            int rightCount = InversePairsCore2(array,mid+1,high)%1000000007;
            int count = 0;//计算数目
            int i = mid;//左边部分
            int j = high;//右边部分
            int k = high-low;//辅助数组
            int[] temp = new int[high-low+1];
            //左右两部分都是从后往前计算
            while(i>=low && j>mid){
                if(array[i] > array[j]){
                    count += j-mid;
                    temp[k--] = array[i--];
                    if(count >= 1000000007)
                        count %= 1000000007;
                }else{
                    temp[k--] = array[j--];
                }
            }
            //添加剩下的前半部分到temp中
            for(;i>=low;i--)
                temp[k--] = array[i];
            //添加剩下的后半部分到temp中
            for(;j>mid;j--)
                temp[k--] = array[j];
            //将排好序的temp复制到array中
            for(int v = 0; v < (high-low+1); v++)
                array[low+v] = temp[v];
            return (leftCount+rightCount+count)%1000000007;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] data1 = {7, 5, 6, 4};
        int [] data2 ={5,6,7,8,1,2,3,4};
        System.out.println(inversePair(data1));//5
        System.out.println(inversePair(data2));//16
    }
}
