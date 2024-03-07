package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 14890 경사로
 * 구현
 */
public class G3_14890_경사로 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int L = parseInt(st.nextToken());

        int arr[][] = new int[N][N];
        int arr2[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int height = parseInt(st.nextToken());
                arr[i][j] = height;
                arr2[j][i] = height;
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (passLine(N, L, arr[i])) {
                ans++;
            }

            if (passLine(N, L, arr2[i])) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static boolean passLine(int N, int L, int[] line) {
        boolean[] isRamp = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) {
                continue;
            } else if (line[i] + 1 == line[i + 1]) {
                for (int j = 0; j < L; j++) {
                    if (i - j < 0) return false;
                    if (line[i - j] != line[i] || isRamp[i - j]) {
                        return false;
                    }
                    isRamp[i - j] = true;
                }
            } else if (line[i] - 1 == line[i + 1]) {
                for (int j = 0; j < L; j++) {
                    if (i + 1 + j >= N) {
                        return false;
                    }
                    if (line[i + 1 + j] != line[i + 1]) {
                        return false;
                    }
                    isRamp[i + 1 + j] = true;
                }
            } else {
                return false;
            }
        }

        return true;
    }
}