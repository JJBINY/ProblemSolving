package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 7453 합이 0인 네 정수
 * 투포인터, MITM
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
        int l = 0;
        int r = CD.length-1;

        while (l < AB.length && r>-1) {
            int sum = AB[l] + CD[r];
            if(sum < 0){
                l++;
                continue;
            }else if(sum>0){
                r--;
                continue;
            }

            int l2 = l + 1;
            while (l2<AB.length && AB[l2] == AB[l]){
                l2++;
            }
            int r2 = r - 1;
            while (r2>-1 && CD[r2]==CD[r]){
                r2--;
            }
            ans += (long)(l2 - l) * (long)(r - r2);
            l = l2;
            r = r2;
        }

        System.out.println(ans);
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
