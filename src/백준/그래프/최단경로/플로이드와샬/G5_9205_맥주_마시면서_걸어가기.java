package 백준.그래프.최단경로.플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G5_9205_맥주_마시면서_걸어가기
 * 플로이드 워셜
 */
public class G5_9205_맥주_마시면서_걸어가기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
//            int T = 1;
            int T = Integer.parseInt(br.readLine());
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
        int N = Integer.parseInt(br.readLine()) +2;
        // 집, 편의점..., 펜타포트 -> 0에서 N-1까지 가는 최단 경로

        Location[] locations = new Location[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            locations[i] = new Location(x, y);
        }

        boolean[][] reachable = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int dist = locations[i].calculateDist(locations[j]);
                if(dist <= 1000){
                    reachable[i][j] = true;
                }
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(i==k) continue;
                for (int j = 0; j < N; j++) {
                    reachable[i][j] |= (reachable[i][k] && reachable[k][j]);
                }
            }
        }

        return reachable[0][N-1]? "happy":"sad";
    }

    static class Location{
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calculateDist(Location location) {
            return Math.abs(x - location.x) + Math.abs(y - location.y);
        }
    }
}