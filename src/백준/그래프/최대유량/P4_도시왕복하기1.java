package 백준.그래프.최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

/**
 * P4 도시 왕복하기 1
 * https://www.acmicpc.net/problem/17412
 */
public class P4_도시왕복하기1 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int P = parseInt(st.nextToken());
        int[][] capacities = new int[N][N];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken()) - 1;
            int b = parseInt(st.nextToken()) - 1;
            capacities[a][b] = 1;
        }

        int[][] flows = new int[N][N];
        int source = 0;
        int sink = 1;
        int totalFlow = 0;
        while (true) {
            //경로 탐색
            int[] prev = new int[N];
            Arrays.fill(prev, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (int next = 0; next < N; next++) {
                    if (capacities[now][next] - flows[now][next] > 0 && prev[next] == -1) {
                        prev[next] = now;
                        queue.add(next);
                        if (next == sink) {
                            break;
                        }
                    }
                }
            }

            //경로 탐색 실패
            if (prev[sink] == -1) break;

            //경로에서 가능한 최소 유량 탐색
            int flow = MAX_VALUE;
            for (int i = sink; i != source; i = prev[i]) {
                flow = Math.min(flow, capacities[prev[i]][i] - flows[prev[i]][i]);
            }
            //유량 갱신
            for (int i = sink; i != source; i = prev[i]) {
                flows[prev[i]][i] += flow;
                flows[i][prev[i]] -= flow;
            }
            totalFlow += flow;
        }

        System.out.println(totalFlow);
        br.close();
    }

}
