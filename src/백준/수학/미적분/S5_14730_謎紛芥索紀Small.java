package 백준.수학.미적분;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S5 14730 謎紛芥索紀 (Small)
 * 수학, 미적분
 */
public class S5_14730_謎紛芥索紀Small {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        int ans = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int C = parseInt(st.nextToken());
            int K = parseInt(st.nextToken());
            ans += C * K;
        }
        System.out.println(ans);


    }
}