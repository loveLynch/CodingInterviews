package com.lynch;

/**
 * Created by lynch on 2019/3/6. <br>
 * 12.矩阵中的路径（回溯法）
 * 设计一个函数，用来判断一个矩阵中是否存在一条包含某字符串的路径。
 * （1）起点随意；
 * （2）路径的移动只能是上下左右；
 * （3）访问过的位置不能再访问。以下图矩阵为例，包含“bfce”，但是不包含“abfb”。
 * a      b      t      g
 * c      f      c      s
 * j      d      e      h
 **/
public class MatrixPath_12 {

    /**
     * 判断是否有该路径
     *
     * @param data
     * @param str
     * @return
     */
    public static boolean hasPath(char[][] data, String str) {
        if (data == null || data.length == 0 || str == null || str.length() == 0)
            return false;
        int rows = data.length;
        int cols = data[0].length;
        boolean[][] visitFlag = new boolean[rows][cols];
        //开始默认都未被访问,设置为false
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                visitFlag[row][col] = false;
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathCore(data, row, col, visitFlag, str, 0))
                    return true;
            }

        }
        return false;
    }

    /**
     * 回溯法路径判断
     *
     * @param data
     * @param rowIndex
     * @param colIndex
     * @param visitFlag
     * @param str
     * @param strIndex
     * @return
     */
    public static boolean hasPathCore(char[][] data, int rowIndex, int colIndex,
                                      boolean[][] visitFlag, String str, int strIndex) {
        //结束条件
        if (strIndex >= str.length())
            return true;
        //边界与合理性判断
        if (rowIndex < 0 || colIndex < 0 || rowIndex >= data.length || colIndex >= data[0].length)
            return false;
        //递归
        if (!visitFlag[rowIndex][colIndex] && data[rowIndex][colIndex] == str.charAt(strIndex)) {
            //如果未被访问，且匹配字符串，标记当前位置为已访问，分上下左右四个位置递归求解
            visitFlag[rowIndex][colIndex] = true;
            boolean result =
                    hasPathCore(data, rowIndex + 1, colIndex, visitFlag, str, strIndex + 1) ||
                            hasPathCore(data, rowIndex - 1, colIndex, visitFlag, str, strIndex + 1) ||
                            hasPathCore(data, rowIndex, colIndex + 1, visitFlag, str, strIndex + 1) ||
                            hasPathCore(data, rowIndex, colIndex - 1, visitFlag, str, strIndex + 1);
            //已经求的结果，无需修改标记了
            if (result) {
                return true;
            }
            //当前递归的路线求解失败，要把这条线路上的标记清除掉
            //因为其他起点的路径依旧可以访问本路径上的节点。
            else {
                visitFlag[rowIndex][colIndex] = false;
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        //标志位，初始化为false
        boolean[] flag = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //循环遍历二维数组，找到起点等于str第一个元素的值，再递归判断四周是否有符合条件的----回溯法
                if (judge(matrix, i, j, rows, cols, flag, str, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //judge(初始矩阵，索引行坐标i，索引纵坐标j，矩阵行数，矩阵列数，待判断的字符串，字符串索引初始为0即先判断字符串的第一位)
    private static boolean judge(char[] matrix, int i, int j, int rows, int cols, boolean[] flag, char[] str, int k) {
        //先根据i和j计算匹配的第一个元素转为一维数组的位置
        int index = i * cols + j;
        //递归终止条件
        if (i < 0 || j < 0 || i >= rows || j >= cols || matrix[index] != str[k] || flag[index] == true)
            return false;
        //若k已经到达str末尾了，说明之前的都已经匹配成功了，直接返回true即可
        if (k == str.length - 1)
            return true;
        //要走的第一个位置置为true，表示已经走过了
        flag[index] = true;

        //回溯，递归寻找，每次找到了就给k加一，找不到，还原
        if (judge(matrix, i - 1, j, rows, cols, flag, str, k + 1) ||
                judge(matrix, i + 1, j, rows, cols, flag, str, k + 1) ||
                judge(matrix, i, j - 1, rows, cols, flag, str, k + 1) ||
                judge(matrix, i, j + 1, rows, cols, flag, str, k + 1)) {
            return true;
        }
        //走到这，说明这一条路不通，还原，再试其他的路径
        flag[index] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] data = {
                {'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'}};
        char[] matrix = new char[data.length * data[0].length];
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                matrix[row*data[0].length+col] = data[row][col];
            }
        }

        System.out.println(hasPath(data, "bfce")); //true
        System.out.println(hasPath(data, "abfb")); //false,访问过的位置不能再访问
        System.out.println(hasPath(matrix,data.length,data[0].length,"bfce".toCharArray()));
        System.out.println(hasPath(matrix,data.length,data[0].length,"abfb".toCharArray()));
    }
}
