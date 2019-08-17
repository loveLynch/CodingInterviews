package com.others.bfsanddfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private static void bfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Integer> dist, char start) {
        Queue<Character> q = new LinkedList<>();
        q.add(start);//将s作为起始顶点加入队列
        dist.put(start, 0);
        int i = 0;
        while (!q.isEmpty()) {
            char top = q.poll();//取出队首元素
            i++;
            System.out.println("The " + i + "th element:" + top + " Distance from " + start + " is:" + dist.get(top));
            int d = dist.get(top) + 1;//得出其周边还未被访问的节点的距离
            for (Character c : graph.get(top)) {
                if (!dist.containsKey(c))//如果dist中还没有该元素说明还没有被访问
                {
                    dist.put(c, d);
                    q.add(c);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashMap<Character, LinkedList<Character>> graph = new HashMap<>();
        LinkedList<Character> s = new LinkedList<>();
        s.add('s');
        s.add('w');
        s.add('r');
        graph.put('s', s);
        LinkedList<Character> w = new LinkedList<>();
        w.add('w');
        w.add('s');
        w.add('t');
        w.add('x');
        graph.put('w', w);
        LinkedList<Character> r = new LinkedList<>();
        r.add('r');
        r.add('v');
        graph.put('r', r);
        LinkedList<Character> v = new LinkedList<>();
        v.add('v');
        v.add('r');
        graph.put('v', v);
        LinkedList<Character> t = new LinkedList<>();
        t.add('t');
        t.add('w');
        t.add('x');
        t.add('u');
        graph.put('t', t);
        LinkedList<Character> x = new LinkedList<>();
        x.add('x');
        x.add('w');
        x.add('t');
        x.add('u');
        x.add('y');
        graph.put('x', x);
        LinkedList<Character> u = new LinkedList<>();
        u.add('u');
        u.add('t');
        u.add('x');
        u.add('y');
        graph.put('u', u);
        LinkedList<Character> y = new LinkedList<>();
        u.add('y');
        u.add('u');
        u.add('x');
        graph.put('y', y);
        HashMap<Character, Integer> dist = new HashMap<>();
        bfs(graph, dist, 'w');
    }
}
