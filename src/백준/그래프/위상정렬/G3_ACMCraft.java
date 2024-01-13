package 백준.그래프.위상정렬;

import java.io.*;
import java.util.*;

/**
 * G3 ACM Craft
 * https://www.acmicpc.net/problem/1005
 */
public class G3_ACMCraft {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int iter = Integer.parseInt(br.readLine());
        for (int i = 0; i < iter; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 건물의 개수
            int k = Integer.parseInt(st.nextToken()); //건설순서 규칙 개수

            int[] inDegree = new int[n+1];
            st = new StringTokenizer(br.readLine());
            List<Node> nodes = new ArrayList<>();
            nodes.add(new Node(0, 0));
            for (int j = 0; j < n; j++) {
                nodes.add(new Node(j + 1, Integer.parseInt(st.nextToken())));
            }

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                Node from = nodes.get(Integer.parseInt(st.nextToken()));
                Node to = nodes.get(Integer.parseInt(st.nextToken()));
                from.nexts.add(to);
                inDegree[to.id] += 1;
            }

            int target = Integer.parseInt(br.readLine());
            Queue<Node> queue = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                if(inDegree[j+1] == 0){
                    queue.add(nodes.get(j + 1));
                }
            }

            while (!queue.isEmpty()){
                Node now = queue.poll();
                if(now.id == target){
                    bw.write(now.totalTime+"\n");
                    break;
                }
                for (Node next : now.nexts) {

                    next.totalTime = Math.max(next.totalTime, now.totalTime + next.buildTime);
                    if(--inDegree[next.id] == 0) queue.add(next);
                }
            }

        }
        bw.flush();
        bw.close();
        br.close();
    }



    static class Node{
        public Node(int id, int buildTime) {
            this.id = id;
            this.buildTime = buildTime;
            this.totalTime = buildTime;
        }

        int id;
        int buildTime;
        int totalTime;
        List<Node> nexts = new ArrayList<>();

    }

}