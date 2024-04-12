package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 1062 가르침
 * 브루트포스, 비트마스크, 백트래킹
 */
public class G4_1062_가르침 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, K;
    static String[] words;
    static int[] alphas;
    static int ans;

    static void solve(BufferedReader br) throws IOException {
        init(br);
        if (K < 5) {
            System.out.println(0);
            return;
        }

        alphas = new int[N];
        for (int i = 0; i < N; i++) {
            for(char c : words[i].toCharArray()){
                alphas[i] |= (1 << c - 'a');
            }
        }
        int val = 0;
        val |= 1 << 'a' - 'a';
        val |= 1 << 'n' - 'a';
        val |= 1 << 't' - 'a';
        val |= 1 << 'i' - 'a';
        val |= 1 << 'c' - 'a';
        combi(val, 5, 1);
        System.out.println(ans);
    }

    static void combi(int val, int cnt, int depth) {
        if (depth > 26 || cnt == K) {
            int rst = 0;
            for (int i = 0; i < N; i++) {
                if ((alphas[i] | val) == val) {
                    rst++;
                }
            }
            ans = Math.max(ans, rst);
            return;
        }

        if ((val & 1<<depth) ==0){
            combi(val | 1 << depth, cnt + 1, depth + 1);
        }
        combi(val, cnt, depth + 1);
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
    }
}
