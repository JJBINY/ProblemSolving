package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;


/**
 *
 */
public class S3_1063_킹 {

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] king = getIdx(st.nextToken());
        int[] stone = getIdx(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int r = king[0];
        int c = king[1];
        int sr = stone[0];
        int sc = stone[1];


        Map<String, Integer> map = Map.of(
                "R", 0,
                "L", 1,
                "B", 2,
                "T", 3,
                "RT", 4,
                "LT", 5,
                "RB", 6,
                "LB", 7
        );
        int[] dr = {1, -1, 0, 0, 1, -1, 1, -1};
        int[] dc = {0, 0, -1, 1, 1, 1, -1, -1};
        for (int i = 0; i < N; i++) {
            Integer j = map.get(br.readLine());
            int nr = r + dr[j];
            int nc = c + dc[j];
            if (isOutOfBound(nr, nc)) continue;
            if (nr == sr && nc == sc) {
                int nsr = sr + dr[j];
                int nsc = sc + dc[j];
                if (isOutOfBound(nsr, nsc)) continue;
                sr = nsr;
                sc = nsc;
            }
            r = nr;
            c = nc;
        }
        StringBuilder sb = new StringBuilder();
        return sb.append((char)('A' + r)).append(c+1).append("\n").append((char)('A' + sr)).append(sc+1);
    }

    private static boolean isOutOfBound(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= 8 || nc >= 8;
    }

    static int[] getIdx(String s) {
        return new int[]{s.charAt(0) - 'A', s.charAt(1) - '1'};
    }

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
}