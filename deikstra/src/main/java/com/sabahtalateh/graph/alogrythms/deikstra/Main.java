package com.sabahtalateh.graph.alogrythms.deikstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        String in = "5 10\n" +
                "1 2 10\n" +
                "1 3 5\n" +
                "2 3 2\n" +
                "2 4 1\n" +
                "3 2 3\n" +
                "3 4 9\n" +
                "3 5 2\n" +
                "4 5 4\n" +
                "5 1 7\n" +
                "5 4 6\n" +
                "1 4";

        AdjList adjList = new AdjList();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new StringReader(in));

        List<String> lst = new ArrayList<>();

        String line = reader.readLine();
        Collections.addAll(lst, line.split(" "));

        int numV = Integer.valueOf(lst.get(0));
        int numE = Integer.valueOf(lst.get(1));
        lst.clear();

        for (int i = 0; i < numV; i++) {
            adjList.addVertex(i + 1);
        }


        for (int i = 0; i < numE; i++) {
            line = reader.readLine();
            Collections.addAll(lst, line.split(" "));
            int v1 = Integer.valueOf(lst.get(0));
            int v2 = Integer.valueOf(lst.get(1));
            int w = Integer.valueOf(lst.get(2));
            adjList.addEdge(v1, v2, w);
            lst.clear();
        }

        System.out.println(adjList);

        line = reader.readLine();
        Collections.addAll(lst, line.split(" "));
        int start = Integer.valueOf(lst.get(0));
        int finish = Integer.valueOf(lst.get(1));
        long path = new Algorithm().run(adjList, start, finish);

        System.out.println(path);
    }


    private static class AdjList {

        private Map<Integer, ArrayList<WeightedVertex>> adjList;

        AdjList() {
            this.adjList = new HashMap<>();
        }

        void addVertex(int v) {
            this.adjList.put(v, new ArrayList<>());
        }

        void addEdge(int v1, int v2, int w) {
            adjList.get(v1).add(new WeightedVertex(v2, w));
        }

        Set<Integer> getAllVertexes() {
            return new HashSet<>(adjList.keySet());
        }

        ArrayList<WeightedVertex> getSiblings(int v) {
            System.out.println(this.adjList);
            return this.adjList.get(v);
        }

        @Override
        public String toString() {
            return "AdjList{" +
                    "adjList=" + adjList +
                    '}';
        }
    }

    private static class Algorithm {
        long run(AdjList adjList, int start, int end) {
            long path = 0;

            HashMap<Integer, Long> optimal = new HashMap<>();
            Set<Integer> rest = adjList.getAllVertexes();

            optimal.put(start, 0L);
            rest.remove(start);

            List<WeightedEdge> waysToGo = new ArrayList<>();
            for (Map.Entry<Integer, Long> vertex : optimal.entrySet()) {
                System.out.println(vertex.getKey());
                System.out.println(adjList.getSiblings(vertex.getKey()));
//                List<WeightedVertex> child = adjList.getSiblings(vertex.getKey());
//                System.out.println(child);



//                for (WeightedVertex sibling : weightedVertices) {
//                    waysToGo.add(new WeightedEdge(vertex, sibling.getVertex(), sibling.getWeight()));
//                }
            }

//            System.out.println(waysToGo);


            return path;
        }
    }

    private static class WeightedVertex {
        private long vertex;
        private long weight;

        WeightedVertex(long vertex, long weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public long getVertex() {
            return vertex;
        }

        public long getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "W-V{" +
                    "v=" + vertex +
                    ", w=" + weight +
                    '}';
        }
    }

    private static class WeightedEdge {
        private long v1;
        private long v2;
        private long weight;

        WeightedEdge(long v1, long v2, long weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        public long getV1() {
            return v1;
        }

        public long getV2() {
            return v2;
        }

        public long getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "W-E{" +
                    "v1=" + v1 +
                    ", v2=" + v2 +
                    ", w=" + weight +
                    '}';
        }
    }
}
