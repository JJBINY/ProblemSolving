package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * S1_25602_캔_주기
 * 브루트포스
 */
public class S1_25602_캔_주기 {
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

    static int N, K, ans;
    static int[] A;
    static int[][] R, M;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = new int[K][N];
        for (int i = 0; i < K; i++) {
            R[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        M = new int[K][N];
        for (int i = 0; i < K; i++) {
            M[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        func(0, 0);
        return ans;
    }

    static void func(int day, int res){
        if(day == K){
            ans = Math.max(ans, res);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(A[i] == 0) continue;
            A[i]--;
            for (int j = 0; j < N; j++) {
                if(A[j] == 0) continue;
                A[j]--;
                func(day+1, res+R[day][i] + M[day][j]);
                A[j]++;
            }
            A[i]++;
        }
    }
}