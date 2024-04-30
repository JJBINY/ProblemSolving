package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G1 16118 달빛여우
 * 최단경로, 다익스트라
 */
public class G1_16118_달빛여우 {
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
        Node.init(N, M, br);

        int[][] fox = new int[2][N + 1];
        int[][] wolf = new int[2][N + 1];
        dijkstra(false, fox);
        dijkstra(true, wolf);
        int ans = 0;

        for (int i = 2; i <= N; i++) {
            if (fox[0][i] < wolf[0][i] && fox[0][i] < wolf[1][i]) {
                ans++;
            }
        }

//        printArr(fox,0);
//        printArr(wolf,0);
//        printArr(wolf,1);
        System.out.println(ans);
    }

    static void printArr(int[][] arr, int x) {
        for (int i = 0; i < arr[0].length; i++) {
            System.out.print(arr[x][i] + " ");
        }
        System.out.println();
    }

    private static void dijkstra(boolean isWolf, int[][] distances) {
        Arrays.fill(distances[0], Integer.MAX_VALUE);
        Arrays.fill(distances[1], Integer.MAX_VALUE);
        PriorityQueue<Path> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.dist));
        pq.offer(new Path(Node.of(1), 0));

        while (!pq.isEmpty()) {
            Node now = pq.peek().node;
            int needRest = pq.peek().needRest;
            int nowDist = pq.poll().dist;

            if (nowDist > distances[needRest][now.id]) {
                continue;
            }

            for (Edge edge : now.edges) {
                if(isWolf){
                    int dist = nowDist + (needRest == 1 ? edge.weight * 2 : edge.weight / 2);
                    if (dist < distances[needRest^1][edge.to]) {
                        distances[needRest^1][edge.to] = dist;
                        pq.offer(new Path(Node.of(edge.to), dist, needRest^1));
                    }
                }else{
                    int dist = nowDist + edge.weight;
                    if (dist < distances[0][edge.to]) {
                        distances[0][edge.to] = dist;
                        pq.offer(new Path(Node.of(edge.to), dist));
                    }
                }
            } // for
        } // while
    }


    static class Path {
        Node node;
        int dist;
        int needRest;

        public Path(Node node, int dist) {
            this(node, dist, 0);
        }

        public Path(Node node, int dist, int needRest) {
            this.node = node;
            this.dist = dist;
            this.needRest = needRest;
        }
    }

    static class Node {
        static Node[] nodes;
        int id;
        List<Edge> edges = new ArrayList<>();

        public static void init(int N, int M, BufferedReader br) throws IOException {
            nodes = new Node[N + 1];
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                Node a = of(parseInt(st.nextToken()));
                Node b = of(parseInt(st.nextToken()));
                int weight = 2 * parseInt(st.nextToken());
                a.edges.add(new Edge(b.id, weight));
                b.edges.add(new Edge(a.id, weight));
            }
        }

        public static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }


    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}