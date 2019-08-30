package com.huawei.bishi8_21;

import java.util.*;

/**
 * Created by lynch on 2019-08-21. <br>
 * ja
 * 3
 * ja ee dd
 * dd rr
 * ja cc bb
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String firstName = input.nextLine();
        int groupNum = input.nextInt();
        input.nextLine();
        ArrayList<ArrayList<String>> groupList = new ArrayList<>();
        for (int i = 0; i < groupNum; i++) {
            ArrayList<String> groupPeople = new ArrayList<>();
            String[] people = input.nextLine().split(" ");
            for (int j = 0; j < people.length; j++) {
                groupPeople.add(people[j]);
            }
            groupList.add(groupPeople);
        }
        ArrayList<String> receive = new ArrayList<>();
        receive.add(firstName);
        //方法一：通过移除群的方式方法调用，复杂度较低
        getCountReceiveNum(groupList, receive, groupList.size());
        if (receive.size() > 1)
            System.out.println(receive.size());
        else {
            System.out.println(receive.size() - 1);//只有自己不能转发
        }

        //方法二：设置标志位时的方法调用，复杂度较高点
        ArrayList<ArrayList<String>> groupListClone = (ArrayList<ArrayList<String>>) groupList.clone();
        for (ArrayList<String> group : groupListClone)
            group.add("No");//设置访问标志位
        getCountReceive(groupListClone, receive, 0);
        System.out.println(receive.size() - 1);//去掉"Yes"或只有自己不能转发


    }


    /**
     * 移除被访问过的群，当群不能被移除时，证明不能再转发
     *
     * @param groupList
     * @param receive
     * @param count
     */
    private static void getCountReceiveNum(ArrayList<ArrayList<String>> groupList, ArrayList<String> receive, int count) {
        for (ArrayList<String> group : groupList) {
            ArrayList<String> groupClone = (ArrayList<String>) group.clone();
            groupClone.retainAll(receive);
            if (!groupClone.isEmpty()) {
                for (int i = 0; i < group.size(); i++) {
                    if (!receive.contains(group.get(i)))
                        receive.add(group.get(i));
                    groupList.removeAll(group);
                }
            }
        }
        //当groupList不变时，证明不能再转发，否则递归转发
        if (count == groupList.size() || groupList.size() == 0)
            return;
        else {
            getCountReceive(groupList, receive, groupList.size());
        }
    }

    /**
     * 设置标志为多次访问,访问群的次数次保证都能被访问一次
     *
     * @param groupList
     * @param receive
     * @param count
     */
    private static void getCountReceive(ArrayList<ArrayList<String>> groupList, ArrayList<String> receive, int count) {
        if (count == groupList.size())
            return;
        for (ArrayList<String> group : groupList) {
            ArrayList<String> groupClone = (ArrayList<String>) group.clone();
            groupClone.retainAll(receive);
            if (!groupClone.isEmpty() && group.get(group.size() - 1).equals("No")) {
                for (int i = 0; i < group.size(); i++) {
                    if (!receive.contains(group.get(i)))
                        receive.add(group.get(i));
                    group.set(group.size() - 1, "Yes");
                }
            }
        }
        //递归保证都能被访问到
        if (count < groupList.size()) {
            getCountReceive(groupList, receive, count + 1);
        }
    }


}
