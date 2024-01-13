import java.util.*;

class 라_미로탈출 {
    /*
    트랩의 활성 상태에 따라 노드간의 간선 방향이 변경 가능
    연결된 2개 노드 사이의 상태에 따라서만 달라지므로 노드마다 간선을 직접 연결하는 건 좋지 않음
    00 01 10 11과 같이 4개의 상태가 가능하며 00,11 은 정방향, 나머지는 역방향

    정방향 간선들만 edges[p][q] = weight 형태로 저장한 뒤
    도 노드 간의 관계가 역방향일 경우엔 edges[q][p]와 같은 형태로 간선 획득
    ex) edges[3][2] = 3 일 때, 트랩인 2가 활성화 된 상태에서 2->3 조회하고 싶은 경우 edges[3][2]를 조회.
    간선이 없는 경우는 INF값으로 처리해서 구분.
    ** 간선 저장 시 동일 방향으로 여러 간선이 있을 수 있으므로 그중 최소 간선만 저장하면 됨
     */

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {

        List<Node> graph = new ArrayList<>();
        int[][] edges = new int[n + 1][n + 1];
        createNodes(n, graph);
        setTraps(traps, graph);
        setEdges(roads, edges, graph);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(graph.get(start));

        boolean[][] visited = new boolean[n + 1][2 << traps.length];
        while (!pq.isEmpty()) {
            Node now = pq.poll();

//            if(visited[now.id][now.state]) continue;
//            visited[now.id][now.state] = true;
            if (now.id == end) {
                return now.cost;
            }
//            System.out.println();
//            System.out.println("now.id = " + now.id);
            for (Node next : now.neighbors) {
                int weight;
                if(now.isCorrectDirection(next)){
                    weight = edges[now.id][next.id];
                }else{
//                    System.out.println("reverse!");
                    weight = edges[next.id][now.id];
                }
//                System.out.println("next.id = " + next.id);
//                System.out.println("weight = " + weight);
                if(weight == Integer.MAX_VALUE) continue;

                int nextState = now.state;
                if(next.tId >0){
                    nextState ^= (1 << next.tId);
                }
//                System.out.println("added");
                pq.add(next.create(next.id, now.cost + weight, nextState));
            }
        }


        return -1;
    }

    private static void setEdges(int[][] roads, int[][] edges,List<Node> graph) {
        for (int i = 0; i < edges.length; i++) {
            Arrays.fill(edges[i], Integer.MAX_VALUE);
        }
        for (int[] road : roads) {
            edges[road[0]][road[1]] = Math.min(edges[road[0]][road[1]],road[2]);
            Node p = graph.get(road[0]);
            Node q = graph.get(road[1]);
            p.neighbors.add(q);
            q.neighbors.add(p);
        }
    }

    private static void setTraps(int[] traps, List<Node> graph) {
        for (int i = 0; i < traps.length; i++) {
            graph.get(traps[i]).tId = i + 1;
        }
    }

    private static void createNodes(int n, List<Node> graph) {
        for (int i = 0; i <= n; i++) {
            graph.add(new Node(i,0,0)); // 계산을 편하게 하기 위해 0추가
        }
    }

    static class Edge {
        Node p;
        Node q;
        int weight;

        public Edge(Node p, Node q, int weight) {
            this.p = p;
            this.q = q;
            this.weight = weight;
        }
    }

    static class Node implements Comparable{
        int id;
        int cost;
        int state;
        int tId = 0;
        Set<Node> neighbors = new HashSet<>();

        public Node(int id, int cost, int state) {
            this.id = id;
            this.cost = cost;
            this.state = state;
        }
        public Node create(int id, int cost, int state){
            Node node = new Node(id, cost, state);
            node.neighbors = this.neighbors;
            node.tId = this.tId;
            return node;
        }

        public boolean isCorrectDirection(Node next) {
            boolean a = (state & (1 << next.tId)) > 0; // true
            boolean b = (state & (1 << tId)) > 0;
            return  !(a^b); // 둘 다 활성이거나 비활성이어야 정방향
        }


        @Override
        public int compareTo(Object o) {
            Node n = (Node) o;
            return cost-n.cost;
        }
    }
}
