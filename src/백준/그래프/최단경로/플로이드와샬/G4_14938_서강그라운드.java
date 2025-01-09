package 백준.그래프.최단경로.플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G4_14938_서강그라운드
 * 그래프, 최단경로, 플로이드-워셜
 */
public class G4_14938_서강그라운드 {
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
    얻을 수 있는 아이템 최대 개수 구하기

    양방향 간선
    가중치 l = [1, 15]
    수색 범위 m = [1, 15] 이내 모든 아이템 획득 가능
    => 도달 가능한 모든 노드까지 시뮬레이션
    => 가능한 최대 개수의 아이템을 획득해야 함
    => 최단 경로

    노드 개수 n = [1, 100]
    간선 개수 r = [1, 100]
    노드 마다 존재하는 아이템수 t = [1, 30]
     */
    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 수
        int m = Integer.parseInt(st.nextToken()); // 이동 범위
        int r = Integer.parseInt(st.nextToken()); // 간선 수
        int[] items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 최단 경로 테이블 초기화
        int[][] dists = new int[n][n];
        int INF = 100_000;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dists[i], INF);
            dists[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken());
            dists[a][b] = v;
            dists[b][a] = v;
        }



        // 플로이드 와샬
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if(i == k) continue;
                for (int j = 0; j < n; j++) {
                    dists[i][j] = Math.min(dists[i][j], dists[i][k] + dists[k][j]);
                }
            }
        }

        // 도달 가능한 지점에서 아이템 구하기
        int ans = 0;
        for (int i = 0; i < n; i++) {
//            System.out.println("Arrays.toString(dists["+i+"]) = " + Arrays.toString(dists[i]));
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if(dists[i][j] <= m){
                    cnt += items[j];
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}