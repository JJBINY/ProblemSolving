package 백준.그래프.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.*;

/**
https://www.acmicpc.net/problem/11725
 문제
 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.

 입력
 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.

 출력
 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
 */
public class S2_11725_트리의부모찾기 {
    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 그래프를 생성한다.
        List<Node> nodes = initGraph(br, N);

        // 1번부터 BFS 혹은 DFS를 수행한다.
        bfs(N, nodes);

        // 2번 노드부터 순서대로 기록된 부모 노드를 출력한다.
        nodes.subList(1,N).stream()
                .forEach(node -> System.out.println(node.parentId));
    }
    private static class Node{

        Integer id;

        Integer parentId;
        List<Node> links = new ArrayList<>();

        public Node(Integer id) {
            this.id = id;
        }

        public void link(Node n) {
            links.add(n);
        }
    }
    private static List<Node> initGraph(BufferedReader br, int N) throws IOException {
        List<Node> nodes = IntStream.rangeClosed(1, N)
                .mapToObj(i -> new Node(i))
                .collect(Collectors.toList());
        for (int i = 0; i < N - 1; i++) {
            String[] link = br.readLine().split(" ");
            Node a = nodes.get(Integer.parseInt(link[0])-1);
            Node b = nodes.get(Integer.parseInt(link[1])-1);
            a.link(b);
            b.link(a);
        }
        return nodes;
    }
    private static void bfs(int N, List<Node> nodes) {
        boolean visited[] = new boolean[N +1];
        Queue<Node> q = new LinkedList();
        q.add(nodes.get(0));
        while (!q.isEmpty()) {
            Node now = q.poll();
            visited[now.id] = true;
            for (Node child : now.links) {
                // 이미 방문한 경우 건너뜀
                if (visited[child.id]) continue;

                // 탐색 과정에서 이전 노드를 현재 노드의 부모 노드로 기록한다.
                child.parentId = now.id;
                q.add(child);
            }
        }
    }
}
