package 백준.이분탐색.MeetInTheMiddle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 7453 합이 0인 네 정수
 * 이분탐색, MITM
 */
public class G2_7453_합이0인네정수 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N;
    static int[] A, B, C, D;

    static void solve(BufferedReader br) throws IOException {
        init(br);

        int[] AB = new int[N * N];
        int[] CD = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx++;
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);


        long ans = 0;
        for (int target : CD) {
            ans += upperBound(AB, -target) - lowerBound(AB, -target);

        }
        System.out.println(ans);
    }

    static int upperBound(int[] arr, int tg) {
        int lo = -1, hi = arr.length;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= tg) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    static int lowerBound(int[] arr, int tg) {
        int lo = -1, hi = arr.length;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < tg) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    private static void init(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = parseInt(st.nextToken());
            B[i] = parseInt(st.nextToken());
            C[i] = parseInt(st.nextToken());
            D[i] = parseInt(st.nextToken());
        }
    }
}
/*
10
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
 */
