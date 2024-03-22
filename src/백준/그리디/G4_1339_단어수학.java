package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G4 1339 단어수학
 * 그리디
 */
public class G4_1339_단어수학 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] alphas = new int[26];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int a = 1;
            for (int j = word.length()-1; j >=0;j--){
                alphas[word.charAt(j) - 'A'] += a;
                a*=10;
            }
        }

        Arrays.sort(alphas);
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += alphas[25 - i] * (9 - i);
        }
        System.out.println(ans);
    }
}