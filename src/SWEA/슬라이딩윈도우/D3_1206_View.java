package SWEA.슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * D3 1206 View
 * 슬라이딩윈도우
 */
public class D3_1206_View {

    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            int T = parseInt(br.readLine());
            int T = 10;
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

    static Object solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[] heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = parseInt(st.nextToken());
        }

        int result = 0;
        for (int i = 2; i < N-2; i++) {
            int cnt = Integer.MAX_VALUE;
            for (int j = -2; j <=2 ; j++) {
                if(j==0) continue;
                cnt = Math.min(cnt, heights[i] - heights[i+j]);
            }
            if(cnt>0){
                result += cnt;
            }
        }
        return result;
    }
}