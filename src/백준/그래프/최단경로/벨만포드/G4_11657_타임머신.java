package 백준.그래프.최단경로.벨만포드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 11657 타임머신
 * 최단경로, 벨만 포드
 */
public class G4_11657_타임머신 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken());
            int M = parseInt(st.nextToken());
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                edges.add(new Edge(input[0], input[1], input[2]));
            }

            long[] distance = new long[N+1];
            Arrays.fill(distance,Integer.MAX_VALUE);
            distance[1] = 0;
            for (int i = 1; i < N+1; i++) {
                for (Edge edge : edges) {
                    if(distance[edge.from] < Integer.MAX_VALUE && distance[edge.to] > distance[edge.from] + edge.weight){
                        distance[edge.to] = distance[edge.from] + edge.weight;
                        if(i == N){
                            distance[0] = -1;
                        }
                    }
                }
            }

            if(distance[0] == -1){
                System.out.println(-1);
            }else{
                StringBuilder sb = new StringBuilder();
                for (int i = 2; i < N+1; i++) {
                    sb.append(distance[i] < Integer.MAX_VALUE ? distance[i] : -1).append("\n");
                }
                System.out.println(sb.toString());
            }
        }
    }

    static class Edge{

        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

}
