package com.lynch.sort;

/**
 * Created by lynch on 2019-03-27. <br>
 *     堆排序
 **/
public class HeapSort {
    //数组堆排序，时间o(nlogn)，空间o(1),不稳定
    //建立最大堆，交换堆的第一个与最后一个元素，调整堆
    //注意，堆排序的0号元素不能使用，为了与其他排序统一接口，先把最小的元素放到0号元素上，再用堆排序
    public static void heapSort(int[] data){
        if(data==null || data.length<=1)
            return;
        //先把最小的元素放到0号元素上
        int minIndex = 0;
        for(int i=1;i<data.length;i++){
            if(data[i]<data[minIndex])
                minIndex = i;
        }
        if(minIndex!=0){
            int temp = data[0];
            data[0] = data[minIndex];
            data[minIndex] = temp;
        }
        //正式开始堆排序（如果0号元素未存值，省略上述代码）
        buildMaxHeap(data);
        for(int indexBound = data.length-1;indexBound>1;){
            int temp = data[indexBound];
            data[indexBound] = data[1];
            data[1] = temp;
            indexBound--;
            adjustMaxHeap(data,1,indexBound);
        }
    }
    public static void buildMaxHeap(int[] data){
        for(int i = data.length/2;i>0;i--){
            adjustMaxHeap(data,i,data.length-1);
        }
    }
    //i表示待调整元素下标，end表示最大堆的最后一个元素的下标，end值会随着排序的进行而减小到1
    public static void adjustMaxHeap(int[] data,int i,int end){
        int left = 2*i;
        int right = 2*i+1;
        int max = i;
        if(left<=end && data[left]>data[max])
            max = left;
        if(right<=end && data[right]>data[max])
            max = right;
        if(max!=i){
            int temp = data[max];
            data[max] = data[i];
            data[i] = temp;
            adjustMaxHeap(data,max,end);
        }
    }

    public static void testHeapSort(){
        int[] data = {5,4,3,1,2};
        heapSort(data);
        System.out.print("  数组堆排序：\t");
        for(int item: data){
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }
}
