package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;

/**
 * G3 14391 종이조각
 * 브루트포스, 비트마스킹, (백트래킹)
 */
public class G3_14391_종이조각 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, M, ans;
    static int[][] arr;
    static int[] dp = new int[1 << 17];

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        Arrays.fill(dp, -1);
        func(0, 0, 0);
        System.out.println(ans);
    }

    static void func(int state, int score, int idx) {
        if(dp[state] >= score){
            return;
        }else if (idx == N * M) {
            ans = Math.max(ans, score);
            return;
        } else if (isVisited(state,idx)) {
            func(state, score, idx + 1);
            return;
        }
        dp[state] = score;
        int r = idx / M;
        int c = idx % M;

        // 세로
        int num = 0;
        int ns = state;
        for (int nr = r; nr < N; nr++) {
            if (isVisited(ns, nr * M + c)) {
                break;
            }
            ns |= 1 << nr * M + c;
            num += arr[nr][c];
            func(ns, score + num, idx + 1);
            num *= 10;
        }
        // 가로
        num = 0;
        ns = state;
        for (int nc = c; nc < M; nc++) {
            if (isVisited(ns, r * M + nc)) {
                break;
            }
            ns |= 1 << r * M + nc;
            num += arr[r][nc];
            func(ns, score + num, idx + 1);
            num *= 10;
        }

    }
    static boolean isVisited(int state, int idx){
        return (state & 1 << idx) > 0;
    }
}
