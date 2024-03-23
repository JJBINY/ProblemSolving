package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G2 10800 컬러볼
 * 누적합, 정렬
 */
public class G2_10800_컬러볼 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Ball[] balls;
    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        init(br, N);

        Map<Integer, List<Ball>> colorMap = new HashMap<>();
        mapBallByColor(colorMap);

        for (List<Ball> cBalls : colorMap.values()) { //O(N)
            for (int i = 1; i < cBalls.size(); i++) {
                getColorPrefixSum(cBalls, i);
            }
        }

        int[] pSums = new int[2001]; //전체 색상 사이즈별 누적합
        getTotalPrefixSum(N, pSums);

        int[] ans = new int[N];
        for (Ball ball : balls) {
            ans[ball.id] = pSums[ball.size - 1] - ball.pSum;
        }

        for (int s : ans) {
            System.out.println(s);
        }
    }

    private static void getTotalPrefixSum(int N, int[] pSums) {
        for (int i = 0; i < N; i++) { //O(N)
            Ball b = balls[i];
            pSums[b.size]++;
        }

        for (int s = 1; s <= 2000; s++) { //O(S)
            pSums[s] = pSums[s - 1] + pSums[s] * s;
        }
    }

    private static void getColorPrefixSum(List<Ball> cBalls, int i) {
        Ball now = cBalls.get(i);
        Ball prev = cBalls.get(i - 1);
        if(now.size == prev.size){ //색상과 크기가 모두 같을 수 있다.
            now.pSum = prev.pSum;
            now.sameCnt = prev.sameCnt + 1;
        }else {
            now.pSum = prev.pSum + prev.size * prev.sameCnt;
        }
    }

    private static void mapBallByColor(Map<Integer, List<Ball>> colorMap) {
        Arrays.sort(balls, Comparator.comparingInt(b -> b.size));
        for (Ball ball : balls) { //O(N)
            List<Ball> list = colorMap.getOrDefault(ball.color, new ArrayList<>());
            list.add(ball);
            colorMap.put(ball.color, list);
        }
    }

    private static void init(BufferedReader br, int N) throws IOException {
        balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size);
        }
    }

    static class Ball{
        int id;
        int color;
        int size;
        int pSum; //색상별 누적합
        int sameCnt = 1;
        public Ball(int id, int color, int size) {
            this.id = id;
            this.color = color;
            this.size = size;
        }
    }
}