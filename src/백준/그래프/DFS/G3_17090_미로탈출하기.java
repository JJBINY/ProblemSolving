package 백준.그래프.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 17090 미로 탈출하기
 * DFS, DP
 */
public class G3_17090_미로탈출하기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, M;
    static char[][] arr;
    static boolean[][] escaped;
    static boolean[][] visited;

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());


        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        escaped = new boolean[N][M];
        visited = new boolean[N][M];


        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(i, j)) {
                    ans++;
                }
            }
        }

        System.out.println(ans);

    }

    static boolean dfs(int a, int b) {

        if (a < 0 || a >= N || b < 0 || b >= M) {
            return true;
        } else if (visited[a][b]) {
            return escaped[a][b];
        }
        visited[a][b] = true;

        int nr = a;
        int nc = b;

        switch (arr[a][b]) {
            case 'U':
                nr--;
                break;
            case 'R':
                nc++;
                break;
            case 'D':
                nr++;
                break;
            case 'L':
                nc--;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return escaped[a][b] = dfs(nr, nc);
    }
}