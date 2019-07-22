package wangyi._2018;

import java.util.Scanner;

/**
 * Created by lynch on 2019-04-15. <br>
 **/
public class Main3_2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            //个数至少为一个
            float count = 1;
            char[] stringArr = s.toCharArray();
            for (int i = 0; i < stringArr.length - 1; i++) {
                if (stringArr[i] != stringArr[i + 1]) {
                    count++;
                }
            }
            System.out.println(s.length() / count);
        }
    }
}
