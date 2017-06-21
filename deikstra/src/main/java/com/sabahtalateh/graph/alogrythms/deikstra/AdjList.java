package com.sabahtalateh.graph.alogrythms.deikstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AdjList {

    private Map<Integer, ArrayList<Map<Integer, Integer>>> adjList;

    public AdjList() {
        this.adjList = new HashMap<Integer, ArrayList<Map<Integer, Integer>>>();
    }

    public void addVertex(int v) {
        adjList.put(v, new ArrayList<Map<Integer, Integer>>());
    }

    @Override
    public String toString() {
        return "AdjList{" +
                "adjList=" + adjList +
                '}';
    }

    public void addEdge(int v1, int v2, int w) {
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        m.put(v2, w);
        adjList.get(v1).add(m);
    }
}


