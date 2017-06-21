package com.sabahtalateh.graph.alogrythms.deikstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] lines = new String[30];

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

        ArrayList<String> lst = new ArrayList<String>();

        String line1 = reader.readLine();
        lines[0] = line1;
        Collections.addAll(lst, line1.split(" "));

        int numV = Integer.valueOf(lst.get(0));
        int numE = Integer.valueOf(lst.get(1));
        lst.clear();

        for (int i = 0; i < numV; i++) {
            adjList.addVertex(i + 1);
        }

        for (int i = 0; i < numE; i++) {
            String line = reader.readLine();
            Collections.addAll(lst, line.split(" "));
            lines[i+1] = line;
            int v1 = Integer.valueOf(lst.get(0));
            int v2 = Integer.valueOf(lst.get(1));
            int w = Integer.valueOf(lst.get(2));
            adjList.addEdge(v1, v2, w);
            lst.clear();
        }

//        System.out.println(Arrays.toString(lines));

        line1 = reader.readLine();
        Collections.addAll(lst, line1.split(" "));
        lines[28] = line1;
        int start = Integer.valueOf(lst.get(0));
        int finish = Integer.valueOf(lst.get(1));

        long ans = new Algorithm().run(adjList, start, finish);
        lines[29] = String.valueOf(ans);
        System.out.println(ans);

        Random random = new Random();
        random.nextInt(1);
        int ran = random.nextInt(5);
//        System.out.println(in);
        if (ran == 4) {
//            throw new Exception(Arrays.toString(lines));
        }
    }


    private static class AdjList {

        private Map<Integer, ArrayList<Map<Integer, Integer>>> adjList;

        AdjList() {
            this.adjList = new HashMap<Integer, ArrayList<Map<Integer, Integer>>>();
        }

        void addVertex(int v) {
            adjList.put(v, new ArrayList<Map<Integer, Integer>>());
        }

        void addEdge(int v1, int v2, int w) {
            HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
            m.put(v2, w);
            adjList.get(v1).add(m);
        }

        ArrayList<Integer> getAllVertexes() {
            ArrayList<Integer> vs = new ArrayList<Integer>();
            adjList.keySet();
            for (Map.Entry<Integer, ArrayList<Map<Integer, Integer>>> e : this.adjList.entrySet()) {
                vs.add(e.getKey());
            }
            return vs;
        }

        ArrayList<Map<Integer, Integer>> getAdjListForVertex(int v) {
            return adjList.get(v);
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

            ArrayList<Integer> optimal = new ArrayList<Integer>();
            ArrayList<Integer> rest = adjList.getAllVertexes();

            long path = 0;
            optimal.add(start);
            rest.remove(rest.indexOf(start));

//        for (int i = 0; i < 3; i++) {
            while (!rest.isEmpty()) {
                Optional<Map<Integer, Integer>> min = Optional.empty();
                List<Map<Integer, Integer>> nexToGo = new ArrayList<Map<Integer, Integer>>();
                for (int v : optimal) {
                    nexToGo.addAll(adjList.getAdjListForVertex(v));
                }

                nexToGo = nexToGo.stream().filter(integerIntegerMap -> {
                    int v1 = (Integer) integerIntegerMap.keySet().toArray()[0];
                    return rest.indexOf(v1) != -1;
                }).collect(Collectors.toList());

                if (nexToGo.isEmpty()) {
                    return -1;
                }

                min = nexToGo.stream().min(Comparator.comparingInt(o -> (Integer) o.values().toArray()[0]));

                if (min.isPresent()) {
                    path += (Integer) min.get().values().toArray()[0];
                    int minV = (Integer) min.get().keySet().toArray()[0];
                    optimal.add(minV);
                    rest.remove(rest.indexOf(minV));
                }

                if (rest.indexOf(end) == -1) {
                    break;
                }
            }

            return path;
        }
    }
}
