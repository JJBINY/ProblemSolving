package 백준.DP.LCS최장공통부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P5_7620_편집거리
 * DP, 편집거리, 레벤슈타인 거리, 비트필드 압축
 */
public class P5_7620_편집거리 {

    static Object solve(BufferedReader br) throws IOException {
        String A = br.readLine();
        String B = br.readLine();

        int n = A.length();
        int m = B.length();

        int[][] dp = new int[2][m + 1];
        BitMatrix2Bit trace = new BitMatrix2Bit(17001, 17001);
        Map<String, Integer> opMap = Map.of(
                "a", 0,
                "d", 1,
                "m", 2,
                "c", 3
        );

        // init
        for (int i = 1; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            int cur = i % 2;
            int prev = (i + 1) % 2;
            dp[cur][0] = i;
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[cur][j] = dp[prev][j - 1];
                    trace.set(i, j, opMap.get("c")); // COPY
                } else {
                    dp[cur][j] = 1 + min(dp[cur][j - 1], dp[prev][j], dp[prev][j - 1]);

                    if (dp[cur][j] == dp[prev][j - 1] + 1) {
                        trace.set(i, j, opMap.get("m")); // MODIFY
                    } else if (dp[cur][j] == dp[prev][j] + 1) {
                        trace.set(i, j, opMap.get("d")); // DELETE
                    } else {
                        trace.set(i, j, opMap.get("a")); // ADD
                    }
                }
            }
        }

        return getEditScript(n, m, trace, B, A);
    }

    private static String getEditScript(int n, int m, BitMatrix2Bit trace, String B, String A) {
    /*
xabzdey
abcde
     */
        String[] cmdMap = {"a", "d", "m", "c"};
        StringBuilder sb = new StringBuilder();
        int i = n;
        int j = m;

        while (i > 0 || j > 0) {
            int op = trace.get(i, j);
            String cmd = cmdMap[op];
//            System.out.println(i+", " + j+", " + cmd);

            sb.append("\n");
            if (cmd.equals("c") || cmd.equals("m")) {
                i--;
                j--;
                sb.append(B.charAt(j));
            } else if (cmd.equals("d")) {
                i--;
                sb.append(A.charAt(i));
            } else {
                j--;
                sb.append(B.charAt(j));
            }
            sb.append(" ").append(cmd);
        }

        return sb.reverse().substring(0, sb.length() - 1);
    }

    private static int min(int... args) {
        int result = args[0];
        for (int arg : args) {
            result = Math.min(result, arg);
        }
        return result;
    }

    public static class BitMatrix2Bit {
        private final int rows, cols, intsPerRow;
        private final int[][] data;

        public BitMatrix2Bit(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.intsPerRow = (cols + 15) / 16;
            this.data = new int[rows][intsPerRow];
        }

        // 0: a, 1: d, 2: m, 3: c
        public void set(int i, int j, int op) {
            assert op < 4 && op >= 0;
            int wordIdx = j / 16;
            int bitOffset = (j % 16) * 2;
            claer(i, wordIdx, bitOffset);
            data[i][wordIdx] |= op << bitOffset;
        }

        private void claer(int i, int wordIdx, int bitOffset) {
            data[i][wordIdx] &= ~(0011 << bitOffset);
        }

        public int get(int i, int j) {
            if(i==0){
                return 0; // a
            } else if (j==0) {
                return 1; // d
            }
            int wordIdx = j / 16;
            int bitOffset = (j % 16) * 2;
            return (data[i][wordIdx] >> bitOffset) & 3;
        }
    }


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
}