package com.shunfeng;

import java.util.*;

/**
 * Created by lynch on 2019-08-29. <br>
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int k = input.nextInt();
        int[] language = new int[m + 1];
        List<Integer>[] peo2lang = new List[n + 1];
        for (int i = 0; i < n + 1; i++)
            peo2lang[i] = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int peo = input.nextInt();
            int lang = input.nextInt();
            peo2lang[peo].add(lang);
            language[lang] = lang;

        }


        System.out.println(getMinMachine(peo2lang, language, m));


    }

    private static int getMinMachine(List<Integer>[] peo2lang, int[] language, int m) {
        int machine = 0;
        for (List<Integer> list : peo2lang) {
            if (list.isEmpty()) {
                machine++;
                continue;
            }
            int lang1 = list.get(0);
            for (int i = 1; i < list.size(); i++)
                join(language, lang1, list.get(i));
        }
        machine--;
        int temp = 0;
        for (int i = 1; i < m + 1; i++) {
            if (language[i] == i)
                temp++;
        }
        if (temp != 0)
            temp--;
        machine += temp;
        return machine;
    }

    public static int find(int[] language, int lang) {
        if (language[lang] == lang)
            return lang;
        int res = find(language, language[lang]);
        language[lang] = res;
        return res;
    }

    public static void join(int[] language, int lang1, int lang2) {
        int fa1 = find(language, lang1);
        int fa2 = find(language, lang2);
        language[fa2] = fa1;

    }


}
