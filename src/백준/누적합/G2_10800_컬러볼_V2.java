package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


/**
 * G2 10800 컬러볼
 * 누적합, 정렬
 */
public class G2_10800_컬러볼_V2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size);
        }

        Arrays.sort(balls, Comparator.comparingInt(b -> b.size));

        int pSum = 0; //전체 누적합
        int[] cSums = new int[N + 1]; //색상별 누적합
        int[] ans = new int[N];
        for (int i = 0, j = 0; i < N; i++) {
            Ball now = balls[i];
            while (now.size > balls[j].size) {
                pSum += balls[j].size;
                cSums[balls[j].color] += balls[j].size;
                j++;
            }
            ans[now.id] = pSum - cSums[now.color];
        }

        StringBuilder sb = new StringBuilder();

        for (int an : ans) {
            sb.append(an).append("\n");
        }
        System.out.println(sb.toString());
    }


    static class Ball {
        int id;
        int color;
        int size;

        public Ball(int id, int color, int size) {
            this.id = id;
            this.color = color;
            this.size = size;
        }
    }
}