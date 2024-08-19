package 백준.그래프.트리.MST;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * G1_17472_다리_만들기_2
 * BFS, Union-Find, MST
 */
public class G1_17472_다리_만들기_2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, M;

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        int[][] arr = new int[N][];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        Island.findAll(arr);
        Island.makeEdges();
        System.out.println(Island.makeMST()); // print answer
//
//		System.out.println(Island.islands);
//		System.out.println(Island.islands.size());
    }

    static class Island {
        private static int[] dr = {-1, 1, 0, 0};
        private static int[] dc = {0, 0, -1, 1};
        private static int[] indexes;
        public static Set<Integer> islands;
        public static List<Edge> edges;

        public static Set<Integer> findAll(int[][] arr) {
            indexes = IntStream.range(0, N * M).toArray();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 0) continue;

                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                        if (arr[nr][nc] == 1) {
                            union(getIdx(i, j), getIdx(nr, nc));
                        }
                    }
                }
            }

            return islands = IntStream.range(0, N * M)
                    .filter(idx -> arr[idx / M][idx % M] == 1)
                    .filter(idx -> idx == find(idx))
                    .boxed()
                    .collect(Collectors.toSet());
        }

        public static List<Edge> makeEdges() {
            edges = new ArrayList<>();

            // make edges
            IntStream.range(0, N * M).forEach(idx -> {

                int sr = idx / M;
                int sc = idx % M;
                if (Island.isIsland(sr, sc) != -1) {
                    int startIsland = Island.find(idx);
//					System.out.println("si =" +startIsland);
                    for (int d = 0; d < 4; d++) {
                        Queue<Pair<int[], Integer>> q = new ArrayDeque<>();
                        q.offer(new Pair<>(new int[]{sr, sc}, -1));

                        boolean[][] visited = new boolean[N][M];
                        visited[sr][sc] = true;

                        Set<Integer> iSet = new HashSet<>();
                        iSet.add(startIsland);

                        while (!q.isEmpty()) {
                            int r = q.peek().v1[0];
                            int c = q.peek().v1[1];
                            int cost = q.poll().v2;


                            int island = Island.isIsland(r, c);
                            if (island > -1 && (r != sr || c != sc)) { // 섬이고 시작점과 다른 경우
                                if (cost < 2) continue;
                                // 이미 방문한 섬 pass
                                if (iSet.contains(island)) continue;
                                iSet.add(island);
                                Edge e = new Edge(startIsland, island, cost);
                                e.d = d;
                                edges.add(e);
                                continue; // 다른 섬에 닿으면 해당 분기 탐색 중단
                            }


                            int nr = r + dr[d];
                            int nc = c + dc[d];

                            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                            if (visited[nr][nc]) continue;
                            visited[nr][nc] = true;

                            q.offer(new Pair<>(new int[]{nr, nc}, cost + 1));


                        } // while q
                    } // 상하좌우 한 방향으로만 이동; 이동 방향 고정
                } // if start is Island
            });

            return edges;
        }

        public static int makeMST() {
            Map<Integer, Integer> map = new HashMap<>();
            int key = 0;
            Iterator<Integer> iter = islands.iterator();
            while (iter.hasNext()) {
                map.put(iter.next(), key++);
            }

            int[] dset = IntStream.range(0, islands.size()).toArray();

            int cost = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
            pq.addAll(edges);
            while (!pq.isEmpty()) {
                Edge e = pq.poll();
                int i1 = map.get(e.a);
                int i2 = map.get(e.b);
                if (DisjointSet.find(i1, dset) == DisjointSet.find(i2, dset)) {
                    continue;
                }
                DisjointSet.union(i1, i2, dset);
                cost += e.cost;
//				System.out.println(e);
//				System.out.println(cost);
            }

//            System.out.println("Arrays.toString(dset) = " + Arrays.toString(dset));

            return Arrays.stream(dset).map(i -> DisjointSet.find(i, dset)).distinct().count() == 1 ? cost : -1;
        }

        static int isIsland(int r, int c) {
            int idx = find(getIdx(r, c));
            return islands.contains(idx) ? idx : -1;
        }

        static int getIdx(int r, int c) {
            return r * M + c;
        }

        static void union(int a, int b) {
            DisjointSet.union(a, b, indexes);
        }

        static int find(int x) {
            return DisjointSet.find(x, indexes);
        }
    }

    static class DisjointSet {
        static void union(int a, int b, int[] arr) {
            a = find(a, arr);
            b = find(b, arr);
            if (a < b) {
                arr[b] = a;
            } else {
                arr[a] = b;
            }
        }

        static int find(int x, int[] arr) {
            if (x == arr[x]) {
                return x;
            }
            return arr[x] = find(arr[x], arr);
        }
    }

    static class Pair<A, B> {
        A v1;
        B v2;

        public Pair(A v1, B v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    static class Edge {
        int a;
        int b;
        int cost;
        int d; // 디버깅용

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("a = %d, b = %d, cost = %d, d = %d", a, b, cost, d);
        }
    }
}