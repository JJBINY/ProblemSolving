package 백준.수학.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G5_7868_해밍_수열
 * 수학, 정수론, 정렬
 */
public class G5_7868_해밍_수열 {
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

    /*
    2^60 = 1024^6 > 10^18
     */

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int seq = Integer.parseInt(st.nextToken());

        List<Long> list = new ArrayList<>();

        for (int i = 0; i <= 60; i++) {
            for (int j = 0; j <= 60; j++) {
                for (int k = 0; k <= 60; k++) {
                    list.add((long) (Math.pow(a, i) * Math.pow(b, j) * Math.pow(c, k)));
                }
            }
        }
        list.sort(Long::compareTo);
        return list.get(seq);
    }
}