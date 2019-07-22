package com.lynch;

/**
 * Created by lynch on 2019-04-03. <br>
 * 67.把字符串转换成整数
 * <p>
 * 说明：考虑的特殊情况非常多
 **/
public class StringToInt_67 {
    /**
     * 1。如果前面有空格，需要剔除空格；
     * 2。剔除空格后，第一个字符串如果是+号，认为是正数；如果是-号，认为是负数；
     * 3。后面的字符如果不是数字，那么返回0，如果是数字，返回实际的数字。遇到不是数字的字符，转换结束。
     * 4。此外，要考虑空串问题，数值溢出问题[2^(-31) ~ 2^31-1]。
     *
     * @param str
     * @return
     * @throws Exception
     */
    private static int strToInt(String str) throws Exception {
        if (str == null || str.length() == 0)
            throw new Exception("待转换字符串为null或空串");
        String MAX_INT_PLUS_1 = Integer.toString(Integer.MIN_VALUE).substring(1);
        StringBuilder stringBuilder = new StringBuilder(str.trim());
        int flag = 0; //记录无符号的正（2）正（1），负（-1）,初始值（0）
        if (stringBuilder.charAt(0) == '-')
            flag = -1;
        else if (stringBuilder.charAt(0) == '+')
            flag = 1;
        else if (stringBuilder.charAt(0) >= '0' && stringBuilder.charAt(0) <= '9')
            flag = 2;
        else
            return 0;
        int endIndex = 1;
        while (endIndex < stringBuilder.length() && stringBuilder.charAt(endIndex) >= '0' && stringBuilder.charAt(endIndex) <= '9')
            endIndex++;
        if (flag == 2) {
            if (stringBuilder.substring(0, endIndex).toString().compareTo(MAX_INT_PLUS_1) >= 0)
                throw new Exception("数值上溢,待转换字符串为" + str);
            return Integer.parseInt(stringBuilder.substring(0, endIndex));
        } else {
            if (flag == 1 && stringBuilder.substring(1, endIndex).compareTo(MAX_INT_PLUS_1) >= 0)
                throw new Exception("数值上溢,待转换字符串为" + str);
            if (flag == -1 && stringBuilder.substring(1, endIndex).compareTo(MAX_INT_PLUS_1) > 0)
                throw new Exception("数值下溢,待转换字符串为" + str);
            if (flag == -1 && stringBuilder.substring(1, endIndex).compareTo(MAX_INT_PLUS_1) == 0)
                //此处注意，此种情况不能用绝对值*（-1），该绝对值已经超出正数的最大值
                return Integer.MIN_VALUE;
            return flag * Integer.parseInt(stringBuilder.substring(1, endIndex));
        }
    }

    public static void funcTest() {
        try {
            System.out.println(strToInt(" 100")); //100
            System.out.println(strToInt("-100")); //-100
            System.out.println(strToInt("0")); //0
            System.out.println(strToInt("-0"));//0
            System.out.println(strToInt("1.23"));  //1
            System.out.println(strToInt("-1.23")); //-1
            System.out.println(strToInt(".123"));  //0
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void edgeTest() {
        try {
            System.out.println(strToInt("2147483647"));  //2147483647
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(strToInt("-2147483647")); //-2147483647
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(strToInt("2147483647"));  //2147483647
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(strToInt("2147483648"));  //上溢
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(strToInt("-2147483648")); //-2147483648
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(strToInt("-2147483649")); //下溢
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(strToInt(null)); //待转换字符串为null或空串
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(strToInt(""));   //待转换字符串为null或空串
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static int stringToInt(String str) {
        //判断输入是否合法
        if (str == null || str.trim().equals("")) {
            return 0;
        }
        // symbol=0,说明该数为正数;symbol=1，该数为负数;start用来区分第一位是否为符号位
        int symbol = 0;
        int start = 0;
        char[] chars = str.trim().toCharArray();
        if (chars[0] == '+') {
            start = 1;
        } else if (chars[0] == '-') {
            start = 1;
            symbol = 1;
        }
        int result = 0;
        for (int i = start; i < chars.length; i++) {
            if (chars[i] > '9' || chars[i] < '0') {
                return 0;
            }
            int sum = result * 10 + (int) (chars[i] - '0');


            if ((sum - (int) (chars[i] - '0')) / 10 != result) {
                return 0;
            }

            result = result * 10 + (int) (chars[i] - '0');
            /*
             * 本人认为java热门第一判断是否溢出是错误的，举个反例
             * 当输入为value=2147483648时，在计算机内部的表示应该是-2147483648
             * 显然value>Integer.MAX_VALUE是不成立的
             */
        }
        // 注意：java中-1的n次方不能用：(-1)^n .'^'异或运算
        // 注意，当value=-2147483648时，value=-value
        result = (int) Math.pow(-1, symbol) * result;
        return result;
    }

    public static int StringToInt(String str) {
        int n = str.length(), s = 1;
        long res = 0;
        char[] strChar = str.toCharArray();
        if (n == 0) return 0;
        if (strChar[0] == '-') s = -1;
        for (int i = (strChar[0] == '-' || strChar[0] == '+') ? 1 : 0; i < n; ++i) {
            if (!('0' <= strChar[i] && strChar[i] <= '9')) return 0;
            res = (res << 1) + (res << 3) + (strChar[i] & 0xf);//res=res*10+str[i]-'0';
        }
        return (int) (res * s);
    }
//1
//-1
//0
//2147483647
//-2147483647
//2147483647

    public static void main(String[] args) {
//        funcTest();
//        edgeTest();
        System.out.println(stringToInt("-"));
        System.out.println(stringToInt("+"));
        System.out.println(stringToInt("aa"));
        System.out.println(stringToInt("2147483647"));
        System.out.println(stringToInt("2147483648"));
        System.out.println(stringToInt("-2147483647"));
        System.out.println(stringToInt("-2147483648"));
        System.out.println(stringToInt("-2147483649"));
        System.out.println(StringToInt("-2147483651"));

    }
}
