package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * S2 3085 사탕게임
 * 브루트포스, 구현
 */
public class S2_3085_사탕게임 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N;
    static char[][] arr;

    static Object solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        char[] candies = {'C', 'P', 'Z', 'Y'};
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // O(N^4)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) { // 4
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    swap(i, j, nr, nc);
                    for (char candy : candies) { //4
                        ans = Math.max(ans, func(candy)); //N^2
                    }
                    swap(i, j, nr, nc);
                }
            }
        }

        return ans;
    }

    static void swap(int r1, int c1, int r2, int c2){
        char t = arr[r1][c1];
        arr[r1][c1] = arr[r2][c2];
        arr[r2][c2] = t;
    }

    static int func(char candy) {
        int res = 0;

        for (int i = 0; i < N; i++) {
            int r = 0;
            int c = 0;
            for (int j = 0; j < N; j++) {
                if (arr[j][i] == candy) {
                    r++;
                } else {
                    res = Math.max(res, r);
                    r = 0;
                }
                if (arr[i][j] == candy) {
                    c++;
                } else {
                    res = Math.max(res, c);
                    c = 0;
                }
            } // for j
            res = Math.max(res, r);
            res = Math.max(res, c);
        } // for i

        return res;
    }
}
