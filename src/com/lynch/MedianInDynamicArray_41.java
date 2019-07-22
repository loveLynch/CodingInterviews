package com.lynch;

import java.util.*;


/**
 * Created by lynch on 2019-03-28. <br>
 * 41.数据流中的中位数
 * 数据流：数字不是一次性给出
 * <p>
 * 下面给出三种思路，分别借助于链表、堆、二叉搜索树完成。
 * 思路1：使用未排序的链表存储数据，使用快排的分区函数求中位数；也可以在插入时进行排序，这样中位数的获取会很容易，但插入费时。
 * 思路2：使用堆存储，两个堆能够完成最大堆-最小堆这样的简单分区，两个堆的数字个数相同或最大堆数字个数比最小堆数字个数大1，
 * 中位数为两个堆堆顶的均值或者最大堆的堆顶。
 * 思路3：使用二叉搜索树存储，每个树节点添加一个表征子树节点数目的字段用于找到中位数。
 **/
public class MedianInDynamicArray_41 {
    /**
     * 模拟读取数据流的过程
     */
    private static interface MedianFinder {
        void addNum(double num);

        double findMedian();
    }

    /**
     * 思路一：
     * 使用未排序的链表存储数据，使用快排的分区函数求中位数；
     * 也可以在插入时进行排序，这样中位数的获取会很容易，但插入费时。
     */
    public static class MedianFinder1 implements MedianFinder {
        List<Double> list = null;

        public MedianFinder1() {
            list = new LinkedList<>();
        }

        /**
         * 动态添加元素
         *
         * @param num
         */
        public void addNum(double num) {
            list.add(num);
        }

        /**
         * 找出中位数
         *
         * @return
         */
        public double findMedian() {
            if (list.size() == 0)
                return 0;
            //如果长度为奇数，求中间的那个数;如果为偶数，求中间两个数的均值
            if ((list.size() & 1) == 1)
                return findKth(list.size() >>> 1);
            else
                return (findKth(list.size() >>> 1) + findKth((list.size() >>> 1) - 1)) / 2;

        }

        /**
         * 得到从小到大排序后下标为k的数据值
         *
         * @param k
         * @return
         */
        public double findKth(int k) {
            int start = 0, end = list.size() - 1;
            int index = partition(start, end);
            while (index != k) {
                if (index < k)
                    index = partition(index + 1, end);
                else {
                    index = partition(start, index - 1);
                }
            }
            return list.get(index);
        }

        /**
         * 分区，[小，pivot，大]
         *
         * @param start
         * @param end
         * @return
         */
        public int partition(int start, int end) {
            if (start >= end)
                return end;
            double pivot = list.get(start);
            //[start,bound)小于等于pivot,bound值为pivot，(bound，end]大于pivot
            int bound = start;
            for (int i = start + 1; i <= end; i++) {
                if (list.get(i) <= pivot) {
                    list.set(bound, list.get(i));
                    bound++;
                    list.set(i, list.get(bound));
                }
            }
            list.set(bound, pivot);
            return bound;
        }

    }

    /**
     * 思路二：
     * 两个堆能够完成最大堆-最小堆这样的简单分区，两个堆的数字个数相同或heapMax比heapMin大1
     * 中位数为最大堆的堆顶或者两个堆堆顶的均值
     */

    public static class MedianFinder2 implements MedianFinder {
        List<Double> maxHeap = null;
        List<Double> minHeap = null;

        public MedianFinder2() {
            maxHeap = new ArrayList<>();
            minHeap = new ArrayList<>();
            maxHeap.add(0.0);
            minHeap.add(0.0);
        }

        @Override
        public void addNum(double num) {
            if (maxHeap.size() == minHeap.size()) {
                if (minHeap.size() <= 1 || num <= minHeap.get(1))
                    addItemToMaxHeap(num);
                else {
                    addItemToMaxHeap(minHeap.get(1));
                    minHeap.set(1, num);
                    adjustMinHeap(1);
                }
            } else {
                if (num >= maxHeap.get(1))
                    addItemToMinHeap(num);
                else {
                    addItemToMinHeap(maxHeap.get(1));
                    maxHeap.set(1, num);
                    adjustMaxHeap(1);
                }

            }
        }

