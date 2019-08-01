package wangyi._2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-25. <br>
 **/
public class LightNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = Integer.parseInt(input.nextLine());
        List<String> numAndLight = new ArrayList<>();
        for (int i = 0; i < 2*N; i++) {
            String numLoad = input.nextLine();
            numAndLight.add(numLoad);
        }
        for (int i = 1; i < 2*N; i = i + 2)
            System.out.println(getMinLight(numAndLight.get(i)));
    }

    private static int getMinLight(String load) {
        char[] loadArray = load.toCharArray();
        int countLight = 0;
        int index = 0;
        while (index < loadArray.length)
            if (loadArray[index] == '.') {
                index += 3;
                countLight++;
            } else {
                index++;
            }
        return countLight;
    }

}
