package com.niuke.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-02. <br>
 **/
public class DelayMax {
    static class Tree {
        public Integer val;
        public Boolean isBecomeRoot;
        public ArrayList<Integer> nextList;

        public Tree(Integer val, Boolean isBecomeRoot) {
            this.val = val;
            this.nextList = new ArrayList<>();
            this.isBecomeRoot = isBecomeRoot;

        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String N = input.nextLine();
        List<String> nodeDelayList = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(N) - 1; i++) {
            String nextLine = input.nextLine();
            nodeDelayList.add(nextLine);
        }
        String Q = input.nextLine();
        List<Integer> nodeList = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(Q); i++) {
            String nextLine = input.nextLine();
            nodeList.add(Integer.valueOf(nextLine));
        }
        int[] fromAndToNode = new int[(Integer.valueOf(N) - 1) * 2];
        int[] nodeDelay = new int[(Integer.valueOf(N) - 1)];
        int i = 0, j = 0;
        for (String dis : nodeDelayList) {
            String[] disArray = dis.split(" ");
            fromAndToNode[i] = Integer.valueOf(disArray[0]);
            i++;
            fromAndToNode[i] = Integer.valueOf(disArray[1]);
            nodeDelay[j] = Integer.valueOf(disArray[2]);
            i++;
            j++;
        }
        for (Integer path : fromAndToNode)
            System.out.print(path + " ");
        System.out.println();
        for (Integer delay : nodeDelay)
            System.out.print(delay + " ");
        System.out.println();
        for (Integer node : nodeList) {
            List<Integer> storeList = new ArrayList<>();
            HashMap<Integer, Integer> lastPathSumMap = new HashMap<>();
            storeList.add(node);
            lastPathSumMap.put(node, 0);
            System.out.println(getNodeToOthersDelaySum(node, new Tree(node, false), fromAndToNode, nodeDelay, Integer.parseInt(N), storeList, lastPathSumMap, 0));
        }
    }


    public static int getNodeToOthersDelaySum(int rootNode, Tree root, int[] fromAndToNode, int[] nodeDelay, int N, List<Integer> storeList, HashMap<Integer, Integer> lastPathSumMap, int step) {
        int maxPath = 0;
        if (fromAndToNode == null || nodeDelay == null)
            return 0;
        if (storeList.size() == N) {
            return maxPath;
        }
        step++;
//        System.out.println("root " + root.val);
        for (int i = 0; i < fromAndToNode.length; i++) {
            if (fromAndToNode[i] == root.val && i % 2 == 0) {
                if (fromAndToNode[i + 1] != root.val && fromAndToNode[i + 1] != rootNode && root.isBecomeRoot == false && !storeList.contains(fromAndToNode[i + 1])) {
                    root.nextList.add((fromAndToNode[i + 1]));
                }
                if (!storeList.contains(fromAndToNode[i + 1])) {
                    storeList.add(fromAndToNode[i + 1]);
                    if (step > 1) {
                        lastPathSumMap.put(fromAndToNode[i + 1], nodeDelay[i / 2] + lastPathSumMap.get(root.val));
                    } else {
                        lastPathSumMap.put(fromAndToNode[i + 1], nodeDelay[i / 2]);
                    }
                    maxPath = compareMaxPath(maxPath, lastPathSumMap.get(fromAndToNode[i + 1]));
                }
            } else if (fromAndToNode[i] == root.val && i % 2 != 0) {

                if (fromAndToNode[i - 1] != root.val && fromAndToNode[i - 1] != rootNode && root.isBecomeRoot == false && !storeList.contains(fromAndToNode[i - 1])) {
                    root.nextList.add((fromAndToNode[i - 1]));
                }
                if (!storeList.contains(fromAndToNode[i - 1])) {
                    storeList.add(fromAndToNode[i - 1]);
                    if (step > 1) {
                        lastPathSumMap.put(fromAndToNode[i - 1], nodeDelay[i / 2] + lastPathSumMap.get(root.val));
                    } else {
                        lastPathSumMap.put(fromAndToNode[i - 1], nodeDelay[i / 2]);
                    }
                    maxPath = compareMaxPath(maxPath, lastPathSumMap.get(fromAndToNode[i - 1]));

                }

            } else {
                continue;
            }
        }
        root.isBecomeRoot = true;
//        System.out.println("storeList start");
//        for (Integer si : storeList)
//            System.out.println(si + " ");
//        System.out.println();
//        System.out.println("storeList end");
//        System.out.println("nextNodeList start");
//        for (Integer si : root.nextList)
//            System.out.println(si + " ");
//        System.out.println();
//        System.out.println("nextNodeList end");

        for (Integer nextNode : root.nextList) {
//            System.out.println("nextNode " + nextNode);
            int middlePath = getNodeToOthersDelaySum(rootNode, new Tree(nextNode, false), fromAndToNode, nodeDelay, N, storeList, lastPathSumMap, step);
            maxPath = compareMaxPath(maxPath, middlePath);
        }


        return maxPath;

    }

    public static int compareMaxPath(int originalMax, int nowPath) {
        if (nowPath > originalMax)
            return nowPath;
        return originalMax;
    }

}
