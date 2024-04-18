package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G3 2146 다리 만들기
 * BFS
 */
public class G3_2146_다리만들기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, island;
    static int[][] arr;
    static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
    static List<Pair<Integer, Integer>> edges = new ArrayList<>();

    static void solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }
        if(N==1){
            System.out.println(0);
            return;
        }

        findIslands();
        findMinDist();
    }

    private static void findMinDist() {
        int ans = Integer.MAX_VALUE;
        int[][] visited = new int[N][N];
        for (Pair<Integer, Integer> edge : edges) {
            int r = edge.a / N;
            int c = edge.a % N;
            int island = edge.b;
            arr[r][c] = 0;
            visited[r][c] = island;
        }

        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>(edges);
        while (!queue.isEmpty()) {
            int r = queue.peek().a / N;
            int c = queue.peek().a % N;
            int island = queue.poll().b;
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (arr[nr][nc] == -1 || visited[nr][nc] == island) continue;

                if(visited[nr][nc]>0){ //answer
                    ans = Math.min(ans, arr[r][c] + arr[nr][nc]);
                    break;
                }
                visited[nr][nc] = island;
                arr[nr][nc] = arr[r][c] + 1;
                queue.offer(new Pair<>(nr * N + nc, island));
            }
        }
        System.out.println(ans);
    }

    private static void findIslands() {
        island = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    island++;
                    bfs(i, j);
                }
            }
        }
    }

    private static void bfs(int i, int j) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(i * N + j);
        arr[i][j] = -1;
        while (!queue.isEmpty()) {
            int r = queue.peek() / N;
            int c = queue.poll() % N;
            boolean isEdge = false;
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (arr[nr][nc] == -1) continue;

                if (arr[nr][nc] == 1) {
                    arr[nr][nc] = -1;
                    queue.offer(nr * N + nc);
                } else {
                    isEdge = true;
                }
            } //for
            if (isEdge) {
                edges.add(new Pair<>(r * N + c, island));
            }
        } //while
    }

    static class Pair<T1, T2> {
        T1 a;
        T2 b;

        public Pair(T1 a, T2 b) {
            this.a = a;
            this.b = b;
        }
    }
}

/*
5
1 0 0 0 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 1 0 0 1
 */