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
 * G3 최대유량
 * https://www.acmicpc.net/problem/6086
 */
public class G3_최대유량 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int n = Integer.parseInt(br.readLine());
        int size = 53;
        int[][] capacities = new int[size][size];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = alphabetToInt(st.nextToken().charAt(0));
            int b = alphabetToInt(st.nextToken().charAt(0));
            capacities[a][b] = capacities[b][a] += parseInt(st.nextToken());
        }

        int[][] flows = new int[size][size];
        int source = alphabetToInt('A');
        int sink = alphabetToInt('Z');
        int totalFlow = 0;
        while (true) {
            //경로 탐색
            int[] prev = new int[size];
            Arrays.fill(prev, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (int next = 0; next < size; next++) {
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

    static int alphabetToInt(char c) {
        if (c <= 'Z') return c - 'A';
        return c - 'a' + 26;
    }

}
