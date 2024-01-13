package 백준.그래프.DFS.SCC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * P5 Strongly Connected Component
 * https://www.acmicpc.net/problem/2150
 * 타잔알고리즘 사용
 */
public class P5_StronglyConnectedComponent {

    static class Node {
        int id;
        int sequence; //방문 순서
        int parent; // 부모노드의 방문 순서
        boolean visited;
        boolean finished;
        List<Node> nexts = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    static List<List<Integer>> sccs = new ArrayList<>();
    static Stack<Node> stack = new Stack<>();
    static int sequence;
    static void dfs(Node now) {

        now.visited = true;
        now.sequence = ++sequence;
        now.parent = now.sequence;
        stack.push(now);
        for (Node next : now.nexts) {
            if (!next.visited) {
                dfs(next);
                now.parent = Math.min(now.parent, next.parent);
            }else if(!next.finished){
                now.parent = Math.min(now.parent, next.sequence);// 싸이클 시작한 노드로 부모노드 갱신
            }
        }

        if (now.parent == now.sequence) {
            addScc(now);
        }
    }

    private static void addScc(Node now) {
        List<Integer> scc = new ArrayList<>();
        while (true) {
            Node node = stack.pop();
            node.finished = true;
            scc.add(node.id);
            if(node == now) break;
        }
        scc.sort(Comparator.naturalOrder());
        sccs.add(scc);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //create graph
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<Node> nodes = IntStream.range(0, v + 1).mapToObj(Node::new).collect(Collectors.toList());
        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            nodes.get(Integer.parseInt(st.nextToken()))
                    .nexts.add(nodes.get(Integer.parseInt(st.nextToken())));
        }
        br.close();

        //find SCCs
        for (int i = 1; i < v + 1; i++) {
            Node now = nodes.get(i);
            if (now.visited) continue;
            dfs(now);
        }

        //print result
        System.out.println(sccs.size());
        sccs.sort((a, b) -> a.get(0) - b.get(0));
        StringBuilder sb = new StringBuilder();
        for (List<Integer> scc : sccs) {
            for (Integer id : scc) {
                sb.append(id).append(" ");
            }
            sb.append(-1).append("\n");
        }
        System.out.println(sb.toString());
    }
}