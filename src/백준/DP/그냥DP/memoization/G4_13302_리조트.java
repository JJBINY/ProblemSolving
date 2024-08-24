package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 13302 리조트
 * DP
 */
public class G4_13302_리조트 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, M;
    static int[][] cost;
    static boolean[] isRest;

    static void solve(BufferedReader br) throws IOException {
        init(br);
        System.out.println(dfs(1, 0));
    }

    static int dfs(int d, int c) {
        if (d > N) return 0;
        else if (cost[d][c] > -1) return cost[d][c];
        else if (isRest[d]) return cost[d][c] = dfs(d + 1, c);

        cost[d][c] = dfs(d + 1, c) + 10000;
        cost[d][c] = Math.min(cost[d][c], dfs(d + 3, c + 1) + 25000);
        cost[d][c] = Math.min(cost[d][c], dfs(d + 5, c + 2) + 37000);
        if (c >= 3) {
            cost[d][c] = Math.min(cost[d][c], dfs(d + 1, c - 3));
        }

        return cost[d][c];
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        isRest = new boolean[N + 1];
        if(M>0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                isRest[parseInt(st.nextToken())] = true;
            }
        }
        cost = new int[N + 1][41];
        for (int[] ints : cost) {
            Arrays.fill(ints, -1);
        }
    }
}
