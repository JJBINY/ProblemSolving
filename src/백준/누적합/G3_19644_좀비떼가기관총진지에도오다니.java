package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3_19644_좀비떼가기관총진지에도오다니
 * 그리디, 누적합
 */
public class G3_19644_좀비떼가기관총진지에도오다니 {
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
        int L = parseInt(br.readLine()); // ~3x10^6
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ml = parseInt(st.nextToken());
        int mk = parseInt(st.nextToken());
        int C = parseInt(br.readLine());

        /*
            좀비 두고 1미터씩 전진한다고 생각
         */

        int[] pSums = new int[L + 1];
        for (int i = 1; i <= L; i++) {
            pSums[i] = pSums[i - 1] + mk;
            int zombie = parseInt(br.readLine());
            int damage = pSums[i] - pSums[Math.max(0, i - ml)];

            if(damage>=zombie) {
                continue;
            }

            if (--C >= 0) {
                pSums[i] = pSums[i - 1];
                continue;
            }

            return "NO";
        }

        return "YES";
    }
}