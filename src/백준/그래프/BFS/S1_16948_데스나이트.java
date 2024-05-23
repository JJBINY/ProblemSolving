package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * S1 16948 데스나이트
 * BFS
 */
public class S1_16948_데스나이트 {
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

    static Object solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] s = {parseInt(st.nextToken()), parseInt(st.nextToken())};
        int[] e = {parseInt(st.nextToken()), parseInt(st.nextToken())};
        int[][] arr = new int[N][N];
        arr[s[0]][s[1]] = 1;

        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(s);

        int[] dr = {-2, -2, 0, 0, 2, 2};
        int[] dc = {-1, 1, -2, 2, -1, 1};
        while (!dq.isEmpty()){
            int r = dq.peek()[0];
            int c = dq.poll()[1];
            if(r == e[0] && c == e[1]){
                return arr[r][c] - 1;
            }

            for (int i = 0; i < 6; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr<0||nr>=N||nc<0||nc>=N) continue;
                if(arr[nr][nc] > 0) continue;
                arr[nr][nc] = arr[r][c] + 1;
                dq.add(new int[]{nr, nc});
            }
        }
        return -1;
    }

}