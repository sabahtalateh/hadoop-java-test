package com.sabahtalateh.graph.alogrythms.deikstra;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        AdjList adjList = new AdjList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

//            System.out.println(optimal);
//            System.out.println(rest);

//            for (int i = 0; i < 4; i ++) {
            while (!rest.isEmpty()) {
                List<WeightedEdge> waysToGo = new ArrayList<>();
                for (Map.Entry<Integer, Long> vertex : optimal.entrySet()) {
                    List<WeightedVertex> children = adjList.getSiblings(vertex.getKey());
                    for (WeightedVertex child : children) {
                        waysToGo.add(new WeightedEdge(vertex.getKey(), child.getVertex(), child.getWeight()));
                    }
                }

                waysToGo = waysToGo.stream().filter(new Predicate<WeightedEdge>() {
                    @Override
                    public boolean test(WeightedEdge weightedEdge) {
                        if (optimal.containsKey(weightedEdge.getV2())) {
                            return false;
                        }
                        return true;
                    }
                }).collect(Collectors.toList());

                if (waysToGo.isEmpty()) {
                    return -1;
                }

                Optional<WeightedEdge> min = waysToGo.stream().min(new Comparator<WeightedEdge>() {
                    @Override
                    public int compare(WeightedEdge o1, WeightedEdge o2) {
                        return Long.compare(o1.getWeight(), o2.getWeight());
                    }
                });

                if (min.isPresent()) {
                    long weight = optimal.get(min.get().getV1()) + min.get().getWeight();
                    optimal.put(min.get().getV2(), weight);
                    rest.remove(min.get().getV2());
                    if (min.get().getV2() == end) {
                        return weight;
                    }
                } else {
                    return -1;
                }

//                System.out.println(optimal);
//                System.out.println(rest);
            }

            return path;
        }
    }

    private static class WeightedVertex {
        private int vertex;
        private long weight;

        WeightedVertex(int vertex, long weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        int getVertex() {
            return vertex;
        }

        long getWeight() {
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
        private int v1;
        private int v2;
        private long weight;

        WeightedEdge(int v1, int v2, long weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        int getV1() {
            return v1;
        }

        int getV2() {
            return v2;
        }

        long getWeight() {
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
