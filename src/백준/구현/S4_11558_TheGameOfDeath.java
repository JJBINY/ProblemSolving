package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

/**
 * S4 11558 The Game of Death
 * 시뮬레이션, 구현, 그래프
 */
public class S4_11558_TheGameOfDeath {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int T = parseInt(br.readLine());
            while (T-- > 0) {
                int N = parseInt(br.readLine());
                int[] edges = new int[N + 1];
                for (int i = 1; i <= N; i++) {
                    edges[i] = parseInt(br.readLine());
                }

                int cnt = 0;
                int now = 1;
                while (true) {
                    now = edges[now];
                    cnt++;

                    if (now == N) {
                        sb.append(cnt).append("\n");
                        break;
                    }

                    if (cnt > N) {
                        sb.append(0).append("\n");
                        break;
                    }
                }

            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
