package 프로그래머스.그래프.BFS;

import java.util.*;

public class Lv3_부대복귀 {

    // 최단경로, BFS

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        //노드 생성
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        //목표지점 체크
        for (int s : sources) {
            nodes[s].isSource = true;
        }

        //간선 추가
        for (int[] r : roads) {
            Node a = nodes[r[0]];
            Node b = nodes[r[1]];
            a.nexts.add(b);
            b.nexts.add(a);
        }

        int[] dists = new int[n + 1];
        Arrays.fill(dists, -1);

        //최단경로 탐색
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(nodes[destination], 0));
        boolean[] visited = new boolean[n + 1];
        visited[destination] = true;
        while (!q.isEmpty()) {
            int dist = q.peek().dist;
            Node now = q.poll().node;

            if (now.isSource) { //목적지 도달
                dists[now.id] = dist;
            }

            for (Node next : now.nexts) {
                if (visited[next.id]) continue;
                visited[next.id] = true;
                q.add(new Pair(next, dist + 1));
            }
        }

        //정답 매핑
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dists[sources[i]];
        }

        return answer;
    }

    class Node {
        int id;
        boolean isSource;
        List<Node> nexts = new ArrayList<>();

        Node(int id) {
            this.id = id;
        }
    }

    class Pair {
        Node node;
        int dist;

        Pair(Node node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}