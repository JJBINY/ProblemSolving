package 프로그래머스.카카오.Lv3;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lv3_등산코스_정하기 {
    /*
    Intensity 최소인 경우엔 왔던 경로 그대로 돌아가는 경우가 항상 포함됨
    -> 왕복이 아닌 편도만 고려해도 됨
    -> 게이트가 아닌 산봉우리를 출발점으로 생각해도 됨
    */
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Node> nodes = new ArrayList<>();
        IntStream.range(0,n+1).forEach(i->nodes.add(Node.of(i)));
        Arrays.stream(paths).forEach(p->nodes.get(p[0]).addPath(nodes.get(p[1]),p[2]));
        Arrays.stream(gates).forEach(g->nodes.get(g).isGate=true);
        Arrays.stream(summits).forEach(s->nodes.get(s).isSummit=true);
        Arrays.sort(summits);
        nodes.stream().forEach(node->node.edges.sort(Comparator.comparing(Edge::getWeight)));
        // nodes.stream().forEach(node->System.out.println(node.isGate));

        int min = Integer.MAX_VALUE;
        int num =0;
        for(int i=0;i<summits.length;i++){
            intensity = 0;
            if(!findGate(nodes.get(summits[i]))) continue; //해당 봉우리에서 출입구에 도달 못하는 경우
            if(min>intensity){
                min = intensity;
                num = summits[i];
            }
        }

        return new int[]{num,min};
    }

    int intensity;
    public boolean findGate(Node summit){
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        boolean[] visited = new boolean[50001];
        visited[summit.idx] = true;

        pq.addAll(summit.edges);
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            Node next = edge.to;

            if(visited[next.idx] || next.isSummit) continue;
            visited[next.idx] = true;

            intensity = intensity<edge.weight ? edge.weight:intensity;
            if(next.isGate) return true;
            pq.addAll(next.edges);
        }

        return false;

    }

    static class Edge{
        Node to;
        int weight;
        public Edge(Node to, int weight){
            this.to=to;
            this.weight=weight;
        }
        public int getWeight(){
            return weight;
        }
    }
    static class Node{
        int idx;
        boolean isGate = false;
        boolean isSummit = false;
        List<Edge> edges = new ArrayList<>();

        private Node(int idx){
            this.idx = idx;
        }
        public static Node of(int idx){
            return new Node(idx);
        }
        public void addPath(Node to, int weight){
            edges.add(new Edge(to,weight));
            to.edges.add(new Edge(this,weight));
        }
    }
}

