package 백준.그래프.최단경로.플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 13424 비밀 모임
 * 최단경로, 플로이드 와샬
 */
public class G4_13424_비밀모임 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            while (T-->0) {
                solve(br);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        // init
        int[][] distances = new int[N][N];
        int MAX_VALUE = (int)1e6;
        for (int i = 0; i < N; i++) {
            Arrays.fill(distances[i], MAX_VALUE);
            distances[i][i] = 0;
        }

        // add paths
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken())-1;
            int b = parseInt(st.nextToken())-1;
            int c = parseInt(st.nextToken());
            distances[a][b] = c;
            distances[b][a] = c;
        }

        // add friends
        int K = parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] friends = new int[K];
        for (int i = 0; i < K; i++) {
            friends[i] = parseInt(st.nextToken())-1;
        }

        // find shortest path : floyd warshall
        for (int mid = 0; mid < N; mid++) {
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    if (distances[a][b] > distances[a][mid] + distances[mid][b]) {
                        distances[a][b] = distances[a][mid] + distances[mid][b];
                    }
                }
            }
        }

        int minDist = MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int totalDist = 0;
            for (int friend : friends) {
                totalDist += distances[i][friend];
            }
            if(totalDist<minDist){
                minDist = totalDist;
                ans = i + 1;
            }
        }
        System.out.println(ans);
    }
}