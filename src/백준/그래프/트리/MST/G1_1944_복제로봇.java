package 백준.그래프.트리.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.comparingInt;

/**
 * G1 1944 복제로봇
 * MST, Graph, BFS
 */
public class G1_1944_복제로봇 {

    static int[] parent;
    static char[][] maze;
    static boolean[][] isSpot;
    static PriorityQueue<Edge> edges = new PriorityQueue<>(comparingInt(e -> e.dist));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        maze = new char[N][N];
        isSpot = new boolean[N][N];
        parent = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            parent[i] = i;
        }
        int numOfSpots = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == 'S' || maze[i][j] == 'K') {
                    isSpot[i][j] = true;
                    numOfSpots += 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isSpot[i][j]) addEdge(N, new Point(i, j, 0));
            }
        }

        int ans = 0;
        int cnt = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (find(edge.a) != find(edge.b)) {
                ans += edge.dist;
                cnt += 1;
                union(edge.a, edge.b);
            }
        }
        if (cnt + 1 != numOfSpots) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
        br.close();
    }

    static int[] dr = new int[]{1, -1, 0, 0};
    static int[] dc = new int[]{0, 0, 1, -1};

    private static void addEdge(int N, Point start) {

        boolean[][] visited = new boolean[N][N];
        visited[start.r][start.c] = true;

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nr = now.r + dr[k];
                int nc = now.c + dc[k];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (maze[nr][nc] == '1') continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                if (isSpot[nr][nc]) {
                    int a = start.r * N + start.c;
                    int b = nr * N + nc;
                    edges.add(new Edge(a, b, now.depth + 1));
                }
                queue.add(new Point(nr, nc, now.depth + 1));
            }
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static class Point {
        final int r;
        final int c;
        final int depth;

        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }

    static class Edge {
        int a;
        int b;
        int dist;

        public Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }
}
