package com.lynch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by lynch on 2019-03-20. <br>
 * 38.字符串的组合
 * <p>
 * 输入一个字符串，打印出该字符串中字符的所有组合。如输入abc，则打印a，b，c，ab，ac，bc，abc。
 **/
public class StringCombination_38_2 {
    //无重复字符：对于每一个字符，都由两种选择：被选中、不被选中；
    //有重复字符：整体需要先排序，对于重复n遍的某种字符，有如下选择：不被选中，选1个，选2个...选n个。
    private static List<char[]> combination(char[] strs) {
        if (strs == null || strs.length == 0)
            return null;
        Arrays.sort(strs);
        List<char[]> ret = new LinkedList<>();
        combinationCore(strs, ret, new StringBuilder(), 0);
        return ret;
    }

    private static void combinationCore(char[] strs, List<char[]> ret, StringBuilder stringBuilder, int cur) {
        if (cur == strs.length) {
            if (stringBuilder.length() > 0)
                ret.add(stringBuilder.toString().toCharArray());
        } else if (cur + 1 == strs.length || strs[cur] != strs[cur + 1]) {
            combinationCore(strs, ret, stringBuilder.append(strs[cur]), cur + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            combinationCore(strs, ret, stringBuilder, cur + 1);

        } else {
            //先计算出重复次数
            int dumplicateStart = cur;
            while (cur != strs.length && strs[dumplicateStart] == strs[cur]) {
                stringBuilder.append(strs[cur]);
                cur++;
            }
            int newStart = cur;
            while (cur >= dumplicateStart) {
                combinationCore(strs, ret, stringBuilder, newStart);
                if (cur != dumplicateStart)
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                cur--;
            }

        }
    }

    private static void combine(char[] str,int start,int end){

        if (start == end){
            System.out.println(str);
        }else{
            for(int j=start;j<end;j++){
                if (str[start] == str[j] && start!=j) continue;  //不同位置的相同字符不交换，避免重复
                swap(str,start,j);
                combine(str,start+1,end);//递归
                swap(str,start,j);  //还原字符串，为下一for循环准备

            }
        }

    }

    private static void swap(char[] str,int i,int j){
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }


    public static void main(String[] args) {
        char[] strs = {'a', 'a', 'b'};
        List<char[]> ret = combination(strs);
        for (char[] item : ret) {
            for (int i = 0; i < item.length; i++)
                System.out.print(item[i]);
            System.out.println();
        }
        System.out.println();
        combine(strs,0,strs.length);
    }
}
