package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * S3_10973_모든_순열
 * 백트래킹
 */
public class S3_10973_모든_순열 {
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

    static int N;
    static StringBuilder res = new StringBuilder();

    static Object solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        func(0, 0, new StringBuilder());
        return res;
    }
    static void func(int depth, int visited, StringBuilder sb){
        if(depth == N){
            res.append(sb).append("\n");
            return;
        }
        for (int i = 1; i <= N ; i++) {
            if((visited & 1<<i)>0){
                continue;
            }
            func(depth + 1, visited | 1 << i, new StringBuilder(sb).append(i).append(" "));
        }
    }
}