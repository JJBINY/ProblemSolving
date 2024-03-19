package 프로그래머스.그래프.위상정렬;

import java.util.*;

public class Lv2_도넛과막대그래프 {

    //위상정렬, BFS
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        Map<Integer, Node> map = new HashMap<>();

        for (int[] e : edges) {

            Node a = map.getOrDefault(e[0], new Node(e[0]));
            Node b = null;
            if (e[0] == e[1]) {
                b = a;
            } else {
                b = map.getOrDefault(e[1], new Node(e[1]));
            }
            a.edges.add(new Edge(b));
            b.inDegree++;
            map.put(e[0], a);
            map.put(e[1], b);
        }

        Node start = null;
        for (Node node : map.values()) {
            if (node.inDegree == 0 && node.edges.size() >= 2) {
                start = node;
                break;
            }
        }
        Queue<Node> Q = new LinkedList<>();
        Q.offer(start);
        answer[0] = start.id;
        while (!Q.isEmpty()) {
            Node now = Q.poll();

            if (now.edges.isEmpty()) {
                answer[2]++; //막대
                continue;
            } else if (now != start && now.edges.size() == 2) {
                answer[3]++; //8자
                continue;
            } else if (now.visited) {
                answer[1]++; //도넛
                continue;
            }

            now.visited = true;
            for (Edge edge : now.edges) {
                if (edge.used) continue;
                edge.used = true;
                Node next = edge.next;
                next.inDegree--;
                Q.offer(next);
            }
        } //while - bfs

        return answer;
    }


    class Node {
        int id;
        int inDegree;
        boolean visited;
        List<Edge> edges = new ArrayList<>();

        Node(int id) {
            this.id = id;
        }
    }

    class Edge {
        Node next;
        boolean used;

        Edge(Node next) {
            this.next = next;
        }
    }
}