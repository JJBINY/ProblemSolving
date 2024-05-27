package 백준.그래프.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * S1_20364_부동산다툼
 * 트리
 */
public class S1_20364_부동산다툼 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int Q = parseInt(st.nextToken());

        boolean[] visited = new boolean[N+1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            String bin = Integer.toBinaryString(parseInt(br.readLine()));
            int x = 0;
            for (int j = 0; j < bin.length(); j++) {
                x *= 2;
                if(bin.charAt(j) == '1') {
                    x += 1;
                }
                if (visited[x]){
                    sb.append(x).append("\n");
                    break;
                }
            }
            if(!visited[x]){
                visited[x] = true;
                sb.append(0).append("\n");
            }
        }

        return sb;
    }
}