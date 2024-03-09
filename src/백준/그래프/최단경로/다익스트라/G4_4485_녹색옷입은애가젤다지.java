package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 4485 녹색 옷 입은 애가 젤다지
 * 최단경로, 다익스트라
 */
public class G4_4485_녹색옷입은애가젤다지 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int iter = 0;
        while (true) {
            int N = parseInt(br.readLine());
            iter++;
            if(N == 0) return;

            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = parseInt(st.nextToken());
                }
            }

            int[][] distances = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(distances[i], Integer.MAX_VALUE);
            }

            PriorityQueue<Pair<Pair<Integer, Integer>, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.b));
            pq.offer(new Pair<>(new Pair<>(0, 0), arr[0][0]));
            int[] dr = new int[]{1, -1, 0, 0};
            int[] dc = new int[]{0, 0, -1, 1};
            while (!pq.isEmpty()) {
                Pair<Integer, Integer> now = pq.peek().a;
                int dist = pq.poll().b.intValue();

                if (dist > distances[now.a][now.b]) continue;

                for (int i = 0; i < 4; i++) {
                    int nr = now.a + dr[i];
                    int nc = now.b + dc[i];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }

                    int nd = dist + arr[nr][nc];
                    if (nd < distances[nr][nc]) {
                        distances[nr][nc] = nd;
                        pq.offer(new Pair<>(new Pair<>(nr, nc), nd));
                    }
                }
            }


            System.out.println(String.format("Problem %d: %d", iter, distances[N - 1][N - 1]));
        }
    }

    static class Pair<A, B> {
        A a;
        B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair<?, ?>)) return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            if (a != null ? !a.equals(pair.a) : pair.a != null) return false;
            if (b != null ? !b.equals(pair.b) : pair.b != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = a != null ? a.hashCode() : 0;
            result = 31 * result + (b != null ? b.hashCode() : 0);
            return result;
        }
    }
}