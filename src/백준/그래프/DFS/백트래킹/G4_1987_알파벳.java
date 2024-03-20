package 백준.그래프.DFS.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G4_1987_알파벳
 * DFS, 백트래킹
 */
public class G4_1987_알파벳 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int R, C;
    static String[] arr;
    static boolean[] alphabet = new boolean[27];
    static int ans;

    static void solve(BufferedReader br) throws IOException {
        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        arr = new String[R];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine();
        }
        alphabet[arr[0].charAt(0) - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(ans);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfs(int r, int c, int cnt) {
        ans = Math.max(ans, cnt);
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            int alpha = arr[nr].charAt(nc) - 'A';
            if (alphabet[alpha]) continue;
            alphabet[alpha] = true;
            dfs(nr, nc, cnt + 1);
            alphabet[alpha] = false;
        }

    }
}