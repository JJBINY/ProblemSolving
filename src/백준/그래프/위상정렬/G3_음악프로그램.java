package 백준.그래프.위상정렬;

import java.io.*;
import java.util.*;

/**
 * G3 음악프로그램
 * https://www.acmicpc.net/problem/2623
 */
public class G3_음악프로그램 {
    static class Node{
        int id;
        List<Node> nexts = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
        }
        int[] inDegree = new int[n + 1];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            Node now = nodes.get(Integer.parseInt(st.nextToken()));
            while (st.hasMoreTokens()){
                Node next = nodes.get(Integer.parseInt(st.nextToken()));
                inDegree[next.id] += 1;
                now.nexts.add(next);
                now = next;
            }
        }


        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(inDegree[i+1] == 0) queue.add(nodes.get(i + 1));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt+=1;
            Node now = queue.poll();
            bw.write(now.id + "\n");
            for (Node next : now.nexts) {
                if(--inDegree[next.id] > 0) continue;
                queue.add(next);
            }
        }

        if(cnt < n){
            System.out.println(0);
            return;
        }
        bw.flush();
        bw.close();
        br.close();

    }



}