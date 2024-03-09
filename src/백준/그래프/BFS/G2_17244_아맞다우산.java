package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 17244 아맞다우산
 * BFS, 비트마스킹
 */
public class G2_17244_아맞다우산 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken()); // col
        int M = parseInt(st.nextToken()); // row

        char[][] arr = new char[M][N];
        Pair<Integer, Integer> start = null;
        Pair<Integer, Integer> end = null;
        char thing = '0';
        for (int i = 0; i < M; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 'S'){
                    start = new Pair<>(i, j);
                }else if(arr[i][j] == 'X'){
                    arr[i][j] = thing++;
                }else if(arr[i][j] == 'E'){
                    end = new Pair<>(i, j);
                }
            }
        }
        int ansState = 0;
        for (int i = 0; i < thing -'0'; i++) {
            ansState |= 1 << i;
        }


        //
        Queue<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> queue = new LinkedList<>();
        queue.offer(new Pair<>(start, new Pair<>(0, 0)));

        boolean[][][] visited = new boolean[M][N][32];
        visited[start.a][start.b][0] = true;

        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};
        while (!queue.isEmpty()){
            Pair<Integer, Integer> now = queue.peek().a;
            int seq = queue.peek().b.a;
            int state = queue.poll().b.b;

            if(now.equals(end)){
                if(state == ansState){
                    System.out.println(seq);
                    return;
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.a + dr[i];
                int nc = now.b + dc[i];

                if(arr[nr][nc] == '#') continue;

                int ns = state;
                if(arr[nr][nc] >= '0' && arr[nr][nc] < '5'){
                    ns |= 1 << (arr[nr][nc] - '0');
                }

                if (visited[nr][nc][ns]) continue;
                visited[nr][nc][ns] = true;

                queue.offer(new Pair<>(new Pair<>(nr, nc), new Pair<>(seq + 1, ns)));
            }
        }
    }

    static class Pair<A,B>{
        A a;
        B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Pair<A, B> pair = (Pair<A,B>) o;
            if (!a.equals(pair.a)) return false;
            return b.equals(pair.b);
        }
    }
}