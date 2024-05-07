package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


 /**
  * P5 1162 도로포장
  * 최단경로, 다익스트라
  */
 public class P5_1162_도로포장 {
     public static void main(String[] args) {
         try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
             solve(br);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     static void solve(BufferedReader br) throws IOException {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int N = parseInt(st.nextToken());
         int M = parseInt(st.nextToken());
         int K = parseInt(st.nextToken());
         addEdge(br, M, K);

         long[][] distances = new long[N+1][K+1];
         for (long[] arr : distances) {
             Arrays.fill(arr, Long.MAX_VALUE);
         }

         PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.dist));
         pq.offer(new Pair(Node.of(1, 0), 0));

         while (!pq.isEmpty()) {
             Pair pair = pq.poll();
             Node now = pair.node;
             long nowDist = pair.dist;

             if (nowDist > distances[now.id][now.k]) continue;

             for (Edge edge : now.edges) {
                 long dist = nowDist + edge.weight;
                 Node next = edge.to;
                 if (dist < distances[next.id][next.k]) {
                     distances[next.id][next.k] = dist;
                     pq.offer(new Pair(next, dist));
                 }
             }
         }

         long ans = Long.MAX_VALUE;
         for (long dist : distances[N]) {
             ans = Math.min(ans, dist);
         }
         System.out.println(ans);
     }


     private static void addEdge(BufferedReader br, int M, int K) throws IOException {
         StringTokenizer st;
         for (int i = 0; i < M; i++) {
             st = new StringTokenizer(br.readLine());
             int a = parseInt(st.nextToken());
             int b = parseInt(st.nextToken());
             int weight = parseInt(st.nextToken());
             for (int k = 0; k < K; k++) {
                 Node a0 = Node.of(a, k);
                 Node b0 = Node.of(b, k);
                 Node a1 = Node.of(a, k+1);
                 Node b1 = Node.of(b, k+1);
                 a0.addEdge(b0, weight);
                 b0.addEdge(a0, weight);
                 b0.addEdge(a1, 0);
                 a0.addEdge(b1, 0);
             }
             Node ak = Node.of(a, K);
             Node bk = Node.of(b, K);
             ak.addEdge(bk, weight);
             bk.addEdge(ak, weight);
         }
     }


     static class Pair {
         Node node;
         long dist;

         public Pair(Node node, long dist) {
             this.node = node;
             this.dist = dist;
         }
     }

     static class Node {
         static Node[][] nodes = new Node[10_001][21];
         int id;
         int k;
         List<Edge> edges = new ArrayList<>();

         static Node of(int id,int k) {
             if (nodes[id][k] == null) {
                 nodes[id][k] = new Node();
                 nodes[id][k].id = id;
                 nodes[id][k].k = k;
             }
             return nodes[id][k];
         }

         void addEdge(Node to, int weight) {
             edges.add(new Edge(to, weight));
         }
     }

     static class Edge {
         Node to;
         int weight;

         public Edge(Node to, int weight) {
             this.to = to;
             this.weight = weight;
         }
     }
 }