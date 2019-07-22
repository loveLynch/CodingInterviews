package wangyi._2018;

import java.util.Scanner;

/**
 * Created by lynch on 2019-04-15. <br>
 * 为了得到一个数的"相反数",我们将这个数的数字顺序颠倒,然后再加上原先的数得到"相反数"。
 * 例如,为了得到1325的"相反数",首先我们将该数的数字顺序颠倒,我们得到5231,之后再加上原先的数,我们得到5231+1325=6556.如果颠倒之后的数字有前缀零,前缀零将会被忽略。例如n = 100, 颠倒之后是1.
 * 输入描述: 输入包括一个整数n,(1 ≤ n ≤ 10^5)
 * 输出描述: 输出一个整数,表示n的相反数
 * <p>
 * 输入例子1: 1325
 * <p>
 * 输出例子1: 6556
 **/
public class Main2 {
    public static void main(String[] args) {

        System.out.println("请输入一个整数：");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        //将字符串转换成数字
        int number1 = Integer.parseInt(s);
        //将字符串倒序后转换成数字
        //因为Integer.parseInt()的参数类型必须是字符串所以必须加上toString()
        int number2 = Integer.parseInt(new StringBuilder(s).reverse().toString());
        System.out.println(number1 + number2);

    }
}
