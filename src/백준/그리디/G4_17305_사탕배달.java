package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 17305 사탕 배달
 * 그리디, 누적합
 * 값 범위 잘보자...
 */
public class G4_17305_사탕배달 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int W = parseInt(st.nextToken());

        List<Integer> small = new ArrayList<>();
        List<Integer> big = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("3")) {
                small.add(parseInt(st.nextToken()));
            } else {
                big.add(parseInt(st.nextToken()));
            }
        }
        br.close();

        small.sort(Comparator.reverseOrder());
        big.sort(Comparator.reverseOrder());

        long val = 0;
        int s = 0;
        while ((s + 1) * 3 <= W && s < small.size()) {
            val += small.get(s++);
        }
        long ans = val;
        int b = 0;
        while (true) {

            while (s * 3 + (b + 1) * 5 <= W && b < big.size()) {
                val += big.get(b++);
                ans = Math.max(ans, val);
            }

            s-=1;
            if (s < 0) {
                break;
            }
            val -= small.get(s);
        }
        System.out.println(ans);
    }

}
/*
6 14
3 50
5 20
5 40
5 60
5 80
5 100
ans : 230

6 16
3 50
3 20
5 40
5 60
5 80
5 100
ans : 250

6 15
3 50
3 20
5 40
5 60
5 80
5 100
ans : 240

6 12
3 50
3 20
3 40
3 60
5 80
5 100
ans : 210
 */

