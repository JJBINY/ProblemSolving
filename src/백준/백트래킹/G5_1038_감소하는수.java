package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;


/**
 * G5_1038_감소하는 수
 * 조합, 백트래킹
 */
public class G5_1038_감소하는수 {
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

    static Set<Long> set = new HashSet<>();

    static Object solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        combi(9, 0);
        List<Long> results = set.stream().sorted().collect(Collectors.toList());
        try {
            return results.get(N);
        }catch (IndexOutOfBoundsException e){
            return -1;
        }
    }

    static void combi(int n, long res) {
        if (!set.add(res) || n == -1) {
            return;
        }

        for (int i = n; i >= 0; i--) {
            combi(i - 1, res * 10 + i);
        }
    }
}