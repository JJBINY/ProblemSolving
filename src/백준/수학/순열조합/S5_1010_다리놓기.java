package 백준.수학.순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S5 1010 다리 놓기
 * 수학, 조합, DP
 */
public class S5_1010_다리놓기 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[][] combination = new int[30][30];
        for (int i = 0; i < 30; i++) {
            combination[i][i] = 1;
            combination[i][0] = 1;
        }
        for (int i = 2; i < 30; i++) {
            for (int j = 1; j < 30; j++) {
                combination[i][j] = combination[i - 1][j - 1] + combination[i - 1][j];
            }
        }

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken());
            int M = parseInt(st.nextToken());
            sb.append(combination[M][N]).append("\n");
        }
        br.close();
        System.out.println(sb.toString());
    }
    
}

