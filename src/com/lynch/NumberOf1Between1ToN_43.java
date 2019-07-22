package com.lynch;

/**
 * Created by lynch on 2019-03-28. <br>
 * 43.1～n整数中1出现的次数
 * 输入一个整数n，求1～n这n个整数的十进制表示中1出现的次数。
 * 如输入12，1～12中包含1的数字有1、10、11、12，一共出现了5次
 **/
public class NumberOf1Between1ToN_43 {

    /**
     * 对每个数字作除法与求余
     * 每位判断
     *
     * @param n
     * @return
     */
    private static int numberOf1Between1AndN(int n) {
        int count = 0;
        if (n <= 0)
            return count;
        for (int i = 1; i <= n; i++)
            count += numberOf1(i);
        return count;
    }

    private static int numberOf1(int i) {
        int count = 0;
        while (i != 0) {
            if (i % 10 == 1)
                count++;
            i /= 10;
        }
        return count;
    }

    /**
     * 以21345为例。
     * 步骤1：把1~21345的所有数字分成两段：第1段是1~1345，第2段是1346~21345。
     * 步骤2：计算第2段中1出现的次数。
     * 步骤2.1：计算最高位万位中1出现的次数，要分最高位是否为1考虑。
     * 此处最高位大于1，countFirst1 =10^4。
     * 步骤2.2：计算其他位中1出现的次数countOhters1=2*10^3*4。
     * （1346~21345与1~20000的countOhters1是相等的，所以可以转化为分析1~20000）
     * 步骤3：依据步骤1,2，递归计算1~1345。
     *
     * @param n
     * @return
     */
    private static int numberOf1Between1AndN2(int n) {
        if (n <= 0)
            return 0;
        if (n < 10)
            return 1;
        String nString = Integer.toString(n);
        char firstChar = nString.charAt(0);
        String apartFirstString = nString.substring(1);
        //计算other~n中1出现的次数，递归计算apartFirstString
        int countFirst1 = 0;
        if (firstChar > '1')
            countFirst1 = power10(nString.length() - 1);
        else
            countFirst1 = Integer.parseInt(apartFirstString) + 1;
        //char - '0' char转int
        //char + '0' int转char
        int countOhters1 = (firstChar - '0') * power10(nString.length() - 2) * (nString.length() - 1);
        return countFirst1 + countOhters1 + numberOf1Between1AndN(Integer.parseInt(apartFirstString));
    }

    public static int power10(int n) {
        int result = 1;
        for (int i = 0; i < n; i++)
            result *= 10;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1Between1AndN(1345));
        System.out.println(numberOf1Between1AndN2(21345));
    }
}
