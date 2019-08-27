package com.bytedance.bishi825;

import java.util.Scanner;

/**
 * Created by lynch on 2019-08-25. <br>
 **/
public class Play2048 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); //方向，1上，2下，3左，4右
        int[][] nums = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nums[i][j] = input.nextInt();
            }
        }
        int[][] result = getResult(n, nums);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(result[i][j] + " ");
            System.out.println();
        }
    }

    private static int[][] getResult(int n, int[][] nums) {
        switch (n) {
            case 1:
                moveUp(nums);
                break;
            case 2:
                moveDown(nums);
                break;
            case 3:
                moveLeft(nums);
                break;
            case 4:
                moveRight(nums);
                break;

            default:
                break;

        }
        return nums;
    }

    public static void moveUp(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for (int j = 0; j < m; j++) {
            //上移
            for (int i = 0; i < n; i++) {
                if (arr[i][j] == 0) {
                    for (int k = i + 1; k < n; k++) {
                        if (arr[k][j] != 0) {
                            arr[i][j] = arr[k][j];
                            arr[k][j] = 0;
                            break;
                        }
                    }
                }
            }
            //归并
            for (int i = 0; i < n - 1; i++) {
                if (arr[i][j] == arr[i + 1][j]) {
                    arr[i][j] *= 2;
                    arr[i + 1][j] = 0;
                }


            }
            //交换
            for (int i = 0; i < n - 1; i++) {
                if (i >= 1 && arr[i - 1][j] == 0 && arr[i][j] != 0) {
                    int temp = arr[i - 1][j];
                    arr[i - 1][j] = arr[i][j];
                    arr[i][j] = temp;

                }
            }
        }
    }

    public static void moveDown(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for (int j = 0; j < m; j++) {
            //下移
            for (int i = n - 1; i >= 0; i--) {
                if (arr[i][j] == 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (arr[k][j] != 0) {
                            arr[i][j] = arr[k][j];
                            arr[k][j] = 0;
                            break;
                        }
                    }
                }
            }
            //归并
            for (int i = n - 1; i > 0; i--) {
                if (arr[i][j] == arr[i - 1][j]) {
                    arr[i][j] *= 2;
                    arr[i - 1][j] = 0;
                }

            }
            //交换
            for (int i = n - 1; i > 0; i--) {
                if (arr[i - 1][j] != 0 && arr[i][j] == 0) {
                    int temp = arr[i - 1][j];
                    arr[i - 1][j] = arr[i][j];
                    arr[i][j] = temp;

                }
            }

        }
    }

    public static void moveLeft(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            //左移
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    for (int k = j + 1; k < m; k++) {
                        if (arr[i][k] != 0) {
                            arr[i][j] = arr[i][k];
                            arr[i][k] = 0;
                            break;
                        }
                    }
                }
            }
            //归并
            for (int j = 0; j < m - 1; j++) {
                if (arr[i][j] == arr[i][j + 1]) {
                    arr[i][j] *= 2;
                    arr[i][j + 1] = 0;
                }
            }
            //交换
            for (int j = 0; j < m; j++) {
                if (j >= 1 && arr[i][j] != 0 && arr[i][j - 1] == 0) {
                    int temp = arr[i][j];
                    arr[i][j] = arr[i][j - 1];
                    arr[i][j - 1] = temp;

                }
            }
        }
    }

    public static void moveRight(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            //右移
            for (int j = m - 1; j >= 0; j--) {
                if (arr[i][j] == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (arr[i][k] != 0) {
                            arr[i][j] = arr[i][k];
                            arr[i][k] = 0;
                            break;
                        }
                    }
                }
            }
            //归并
            for (int j = m - 1; j > 0; j--) {
                if (arr[i][j] == arr[i][j - 1]) {
                    arr[i][j] *= 2;
                    arr[i][j - 1] = 0;
                }
            }
            //交换
            for (int j = 1; j < m; j++) {
                if (j >= 1 && arr[i][j - 1] != 0 && arr[i][j] == 0) {
                    int temp = arr[i][j];
                    arr[i][j] = arr[i][j - 1];
                    arr[i][j - 1] = temp;

                }
            }
        }
    }
}
