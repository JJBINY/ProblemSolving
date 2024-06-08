package 백준.수학.수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;


/**
 * G4_16282_BlackChain
 * 수학, 패턴찾기, 수열
 */
public class G4_16282_BlackChain {
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

    static Object solve(BufferedReader br) throws IOException {
        long n = Long.parseLong(br.readLine());
        long pow = 4;
        int k = 1;
        for (; (k + 1) * pow - 1 < n; k++, pow *= 2) {}
        return k;
    }
}