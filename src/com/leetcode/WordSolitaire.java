package com.leetcode;

import java.util.*;

/**
 * Created by lynch on 2019-08-31. <br>
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * hit cog hot dot dog lot log cog
 **/
public class WordSolitaire {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        String[] strArray = string.split(" ");
        String beginWord = strArray[0];
        String endWord = strArray[1];
        List<String> wordList = new ArrayList<>();
        for (int i = 2; i < strArray.length; i++)
            wordList.add(strArray[i]);
        System.out.println(ladderLength1(beginWord, endWord, wordList));
        System.out.println(ladderLength2(beginWord, endWord, wordList));
    }


    /**
     * 递归bfs
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0)
            return 0;
        //所有字典字符串
        HashSet<String> wordSet = new HashSet<>(wordList);
        //开始端
        HashSet<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        //结束端
        HashSet<String> endSet = new HashSet<>();
        endSet.add(endWord);
        if (!wordSet.contains(endWord))
            return 0;
        //经历过上面的一系列判定，到这里的时候，若是有路径，则最小是2，所以以2开始
        return bfs(beginSet, endSet, wordSet, 2);
    }

    public static int bfs(HashSet<String> st, HashSet<String> ed, HashSet<String> dic, int l) {
        //双端查找的时候，若是有任意一段出现了“断裂”，也就是说明不存在能够连上的路径，则直接返回0
        if (st.size() == 0) return 0;
        if (st.size() > ed.size()) {//双端查找，为了优化时间，永远用少的去找多的，比如开始的时候塞进了1000个，而结尾只有3个，则肯定是从少的那一端开始走比较好
            return bfs(ed, st, dic, l);
        }
        //BFS的标记行为，即使用过的不重复使用
        dic.removeAll(st);
        //收集下一层临近点
        HashSet<String> next = new HashSet<>();
        for (String s : st) {
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char tmp = arr[i];
                //变化
                for (char c = 'a'; c <= 'z'; c++) {
                    if (tmp == c) continue;
                    arr[i] = c;
                    String nstr = new String(arr);
                    if (dic.contains(nstr)) {
                        if (ed.contains(nstr)) return l;
                        else next.add(nstr);
                    }
                }
                //复原
                arr[i] = tmp;
            }
        }
        return bfs(next, ed, dic, l + 1);
    }

    /**
     * 非递归
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        HashSet<String> dic = new HashSet<>(wordList);
        start.add(beginWord);
        end.add(endWord);
        int step = 1;
        if (!dic.contains(endWord)) return 0;
        while (!start.isEmpty()) {
            step++;
            HashSet<String> tmpSet = new HashSet<>();
            dic.removeAll(start);
            for (String s : start) {
                char[] arr = s.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char tmp = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (tmp == c) continue;
                        arr[i] = c;
                        String strTmp = new String(arr);
                        if (dic.contains(strTmp)) {
                            if (end.contains(strTmp)) {
                                return step;
                            } else {
                                tmpSet.add(strTmp);
                            }
                        }
                    }
                    arr[i] = tmp;
                }
            }
            if (tmpSet.size() < end.size()) {
                start = tmpSet;
            } else {
                start = end;
                end = tmpSet;
            }

        }
        return 0;
    }
}
