package SWEA.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * 시간: 138ms
 * 메모리: 22,040KB
 */
public class 모의_2105_디저트_카페 {

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append("#").append(i).append(" ");
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int result, N;
    static int[][] dessert;
    static boolean[] eaten = new boolean[101];
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};
    static int[] dCnt1 = {1, 0, -1, 0};
    static int[] dCnt2 = {0, 1, 0, -1};

    static Object solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        dessert = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dessert[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 1; j < N - 1; j++) {
//                System.out.println(String.format("from %d %d", i, j));
                eaten[dessert[i][j]] = true;
                dfs(i+1, j+1, 0, 1, 0, 1); // i,j부터 우하향으로 탐색 시작
                eaten[dessert[i][j]] = false;
            }
        }
        return result == 0 ? -1 : result;
    }

    static void dfs(int r, int c, int d, int cnt1, int cnt2, int dessertCnt) {
//        System.out.println(String.format("%d %d %d %d %d = %d", r, c, d, cnt1, cnt2, dessertCnt));
        if (d == 3 && (cnt1+cnt2) == 0) { //한바퀴 돈 경우
            result = Math.max(result, dessertCnt);
//            System.out.println("dessertCnt = " + dessertCnt);
            return;
        }

        if(cnt1 <0 || cnt2 <0){
            return;
        }else if (eaten[dessert[r][c]]) {
//            System.out.println(dessert[r][c] + " already eaten");
            return;
        }
        eaten[dessert[r][c]] = true;

        for (int i = 0; i < 2; i++) {
            int nd = d + i; // d, d+1 -> 방향 그대로 or 방향 꺽어서 진행
            if (nd > 3) continue;

            int nr = r + dr[nd];
            int nc = c + dc[nd];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            dfs(nr, nc, nd, cnt1 + dCnt1[nd], cnt2 + dCnt2[nd], dessertCnt + 1);
        }

        eaten[dessert[r][c]] = false;
    }
}