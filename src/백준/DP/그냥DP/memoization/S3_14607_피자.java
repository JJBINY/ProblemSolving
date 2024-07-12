package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * S3_14607_피자
 * 수학, DP
 */
public class S3_14607_피자 {
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

    static Map<Long, Long> map = new HashMap<>();

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        map.put(1l, 0l);
        map.put(2l, 1l);

        return func(N);
    }

    static long func(long n) {
        if (!map.containsKey(n)) {
            long a, b;
            a = b = n / 2;
            if (n % 2 == 1) {
                a++;
            }
            map.put(n, a * b + func(a) + func(b));
        }

        return map.get(n);
    }
}