package 백준.DP.LCS최장공통부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * D1_18438_LCS_5
 * DP, 분할정복, 히르쉬버그
 * 언어에 따른 추가 메모리 제공 없는 이슈로 C로 변환 후 제출
 */
public class D1_18438_LCS_5 {

    static Object solve(BufferedReader br) throws IOException {
        String A = br.readLine();
        String B = br.readLine();
        String lcs = Hirschberg.computeLCS(A, B);
        StringBuilder sb = new StringBuilder();
        return sb.append(lcs.length()).append("\n").append(lcs);
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

    public static class Hirschberg {
        public static String computeLCS(String A, String B) {
            StringBuilder sb = new StringBuilder();
            computeLCS(A, B, sb);
            return sb.toString();
        }


        public static void computeLCS(String A, String B, StringBuilder sb) {
            if (B.isEmpty()) {
                return;
            } else if (A.length() == 1) {
                if (B.contains(A)) {
                    sb.append(A);
                }
                return;
            }

            int mid = A.length() >> 1;
            String leftA = A.substring(0, mid);
            String rightA = A.substring(mid);

            int[] leftPrefix = computeLcsLengths(leftA, B);
            int[] rightSuffix = computeLcsLengths(StringUtil.reverse(rightA), StringUtil.reverse(B));

            int splitAt = findSplitIdx(leftPrefix, rightSuffix, B.length());

            String leftB = B.substring(0, splitAt);
            String rightB = B.substring(splitAt);

            computeLCS(leftA, leftB, sb);
            computeLCS(rightA, rightB, sb);
        }

        private static int[] computeLcsLengths(String A, String B) {
            int m = B.length();
            int[][] dp = new int[2][m + 1];

            for (int i = 0; i < A.length(); i++) {
                int cur = i % 2;
                int prev = (i + 1) % 2;

                for (int j = 0; j < m; j++) {
                    if (A.charAt(i) == B.charAt(j)) {
                        dp[cur][j + 1] = dp[prev][j] + 1;
                    } else {
                        dp[cur][j + 1] = Math.max(dp[cur][j], dp[prev][j + 1]);
                    }
                }
            }
            return dp[(A.length() - 1) % 2];
        }

        // prefix + suffix 합이 최대가 되는 분할 지점 k를 찾는다
        private static int findSplitIdx(int[] prefix, int[] suffix, int len) {
            int score = Integer.MIN_VALUE;
            int idx = 0;

            for (int k = 0; k <= len; k++) {
                int cur = prefix[k] + suffix[len - k];
                if (cur > score) {
                    score = cur;
                    idx = k;
                }
            }

            return idx;
        }
    }

    static class StringUtil {
        public static String reverse(String str) {
            char[] arr = str.toCharArray();
            for (int l = 0, r = arr.length - 1; l < r; l++, r--) {
                swap(arr, l, r);
            }
            return String.valueOf(arr);
        }

        private static void swap(char[] arr, int l, int r) {
            char tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
}