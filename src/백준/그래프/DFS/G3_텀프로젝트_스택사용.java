package 백준.그래프.DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * G3 텀프로젝트
 * https://www.acmicpc.net/problem/9466
 */
public class G3_텀프로젝트_스택사용 {

    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int iter = Integer.parseInt(br.readLine());
        while (iter-- > 0) {
            int n = Integer.parseInt(br.readLine());
            List<Node> nodes = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                nodes.add(new Node(i));
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n + 1; i++) {
                nodes.get(i).next = nodes.get(Integer.parseInt(st.nextToken()));
            }
            visited = new boolean[n + 1];
            int cnt = 0;
            for (int i = 1; i < n + 1; i++) {
                if (visited[i]) continue;
                cnt += dfs(nodes.get(i), new Stack<>());
            }
            bw.write(cnt+ "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(Node now, Stack<Integer> stack) {

        stack.add(now.id);
        visited[now.id] = true;
        Node next = now.next;

        if (!visited[next.id]) return dfs(next, stack);

        if (stack.contains(next.id)) {
            while (stack.pop() != next.id) {
            }
        }
        return stack.size();
    }

    static class Node {
        int id;
        Node next;

        public Node(int id) {
            this.id = id;
        }
    }
}