package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * S2_14400_편의점_2
 * 수학, 정렬, 기하학
 */
public class S2_14400_편의점_2 {
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

    /*
    맨해튼 거리 -> x,y 따로 고려한 뒤 합치면 됨
    x만 고려한다면?
    - 왼쪽 끝에서부터 d만큼 좌표 이동한다고 하면 {왼쪽 고객 수 - 오른쪽 고객수}만큼 정답 변화
    - 오른쪽 끝에서도 같은 논리
    -> 현재 위치에서 좌우 고객 수가 중요 -> 중앙으로 갈수록 감소 -> 중앙값이 최소가 되는 위치
     */
    static Object solve(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        long xMid = 0;
        long yMid = 0;
        long[] x = new long[n];
        long[] y = new long[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(x);
        Arrays.sort(y);
        xMid = x[n / 2];
        yMid = y[n / 2];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(x[i] - xMid) + Math.abs(y[i] - yMid);
        }
        return ans;
    }
}