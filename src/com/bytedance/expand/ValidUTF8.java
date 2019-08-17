package com.bytedance.expand;

/**
 * Created by lynch on 2019-08-12. <br>
 **/

/**
 * @author Lynch
 * @date 2019/8/12 12:11
 * UTF-8 编码验证
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
 * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
 * 这是 UTF-8 编码的工作方式：
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
 * <p>
 * 注意:
 * 输入是整数数组。只有每个整数的最低 8 个有效位用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 * 示例 1:
 * <p>
 * data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
 * <p>
 * 返回 true 。
 * 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。
 * 示例 2:
 * <p>
 * data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
 * <p>
 * 返回 false 。
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 */
public class ValidUTF8 {

    public static void main(String[] args) {
        int[] data1 = new int[]{197, 130, 1};
        int[] data2 = new int[]{235, 140, 4};
        System.out.println(validUtf8(data1));
        System.out.println(validUtf8(data2));
    }

    /**
     * 要记连续1的个数，
     * 如果是标识字节，先将其向右平移五位，如果得到 110，则说明后面跟了一个字节，
     * 否则向右平移四位，如果得到 1110，则说明后面跟了两个字节，
     * 否则向右平移三位，如果得到 11110，则说明后面跟了三个字节，
     * 否则向右平移七位，如果为1的话，说明是 10000000 这种情况，不能当标识字节，直接返回 false。
     * 在非标识字节中，向右平移六位，如果得到的不是 10，则说明不是以 10 开头的，直接返回 false，
     * 否则 cnt 自减1，成功完成遍历返回 true
     *
     * @param data
     * @return
     */
    public static boolean validUtf8(int[] data) {
        int cnt = 0;
        for (int d : data) {
            if (cnt == 0) {
                if ((d >> 5) == 0b110) cnt = 1;
                else if ((d >> 4) == 0b1110) cnt = 2;
                else if ((d >> 3) == 0b11110) cnt = 3;
                else if ((d >> 7) == 1) return false;
            } else {
                if ((d >> 6) != 0b10) return false;
                cnt--;
            }
        }
        return cnt == 0;
    }
}
