package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * P5 거의 최단 경로
 * https://www.acmicpc.net/problem/5719
 */
public class P5_거의최단경로 {

    static int[][] weights;
    static int[] distances;
    static List<List<Integer>> states;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n ==0 || m==0){
                break;
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            weights = new int[n][n];
            while (m-- >0){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                weights[u][v] = weight;
            }

            //최단경로 탐색
            distances = new int[n];
            states = IntStream.range(0, n).mapToObj(i -> new ArrayList<Integer>())
                    .collect(Collectors.toList());
            dijkstra(s, d);

            //최단경로에 포함된 경로 제거
            removeEdge(d,s);

            //거의 최단경로 탐색
            sb.append(dijkstra(s, d)).append("\n");
        }
        br.close();
        System.out.print(sb.toString());
    }


    private static int dijkstra(int s, int d) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getDist));
        pq.add(new Pair(s, 0));
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[s] = 0;
        int result = -1;
        while (!pq.isEmpty()){
            int now = pq.peek().id;
            int dist = pq.poll().dist;

            if(distances[now]< dist){
                continue;
            }else if(now == d){
                result = dist;
            }

            for (int next = 0; next < n; next++) {
                int weight = weights[now][next];

                if(weight==0) continue;

                if(dist+weight < distances[next]){
                    distances[next] = dist + weight;
                    states.get(next).clear();
                    states.get(next).add(now);
                    pq.add(new Pair(next, dist + weight));
                }else if(dist+weight == distances[next]){
                    states.get(next).add(now);
                }
            }
        }
        return result;
    }


    static void removeEdge(int now, int target){
        if(now == target){
            return;
        }

        for (int prev : states.get(now)) {
            if(weights[prev][now]>0){
                weights[prev][now] = 0;
                removeEdge(prev, target);
            }
        }
    }

    static class Pair{
        int id;
        int dist;

        public Pair(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        public int getDist() {
            return dist;
        }
    }

}