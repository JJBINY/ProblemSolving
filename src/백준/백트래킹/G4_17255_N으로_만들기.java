package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * G4_17255_N으로_만들기
 * 백트래킹, DP, Map
 */
public class G4_17255_N으로_만들기 {
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

    static Map<String, Integer> dp = new HashMap<>();

    static Object solve(BufferedReader br) throws IOException {
        return backtrack(br.readLine());
    }

    static int backtrack(String N) {
        if (dp.containsKey(N)) {
//            System.out.println("N = " + N + ", dp.get(N) = " + dp.get(N));
            return dp.get(N);
        }else if(N.length() == 1){
            return 1;
        }

        String rCut = N.substring(0, N.length() - 1);
        int cnt = backtrack(rCut);
        String lCut = N.substring(1);
        if (!rCut.equals(lCut)) {
            cnt += backtrack(lCut);
        }

        dp.put(N, cnt);
//        System.out.println("N = " + N + ", dp.get(N) = " + dp.get(N));

        return dp.get(N);
    }
}