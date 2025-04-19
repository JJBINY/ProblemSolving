package 백준.슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * S1_1522_문자열_교환
 * 슬라이딩 윈도우
 */
public class S1_1522_문자열_교환 {

    static Object solve(BufferedReader br) throws IOException {
        int[] arr = Arrays.stream(br.readLine().split(""))
                .mapToInt(s -> s.equals("a") ? 1 : 0)
                .toArray();
        int windowSize = (int) Arrays.stream(arr).filter(i -> i == 1).count();

        int ans = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int cnt = 0;
            for (int j = 0; j < windowSize; j++) {
                if (arr[(i + j) % arr.length] == 0) {
                    cnt++;
                }
            }
            ans = Math.min(ans, cnt);
        }
        return ans;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 */