package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * S2 15665 N과M 11
 * 백트래킹
 */
public class S2_15665_N과M_11 {
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

    static int N, M;
    static int[] arr;
    static StringBuilder res = new StringBuilder();
    static Set<String> set = new HashSet<>();

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();
        func(0, new StringBuilder());
        return res;
    }

    static void func(int depth, StringBuilder sb) {
        if (!set.add(sb.toString())) return;
        else if (depth == M) {
            res.append(sb.toString()).append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            func(depth + 1, new StringBuilder(sb).append(arr[i]).append(" "));
        }
    }
}