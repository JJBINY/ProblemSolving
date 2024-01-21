package 백준.그래프.최대유량.MCMF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P2 8992 집기 게임
 * MCMF, 최대유량, 기하학, 선분교차판정
 */
public class P2_8992_집기게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken());
            int M = parseInt(st.nextToken());
            int MAX_LEN = N + M;

            Node.init(MAX_LEN + 1, 2);
            Node source = Node.of(0, 1);
            Node sink = Node.of(0, 0);

            Line[] lines = new Line[MAX_LEN + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                lines[i] = new Line(parseInt(st.nextToken()), parseInt(st.nextToken()),
                        parseInt(st.nextToken()), parseInt(st.nextToken()),
                        parseInt(st.nextToken()));
                source.addEdge(Node.of(i, 0), 1, 0);
                Node.of(i, 0).addEdge(Node.of(i, 1), 1, 0);
            }

            for (int i = N + 1; i <= MAX_LEN; i++) {
                st = new StringTokenizer(br.readLine());
                lines[i] = new Line(parseInt(st.nextToken()), parseInt(st.nextToken()),
                        parseInt(st.nextToken()), parseInt(st.nextToken()),
                        parseInt(st.nextToken()));
                Node.of(i, 1).addEdge(sink, 1, 0);
                Node.of(i, 0).addEdge(Node.of(i, 1), 1, 0);
            }


            //간선연결
            for (int i = 1; i <= N; i++) {
                Line a = lines[i];
                for (int j = N + 1; j <= MAX_LEN; j++) {
                    Line b = lines[j];
                    if (Line.isCrossed(a, b)) {
                        Node.of(i).addEdge(Node.of(j), 1, -(a.weight * b.weight));
                    }
                }
            }

            int ans = 0;

            int minCost = 0;
            int maxFlow = 0;
            while (true) {
                boolean[][] isInQ = new boolean[MAX_LEN + 1][2];
                int[][] dist = new int[MAX_LEN + 1][2];
                Node[][] prev = new Node[MAX_LEN + 1][2];
                Node.Edge[][] path = new Node.Edge[MAX_LEN + 1][2];
                Queue<Node> Q = new LinkedList<>();

                for (int[] ints : dist) Arrays.fill(ints, MAX_VALUE);
                dist[source.id][source.time] = 0;
                Q.add(source);

                while (!Q.isEmpty()) {
                    Node s = Q.poll();
                    isInQ[s.id][s.time] = false;

                    for (Node.Edge edge : s.edges) {
                        if (edge.getFreeCapacity() <= 0) continue;

                        Node e = edge.to;
                        int d = dist[s.id][s.time] + edge.cost;
                        if (dist[e.id][e.time] > d) {
                            dist[e.id][e.time] = d;
                            prev[e.id][e.time] = s;
                            path[e.id][e.time] = edge;
                            if (!isInQ[e.id][e.time]) {
                                Q.add(e);
                                isInQ[e.id][e.time] = true;
                            }
                        }
                    }
                }

                if (isNull(prev[sink.id][sink.time])) break;

                int flow = MAX_VALUE;
                for (Node i = sink; i != source; i = prev[i.id][i.time]) {
                    flow = Math.min(flow, path[i.id][i.time].getFreeCapacity());
                }

                for (Node i = sink; i != source; i = prev[i.id][i.time]) {
                    Node.Edge p = path[i.id][i.time];
                    minCost += flow * p.cost;
                    p.flow += flow;
                    p.reversed.flow -= flow;
                }
                maxFlow += flow;
            }
            sb.append(maxFlow).append(" ").append(-minCost).append("\n");
        }
        System.out.println(sb.toString());
        br.close();

    }


    static class Node {
        private static Node[][] nodes;
        final List<Edge> edges = new ArrayList<>();

        final int id;
        final int time;

        public Node(int id, int time) {
            this.id = id;
            this.time = time;
        }

        static void init(int I, int T) {
            nodes = new Node[I][T];
        }

        static Node of(int id, int time) {
            if (isNull(nodes[id][time])) {
                nodes[id][time] = new Node(id, time);
            }
            return nodes[id][time];
        }

        static Node of(int id) {
            return of(id, 0);
        }

        public void addEdge(Node to, int capacity, int cost) {
            Edge edge = new Edge(to, capacity, cost);
            Edge reverEdge = new Edge(this, 0, -cost);

            edge.reversed = reverEdge;
            reverEdge.reversed = edge;

            this.edges.add(edge);
            to.edges.add(reverEdge);
        }

        static class Edge {
            final Node to;
            final int capacity;
            int flow;
            int cost;
            Edge reversed;

            private Edge(Node to, int capacity, int cost) {
                this.to = to;
                this.capacity = capacity;
                this.cost = cost;
            }

            public int getFreeCapacity() {
                return capacity - flow;
            }
        }
    }


    static class Line {
        Point p1;
        Point p2;
        int weight;

        public Line(int x1, int y1, int x2, int y2, int weight) {
            this.p1 = new Point(x1, y1);
            this.p2 = new Point(x2, y2);
            this.weight = weight;
        }

        public static boolean isCrossed(Line l1, Line l2) {

            Point p1 = l1.p1;
            Point p2 = l1.p2;
            Point p3 = l2.p1;
            Point p4 = l2.p2;
            if (p1.compareTo(p2) >= 0) {
                p1.swap(p2);
            }
            if (p3.compareTo(p4) >= 0) {
                p3.swap(p4);
            }

            long v1 = CCW(p1, p2, p3);
            long v2 = CCW(p1, p2, p4);

            long v3 = CCW(p3, p4, p1);
            long v4 = CCW(p3, p4, p2);
            long r1 = v1 * v2; //각각의 선분의 한 점에서 다른 선분의 점들에 대해 그은 벡터
            long r2 = v3 * v4;
            if (r1 == 0 && r2 == 0) {
                if (p1.compareTo(p4) <= 0 && p3.compareTo(p2) <= 0) {
                    return true;

                } else {
                    return false;
                }
            } else if (r1 <= 0 && r2 <= 0) {
                return true;
            } else {
                return false;
            }
        }

        static private long CCW(Point a, Point b, Point c) {
            long result = (a.x * b.y + b.x * c.y + c.x * a.y) - (a.y * b.x + b.y * c.x + c.y * a.x);
            if (result > 0) {
                return 1;
            } else if (result == 0) {
                return 0;
            } else {
                return -1;
            }
        }

        static class Point implements Comparable {
            long x;
            long y;

            public Point(long x, long y) {
                this.x = x;
                this.y = y;
            }


            private void swap(Point other) {
                long x = this.x;
                long y = this.y;
                this.x = other.x;
                this.y = other.y;
                other.x = x;
                other.y = y;
            }

            @Override
            public int compareTo(Object o) {
                Point p = (Point) o;
                if (x == p.x) {
                    return (int) (y - p.y);
                }
                return (int) (x - p.x);
            }
        }
    }
}
