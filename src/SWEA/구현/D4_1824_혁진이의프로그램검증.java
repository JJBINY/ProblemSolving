package SWEA.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1824. 혁진이의 프로그램 검증 D4
 * 구현, DFS, 백트래킹
 */
public class D4_1824_혁진이의프로그램검증 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = Integer.parseInt(br.readLine());
            //			int T  = 10;
            for (int t = 1; t <= T; t++) {
                ans.append("#").append(t).append(" ")
                        .append(solve(br))
                        .append("\n");
            }
            System.out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int R, C;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1}; //상하좌우
    static char[][] arr;
    static boolean[][][][] visited;
    static boolean result;

    public static Object solve(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        visited = new boolean[R][C][4][16];
        result = false;
        simulate(0, 0, 3, 0);
        return result ? "YES" : "NO";
    }

    static void simulate(int r, int c, int dir, int mem) {
        if (visited[r][c][dir][mem]) return;
        visited[r][c][dir][mem] = true;

        char cmd = arr[r][c];
        if (cmd == '?') {
            for (int i = 0; i < 4; i++) {
                visited[r][c][i][mem] = true;
            }
            for (int i = 0; i < 4; i++) {
                simulate(nr(r, i), nc(c, i), i, mem);
            }
            return;
        } else if (cmd == '@') {
            result = true;
            return;
        } else if (cmd == '^') {
            dir = 0;
        } else if (cmd == 'v') {
            dir = 1;
        } else if (cmd == '<') {
            dir = 2;
        } else if (cmd == '>') {
            dir = 3;
        } else if (cmd == '_') {
            if (mem == 0) {
                dir = 3;
            } else {
                dir = 2;
            }
        } else if (cmd == '|') {
            if (mem == 0) {
                dir = 1;
            } else {
                dir = 0;
            }
        } else if (cmd == '+') {
            mem = (mem + 1) % 16;
        } else if (cmd == '-') {
            mem = (mem + 15) % 16;
        } else if (cmd == '.') {
        } else {
            mem = cmd - '0';
        }

        simulate(nr(r, dir), nc(c, dir), dir, mem);
    }

    static int nr(int r, int dir) {
        return (r + dr[dir] + R) % R;
    }

    static int nc(int c, int dir) {
        return (c + dc[dir] + C) % C;
    }
}
