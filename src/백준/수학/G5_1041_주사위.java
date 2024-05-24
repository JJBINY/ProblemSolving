package 백준.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;


/**
 * G5_1041_주사위
 * 수학, 그리디
 */
public class G5_1041_주사위 {
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

    static Object solve(BufferedReader br) throws IOException {
        long N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] d = new int[6];
        for (int i = 0; i < 6; i++) {
            d[i] = parseInt(st.nextToken());
        }

        if(N==1){
            return Arrays.stream(d).sum() - Arrays.stream(d).max().getAsInt();
        }
        int a = Arrays.stream(d).min().getAsInt();

        long edge = getEdge(d);
        long corner = getCorner(d);

        long ans = 4 * corner;
        ans += 4 * edge;
        ans += 8 * (N - 2) * edge;
        ans += (N - 2) * (5 * N - 6) * a;
        return ans;
    }

    private static int getCorner(int[] d) {
        int corner = Integer.MAX_VALUE;
        corner = Math.min(corner, d[0] + d[1] + d[2]);
        corner = Math.min(corner, d[0] + d[1] + d[3]);
        corner = Math.min(corner, d[0] + d[2] + d[4]);
        corner = Math.min(corner, d[0] + d[3] + d[4]);
        corner = Math.min(corner, d[1] + d[2] + d[5]);
        corner = Math.min(corner, d[1] + d[3] + d[5]);
        corner = Math.min(corner, d[2] + d[4] + d[5]);
        return corner = Math.min(corner, d[3] + d[4] + d[5]);
    }

    private static int getEdge(int[] d) {
        int edge = MAX_VALUE;
        for (int i = 1; i < 5; i++) {
            edge = Math.min(edge, d[0] + d[i]);
        }
        edge = Math.min(edge, d[1] + d[2]);
        edge = Math.min(edge, d[1] + d[3]);
        edge = Math.min(edge, d[1] + d[5]);
        edge = Math.min(edge, d[2] + d[4]);
        edge = Math.min(edge, d[2] + d[5]);
        edge = Math.min(edge, d[3] + d[4]);
        edge = Math.min(edge, d[3] + d[5]);
        return edge = Math.min(edge, d[4] + d[5]);
    }
}