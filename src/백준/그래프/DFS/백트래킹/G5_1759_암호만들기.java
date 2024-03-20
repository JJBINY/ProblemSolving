package 백준.그래프.DFS.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G5_1759_암호만들기
 * 완전탐색, 조합, 백트래킹
 */
public class G5_1759_암호만들기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int L, C;
    static String[] arr;
    static StringBuilder ans = new StringBuilder();

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new String[C];
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken();
        }
        Arrays.sort(arr);
        func(0, 0, new StringBuilder(), 0, 0);
        System.out.println(ans);
    }

    static void func(int from, int cnt, StringBuilder now, int mo, int ja) {
        if (cnt == L) {
            if (mo >= 1 && ja >= 2) {
                ans.append(now).append("\n");
            }
            return;
        } else if (from >= C) return;


        boolean isMo = isMo(arr[from]);
        func(from + 1, cnt + 1, new StringBuilder(now).append(arr[from]), mo + (isMo ? 1 : 0), ja + (isMo ? 0 : 1));
        func(from + 1, cnt, now, mo, ja);
    }

    static boolean isMo(String s) {
        return s.equals("a") ||
                s.equals("e") ||
                s.equals("i") ||
                s.equals("o") ||
                s.equals("u");
    }

}