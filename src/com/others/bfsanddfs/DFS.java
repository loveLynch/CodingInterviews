package com.others.bfsanddfs;

import java.util.HashMap;
import java.util.LinkedList;

public class DFS {
    public static int count = 0;

    private static void dfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited) {
        visit(graph, visited, 'u');//为了和图中的顺序一样，我人为控制了DFS先访问u节点
        visit(graph, visited, 'w');
    }

    private static void visit(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited, char start) {
        if (!visited.containsKey(start)) {
            count++;
            System.out.println("The time into element " + start + ":" + count);//记录进入该节点的时间
            visited.put(start, true);
            for (char c : graph.get(start)) {
                if (!visited.containsKey(c)) {
                    visit(graph, visited, c);//递归访问其邻近节点
                }
            }
            count++;
            System.out.println("The time out element " + start + ":" + count);//记录离开该节点的时间
        }
    }

    public static void main(String[] args) {
        HashMap<Character, LinkedList<Character>> graph = new HashMap<>();
        LinkedList<Character> u = new LinkedList<>();
        u.add('u');
        u.add('v');
        u.add('x');
        graph.put('u', u);
        LinkedList<Character> x = new LinkedList<>();
        x.add('x');
        x.add('u');
        x.add('v');
        x.add('y');
        graph.put('x', x);
        LinkedList<Character> v = new LinkedList<>();
        v.add('v');
        v.add('u');
        v.add('x');
        v.add('y');
        graph.put('v', v);
        LinkedList<Character> y = new LinkedList<>();
        y.add('y');
        y.add('x');
        y.add('v');
        y.add('w');
        graph.put('y', y);
        LinkedList<Character> w = new LinkedList<>();
        w.add('w');
        w.add('y');
        w.add('z');
        graph.put('w', w);
        LinkedList<Character> z = new LinkedList<>();
        z.add('z');
        z.add('w');
        z.add('z');
        graph.put('z', z);
        HashMap<Character, Boolean> visited = new HashMap<>();
        dfs(graph, visited);
    }
}
