package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 13422 도둑
 * 누적합, 투포인터, 슬라이딩 윈도우
 */
public class G4_13422_도둑 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken());
            int M = parseInt(st.nextToken());
            int K = parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            long[] arr = new long[N + M];
            for (int i = 1; i <= N; i++) {
                arr[i] = parseInt(st.nextToken());
                if(i<M) {
                    arr[N + i] = arr[i];
                }
            } //for i


            for (int i = 1; i < arr.length; i++) {
                arr[i] += arr[i - 1];
            }

            int ans = 0;
            for (int i = M; i < arr.length; i++) {
                if(arr[i] - arr[i - M] < K)ans++;
            }

            if(M==N){
                sb.append(ans > 0 ? 1 : 0).append("\n");
                continue;
            }

            sb.append(ans).append("\n");
        } //while T

        System.out.println(sb.toString());
    }


    private static void printArr(int[][] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print((arr[i][j] >= 0 ? " " : "") + arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}