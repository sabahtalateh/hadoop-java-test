package com.sabahtalateh.graph.alogrythms.deikstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

public class Deikstra {
    public static void main(String[] args) throws IOException {
        AdjList adjList = new AdjList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> lst = new ArrayList<String>();
        Collections.addAll(lst, reader.readLine().split(" "));

        int numV = Integer.valueOf(lst.get(0));
        int numE = Integer.valueOf(lst.get(1));
        lst.clear();

        for (int i = 0; i < numV; i++) {
            adjList.addVertex(i+1);
        }

        for (int i = 0; i < numE; i++) {
            Collections.addAll(lst, reader.readLine().split(" "));
            System.out.println(lst);
            int v1 =Integer.valueOf(lst.get(0));
            int v2 =Integer.valueOf(lst.get(1));
            int w =Integer.valueOf(lst.get(2));
            adjList.addEdge(v1, v2, w);
            lst.clear();
        }

        System.out.println(adjList);
    }
}
