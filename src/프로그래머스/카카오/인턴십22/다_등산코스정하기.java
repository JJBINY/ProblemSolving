import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class 다_등산코스정하기 {
    /*
    1:43:30
    문제조건 잘읽기 ㅎㅎㅎ 서밋 정렬 안해서 해맴
    방문은 실제 방문할 때(next넣을 때가 아닌 now일 때) 처리하기
    -> 뒤에 들어오는 노드가 우선순위 더 높은 노드일 수도 있음
     */
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        List<Node> nodes = init(n, paths, gates, summits);

        int intensity = Integer.MAX_VALUE;
        int summit = 0;
        Arrays.sort(summits);
        for (int s : summits) {
            Node start = nodes.get(s);
            int result = dijkstra(n, start);
            if(result < intensity){
                intensity=result;
                summit=s;
            }
        }


        return new int[]{summit, intensity};
    }

    private static int dijkstra(int n, Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);
        boolean[] visited = new boolean[n + 1];
        while (!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.id]) continue;
            visited[now.id] = true;
//            System.out.println();
//            System.out.println("now.id = " + now.id);

            if(now.isGate()){
//                System.out.println("now.intensity = " + now.intensity);
                return now.intensity;
            }


            for (Edge edge : now.edges) {
                Node next = edge.next;
                if(next.isSummit()) continue;
//                System.out.println("next.id = " + next.id);
                pq.add(next.create(next.id, Math.max(now.intensity,edge.intensity)));
            }
        }
        return Integer.MAX_VALUE;
    }

    private static List<Node> init(int n, int[][] paths, int[] gates, int[] summits) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i, -1));
        }

        for (int[] path : paths) {
            Node n1 = nodes.get(path[0]);
            Node n2 = nodes.get(path[1]);
            n1.edges.add(new Edge(n2, path[2]));
            n2.edges.add(new Edge(n1, path[2]));
        }

        for (int gate : gates) {
            nodes.get(gate).type = 1;
        }
        for (int summit : summits) {
            nodes.get(summit).type = 2;
        }

        return nodes;
    }

    static class Node implements Comparable{
        int id;
        int intensity;
        int type = 0;
        List<Edge> edges = new ArrayList<>();

        public Node(int id, int intensity) {
            this.id = id;
            this.intensity = intensity;
        }

        public Node create(int id, int intensity) {
            Node node = new Node(id, intensity);
            node.edges = this.edges;
            node.type = this.type;
            return node;
        }

        @Override
        public int compareTo(Object o) {
            Node n = (Node) o;
            return intensity - n.intensity;
        }
        public boolean isGate(){
            return type == 1;
        }

        public boolean isSummit(){
            return type == 2;
        }

    }
    static class Edge{
        Node next;
        int intensity;

        public Edge(Node next, int intensity) {
            this.next = next;
            this.intensity = intensity;
        }
    }
}