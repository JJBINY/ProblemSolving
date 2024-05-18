package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 2142 우체국
 * 누적합, 큰 수
 */
public class G4_2141_우체국 {
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
        int N = parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = parseInt(st.nextToken());
            arr[i][1] = parseInt(st.nextToken());
        }

        // sum(|x-X[i]|*A[i]) -> 그래프를 그려보면 최소가 될 수 있는 x 값은 마을 위치

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        BigInteger aSum = BigInteger.ZERO;
        BigInteger pSum = BigInteger.ZERO;
        BigInteger[] lSums = new BigInteger[N];
        lSums[0] = BigInteger.ZERO;
        for (int i = 1; i < N; i++) {
            aSum = aSum.add(BigInteger.valueOf(arr[i - 1][1]));
            pSum = aSum.multiply(BigInteger.valueOf(arr[i][0] - arr[i - 1][0]));
            lSums[i] = lSums[i - 1].add(pSum);
        }

        aSum = BigInteger.ZERO;
        pSum = BigInteger.ZERO;
        BigInteger[] rSums = new BigInteger[N];
        rSums[N-1] = BigInteger.ZERO;
        for (int i = N - 2; i >= 0; i--) {
            aSum = aSum.add(BigInteger.valueOf(arr[i + 1][1]));
            pSum = aSum.multiply(BigInteger.valueOf(arr[i + 1][0] - arr[i][0]));
            rSums[i] = rSums[i + 1].add(pSum);
        }

        BigInteger dist = lSums[0].add(rSums[0]);
        int result = arr[0][0];
        for (int i = 1; i < N; i++) {
            if (lSums[i].add(rSums[i]).compareTo(dist) < 0) {
                dist = lSums[i].add(rSums[i]);
                result = arr[i][0];
            }
        }
        return result;
    }
}