package 백준.그래프.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G2 문제집
 * https://www.acmicpc.net/problem/1766
 */
public class G2_문제집 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Edge edge = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            nodes.get(edge.from).edges.add(edge);
            nodes.get(edge.to).inDegree += 1;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator
                .comparing(Node::getInDegree)
                .thenComparing(Node::getId));

        for (int i = 1; i < n+1; i++) {
            Node node = nodes.get(i);
            if(node.inDegree == 0) {
                pq.add(node);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()){
            Node now = pq.poll();
            sb.append(now.id + " ");
            for (Edge edge : now.edges) {
                Node next = nodes.get(edge.to);
                next.inDegree -= 1;
                if(next.inDegree == 0){
                    pq.add(next);
                }
            }
        }

        System.out.println(sb.toString());

    }


    static class Edge{
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static class Node{
        int id;
        int inDegree = 0;
        List<Edge> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public int getInDegree() {
            return inDegree;
        }
    }
}

