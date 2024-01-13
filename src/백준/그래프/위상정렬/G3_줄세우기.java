package 백준.그래프.위상정렬;

import java.io.*;
import java.util.*;

/**
 * G3 줄세우기
 * https://www.acmicpc.net/problem/2252
 */
public class G3_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[n + 1];
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
        }
        int m = Integer.parseInt(st.nextToken());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            Node from = nodes.get(Integer.parseInt(st.nextToken()));
            Node to = nodes.get(Integer.parseInt(st.nextToken()));
            from.nexts.add(to);
            inDegree[to.id] += 1;
        }
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(inDegree[i+1] == 0) queue.add(nodes.get(i + 1));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            bw.write(now.id + " ");
            for (Node next : now.nexts) {
                if(--inDegree[next.id] > 0) continue;
                queue.add(next);
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static class Node{
        int id;
        List<Node> nexts = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }

}