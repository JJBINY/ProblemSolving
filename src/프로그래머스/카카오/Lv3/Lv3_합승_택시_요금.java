package 프로그래머스.카카오.Lv3;


import java.util.*;

public class Lv3_합승_택시_요금 {
    /*
    합승해서 지나는 마지막 지점을 m이라 할 때,

    1. s부터 각지점까지의 최단거리
    2. a부터 각지점까지의 최단거리
    3. b부터 각지점까지의 최단거리
    4. 1,2,3을 구한 뒤 각 지점에서 s,a,b로 이르는 최단거리의 합이 가장 적은 지점을 선택한다.

    */
    public int solution(int n, int s, int a, int b, int[][] fares) {


        List<List<Node>> graph = getGraph(n, fares);
        int[] fromS = dijkstra(n, graph, s-1);
        int[] fromA = dijkstra(n, graph, a-1);
        int[] fromB = dijkstra(n, graph, b-1);

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = fromA[i] + fromB[i] + fromS[i];
            answer = answer < sum ? answer : sum;
        }

        return answer;
    }

    private static List<List<Node>> getGraph(int n, int[][] fares) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            graph.get(fare[0]-1).add(Node.of(fare[1]-1, fare[2]));
            graph.get(fare[1]-1).add(Node.of(fare[0]-1, fare[2]));
        }
        return graph;
    }

    public int[] dijkstra(int n, List<List<Node>> graph, int start){

        int[] totalCost = new int[n];
        Arrays.fill(totalCost, Integer.MAX_VALUE);
        totalCost[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        pq.add(Node.of(start, 0));

        while (!pq.isEmpty()){
            Node now = pq.poll();

            if(totalCost[now.id] < now.cost) continue;
            List<Node> edges = graph.get(now.id);
            for (Node to : edges) {
                int newCost = now.cost + to.cost;
                if(now.cost + to.cost < totalCost[to.id]){
                    totalCost[to.id] = newCost;
                    pq.add(to.renew(newCost));
                }
            }
        }

        return totalCost;
    }

    static class Node implements Comparable{
        int id;

        int cost;

        private Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        public static Node of(int id, int cost){
            return new Node(id, cost);
        }

        public Node renew(int cost){
            return new Node(this.id, cost);
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Object o) {
            Node n = (Node)o;
            return cost - n.cost;
        }
    }
}

