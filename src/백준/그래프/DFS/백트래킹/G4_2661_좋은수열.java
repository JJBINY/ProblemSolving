package 백준.그래프.DFS.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G4_2661_좋은수열
 */
public class G4_2661_좋은수열 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N;

    static void solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        find(new StringBuilder());
    }

    static boolean find(StringBuilder arr) {
        if (!isGood(arr)) {
            return false;
        }
        if (arr.length() == N) {
            System.out.println(arr.toString());
            return true;
        }

        for (int i = 1; i <= 3; i++) {
            if (find(arr.append(i))) {
                return true;
            }
            arr.deleteCharAt(arr.length() - 1);
        }
        return false;
    }

    private static boolean isGood(StringBuilder arr) {
        int L = arr.length();

        for (int i = 1; i <= L / 2; i++) {
            boolean isGood = false;
            for (int j = 0; j < i; j++) {
                if (arr.charAt(L - 1 - i - j) != arr.charAt(L - 1 - j)) {
                    isGood = true;
                    break;
                }
            }
            if (!isGood) {
                return false;
            }
        }

        return true;
    }
}