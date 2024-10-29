package 백준.DP.배낭문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G4__14863_서울에서_경산까지
 * DP, 배낭문제...>
 */
public class G4__14863_서울에서_경산까지 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    - 각 구간에서 무조건 1개 간선을 선택해서 사용해야 함
    - 가능한 경우가 무조건 존재함이 보장됨
    => 냅색 응용하면 되지 않을까?
     */
    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Pair[][] edges = new Pair[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 2; j++) {
                int w = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edges[i][j] = new Pair(w, v);
            }
        }

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        for (int i = 1; i <= N; i++) {
            Pair[] edge = edges[i];
            for (int j = 1; j <= K; j++) {
                for (int k = 0; k < 2; k++) {
                    if (j < edge[k].weight) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - edge[k].weight] + edge[k].value);
                }
            }
        }
        return dp[N][K];
    }

    static class Pair {
        int weight;
        int value;

        public Pair(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}

/*
3 1650
500 200 200 100
800 370 300 120
700 250 300 90

3 600
500 150 200 1000
100 835 200 324
200 125 300 900
 */