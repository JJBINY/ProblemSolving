package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 10836 여왕벌
 * 구현
 */
public class G4_10836_여왕벌 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = parseInt(st.nextToken());
        int N = parseInt(st.nextToken());

        int[] growth = new int[3];
        int[][] arr = new int[M][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                growth[j] = parseInt(st.nextToken());
            }

            for (int m = 0; m < M; m++) {
                if (growth[0]-- > 0) {
                    continue;
                } else if (growth[1]-- > 0) {
                    arr[M - 1 - m][0] +=  1;
                }else{
                    arr[M - 1 - m][0] += 2;
                }
            }

            for (int m = M; m < 2*M-1; m++) {
                if (growth[0]-- > 0) {
                    continue;
                } else if (growth[1]-- > 0) {
                    arr[0][m-M+1] += 1;
                }else{
                    arr[0][m-M+1] += 2;
                }
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                arr[i][j] = arr[i][j - 1];
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                arr[j][i] = Math.max(arr[j][i], arr[j - 1][i]);
            }
        }

        print(M, arr);
    }

    private static void print(int M, int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]+1).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}