        private void addItemToMaxHeap(double value) {
            maxHeap.add(value);
            int curIndex = maxHeap.size() - 1;
            while (curIndex > 1 && maxHeap.get(curIndex) > maxHeap.get(curIndex >>> 1)) {
                double temp = maxHeap.get(curIndex);
                maxHeap.set(curIndex, maxHeap.get(curIndex >>> 1));
                maxHeap.set(curIndex >>> 1, temp);
                curIndex = curIndex >>> 1;
            }
        }

        private void adjustMaxHeap(int index) {
            int left = index * 2, right = left + 1;
            int max = index;
            if (left < maxHeap.size() && maxHeap.get(left) > maxHeap.get(max))
                max = left;
            if (right < maxHeap.size() && maxHeap.get(right) > maxHeap.get(max))
                max = right;
            if (max != index) {
                double temp = maxHeap.get(index);
                maxHeap.set(index, maxHeap.get(max));
                maxHeap.set(max, temp);
                adjustMaxHeap(max);
            }
        }

        private void addItemToMinHeap(double value) {
            minHeap.add(value);
            int curIndex = maxHeap.size() - 1;
            while (curIndex > 1 && minHeap.get(curIndex) < minHeap.get(curIndex >>> 1)) {
                double temp = minHeap.get(curIndex);
                minHeap.set(curIndex, minHeap.get(curIndex >>> 1));
                minHeap.set(curIndex >>> 1, temp);
                curIndex = curIndex >>> 1;
            }
        }

        private void adjustMinHeap(int index) {
            int left = index * 2, right = left + 1;
            int min = index;
            if (left < minHeap.size() && minHeap.get(left) < minHeap.get(min))
                min = left;
            if (right < minHeap.size() && minHeap.get(right) < minHeap.get(min))
                min = right;
            if (min != index) {
                double temp = minHeap.get(index);
                minHeap.set(index, minHeap.get(min));
                minHeap.set(min, temp);
                adjustMinHeap(min);
            }
        }

        @Override
        public double findMedian() {
            if (maxHeap.size() > minHeap.size())
                return maxHeap.get(1);
            else
                return (maxHeap.get(1) + minHeap.get(1)) / 2;
        }
    }


    /**
     * 思路三：
     * 使用二叉搜索树存储，每个树节点添加一个表征子树节点数目的字段用于计算中位数。
     */
    public static class MedianFinder3 implements MedianFinder {
        //左孩子小于根节点，右孩子大于等于根节点
        TreeNodeWithNums<Double> root = null;

        public MedianFinder3() {
        }

        @Override
        public void addNum(double num) {
            if (root == null)
                root = new TreeNodeWithNums<>(num);
            else
                addNode(root, num);
        }

        public void addNode(TreeNodeWithNums<Double> node, double num) {
            if (num < node.val) {
                if (node.left == null) {
                    node.left = new TreeNodeWithNums<>(num);
                    node.nodes++;
                } else
                    addNode(node.left, num);
            } else {
                if (node.right == null) {
                    node.right = new TreeNodeWithNums<>(num);
                    node.nodes++;
                } else
                    addNode(node.right, num);
            }
        }

        @Override
        public double findMedian() {
            if (root == null)
                return 0;
            if ((root.nodes & 1) == 1)
                return findMedian(root, root.nodes / 2 + 1);
            else
                return (findMedian(root, root.nodes / 2) + findMedian(root, root.nodes / 2 + 1)) / 2;
        }

        private double findMedian(TreeNodeWithNums<Double> node, int index) {
            if (node.left != null) {
                if (index == node.left.nodes + 1)
                    return node.val;
                else if (index <= node.left.nodes)
                    return findMedian(node.left, index);
                else
                    return findMedian(node.right, index - node.left.nodes - 1);
            } else if (node.right != null) {
                return findMedian(node.right, index - 1);
            } else
                return node.val;
        }
    }

    public static class TreeNodeWithNums<T> {
        public T val;
        public int nodes;
        public TreeNodeWithNums<T> left;
        public TreeNodeWithNums<T> right;

        public TreeNodeWithNums(T val) {
            this.val = val;
            this.nodes = 1;
            this.left = null;
            this.right = null;
        }
    }

