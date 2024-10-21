package 백준.자료구조.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


/**
 * S4_1269_대칭_차집합
 * 자료구조, Set
 */
public class S4_1269_대칭_차집합 {
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

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nA = Integer.parseInt(st.nextToken());
        int nB = Integer.parseInt(st.nextToken());
        Set<Integer> A = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
        Set<Integer> B = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toSet());

        Set<Integer> AUB = new HashSet<>();
        AUB.addAll(A);
        AUB.addAll(B);

        int duplicated = A.size() + B.size() - AUB.size();
        return A.size() + B.size() - 2 * duplicated;
    }
}