    public static class FindMedian {
        //Java的PriorityQueue 是从JDK1.5开始提供的新的数据结构接口，
        // 默认内部是自然排序，结果为小顶堆，也可以自定义排序器，比如下面反转比较，完成大顶堆。

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(20, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //PriorityQueue默认是小顶堆，实现大顶堆，需要反转默认排序器
                return o2.compareTo(o1);
            }
        });

        public void Insert(Integer num) {
            if (((maxHeap.size() + minHeap.size()) & 1) == 0) { // 判断偶数的高效写法
                if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                    maxHeap.offer(num);
                    num = maxHeap.poll();
                }
                minHeap.offer(num);
            } else {
                if (!minHeap.isEmpty() && num > minHeap.peek()) {
                    minHeap.offer(num);
                    num = minHeap.poll();
                }
                maxHeap.offer(num);
            }
        }

        public Double GetMedian() {
            double result;
            if (((maxHeap.size() + minHeap.size()) & 1) == 1)
                //总数为奇数时，大顶堆堆顶就是中位数
                result = maxHeap.peek();
            else
                result = (minHeap.peek() + maxHeap.peek()) / 2.0;
            return result;
        }
    }
    public static class GetMedian {
        int count;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //PriorityQueue默认是小顶堆，实现大顶堆，需要反转默认排序器
                return o2.compareTo(o1);
            }
        });

        public void Insert(Integer num) {
            count++;
            if ((count & 1) == 0) { // 判断偶数的高效写法
                if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                    maxHeap.offer(num);
                    num = maxHeap.poll();
                }
                minHeap.offer(num);
            } else {
                if (!minHeap.isEmpty() && num > minHeap.peek()) {
                    minHeap.offer(num);
                    num = minHeap.poll();
                }
                maxHeap.offer(num);
            }
        }

        public Double GetMedian() {
            if (count == 0)
                throw new RuntimeException("no available number!");
            double result;
            //总数为奇数时，大顶堆堆顶就是中位数
            if ((count & 1) == 1)
                result = maxHeap.peek();
            else
                result = (minHeap.peek() + maxHeap.peek()) / 2.0;
            return result;
        }
    }
    public static void main(String[] args) {
//        MedianFinder1 medianFinder1 = new MedianFinder1();
//        medianFinder1.addNum(1.0);
//        medianFinder1.addNum(2.0);
//        System.out.println(medianFinder1.findMedian());
//        medianFinder1.addNum(3.0);
//        System.out.println(medianFinder1.findMedian());

//        MedianFinder2 medianFinder2 = new MedianFinder2();
//        medianFinder2.addNum(1.0);
//        medianFinder2.addNum(4.0);
//        medianFinder2.addNum(5.0);
//        System.out.println(medianFinder2.findMedian());
//        medianFinder2.addNum(2.0);
//        medianFinder2.addNum(2.0);
//        System.out.println(medianFinder2.findMedian());
//        medianFinder2.addNum(6.0);
//        medianFinder2.addNum(8.0);
//        medianFinder2.addNum(5.0);
//        System.out.println(medianFinder2.findMedian());


//        MedianFinder3 medianFinder3 = new MedianFinder3();
//        System.out.println(medianFinder3.findMedian());
//        medianFinder3.addNum(6.0);
//        medianFinder3.addNum(8.0);
//        medianFinder3.addNum(5.0);
//        System.out.println(medianFinder3.findMedian());

//        FindMedian findMedian = new FindMedian();
//        findMedian.Insert(1);
//        findMedian.Insert(2);
//        System.out.println(findMedian.GetMedian());
//        findMedian.Insert(5);
//        findMedian.Insert(3);
//        findMedian.Insert(5);
//        System.out.println(findMedian.GetMedian());
//        findMedian.Insert(4);
//        findMedian.Insert(2);
//        findMedian.Insert(8);
//        System.out.println(findMedian.GetMedian());


        GetMedian getMedian = new GetMedian();
        getMedian.Insert(1);
        getMedian.Insert(2);
        System.out.println(getMedian.GetMedian());
        getMedian.Insert(5);
        getMedian.Insert(3);
        getMedian.Insert(5);
        System.out.println(getMedian.GetMedian());
        getMedian.Insert(4);
        getMedian.Insert(2);
        getMedian.Insert(8);
        System.out.println(getMedian.GetMedian());



    }


}
