package 백준.그래프.DFS.SCC._2SAT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P4 파이프 청소
 * https://www.acmicpc.net/problem/11668
 */
public class P4_파이프청소 {
    static int sequence;
    static Stack<List<Node>> sccs = new Stack<>();
    static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        Node.init();

        Pipe.Point[] wells = new Pipe.Point[w + 1];
        for (int i = 1; i < w + 1; i++) {
            st = new StringTokenizer(br.readLine());
            wells[i] = new Pipe.Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        Pipe[] pipes = new Pipe[p + 1];
        for (int i = 1; i < p + 1; i++) {
            st = new StringTokenizer(br.readLine());
            Pipe.Point well = wells[Integer.parseInt(st.nextToken())];
            Pipe.Point city = new Pipe.Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            pipes[i] = new Pipe(well, city);
        }

        //그래프 생성
        for (int i = 1; i < p + 1; i++) {
            for (int j = i + 1; j < p + 1; j++) {
                if (pipes[i].well.equals(pipes[j].well)) continue;
                if (pipes[i].isCrossedWith(pipes[j])) {
                    Node.of(-i).nexts.add(Node.of(j));
                    Node.of(-j).nexts.add(Node.of(i));
                    Node.of(i).nexts.add(Node.of(-j));
                    Node.of(j).nexts.add(Node.of(-i));
                }
            }
        }

        //scc탐색
        for (int i = 1; i < p + 1; i++) {
            if (!Node.of(i).isFinished) {
                dfs(Node.of(i));
            }
            if (!Node.of(-i).isFinished) {
                dfs(Node.of(-i));
            }
        }

        //결과 출력
        if (isSatisfied(p)) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
        br.close();
    }

    static void dfs(Node now) {

        now.sequence = ++sequence;
        now.low = sequence;
        stack.push(now);
        for (Node next : now.nexts) {
            if (next.sequence == 0) {
                dfs(next);
                now.low = Math.min(now.low, next.low);
            } else if (!next.isFinished) {
                now.low = Math.min(now.low, next.sequence);
            }
        }
        if (now.low == now.sequence) {
            List<Node> scc = new ArrayList<>();
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                node.isFinished = true;
                node.scc = sccs.size() + 1;
                scc.add(node);
                if (node.equals(now)) {
                    break;
                }
            }
            sccs.push(scc);
        }
    }

    private static boolean isSatisfied(int n) {
        for (int i = 1; i < n + 1; i++) {
            if (Node.of(i).scc == Node.of(-i).scc) {
                return false;
            }
        }
        return true;
    }

    private static String getPossibleVariables(int n) {
        int[] result = new int[n + 1];
        Arrays.fill(result, -1);
        StringBuilder sb = new StringBuilder();
        //scc 생성 순서 역순이 위상정렬 결과와 같음
        while (!sccs.isEmpty()) {
            List<Node> scc = sccs.pop();
            for (Node node : scc) {
                if (result[node.getId()] == -1) {
                    result[node.getId()] = node.val > 0 ? 0 : 1;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            sb.append(result[i]).append(" ");
        }
        return sb.toString();
    }


    static class Node {
        private static final Node[] positive = new Node[10001];
        private static final Node[] negative = new Node[10001];
        private int val;
        private boolean isFinished;
        private int sequence;
        private int low;
        private int scc;
        private final List<Node> nexts = new ArrayList<>();

        private Node(int val) {
            this.val = val;
        }

        public static Node of(int val) {
            int id = Math.abs(val);
            if (val < 0) {
                if (negative[id] == null) {
                    negative[id] = new Node(val);
                }
                return negative[id];
            } else {
                if (positive[id] == null) {
                    positive[id] = new Node(val);
                }
                return positive[id];
            }
        }

        public static void init() {
            Arrays.fill(positive, null);
            Arrays.fill(negative, null);
        }

        public int getId() {
            return Math.abs(val);
        }
    }


    static class Pipe {
        Point well;
        Point city;

        public Pipe(Point well, Point city) {
            this.well = well;
            this.city = city;
        }

        public boolean isCrossedWith(Pipe other) {
            Point p1 = this.well;
            Point p2 = this.city;
            Point p3 = other.well;
            Point p4 = other.city;
            if (p1.compareTo(p2) >= 0) {
                p1 = Point.getNewInstance(city);
                p2 = Point.getNewInstance(well);
            }
            if (p3.compareTo(p4) >= 0) {
                p3 = Point.getNewInstance(other.city);
                p4 = Point.getNewInstance(other.well);
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
            private final long x;
            private final long y;

            public Point(long x, long y) {
                this.x = x;
                this.y = y;
            }

            static public Point getNewInstance(Point p) {
                return new Point(p.x, p.y);
            }


            @Override
            public int compareTo(Object o) {
                Point p = (Point) o;
                if (x == p.x) {
                    return (int) (y - p.y);
                }
                return (int) (x - p.x);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Point point = (Point) o;

                if (x != point.x) return false;
                return y == point.y;
            }

            @Override
            public int hashCode() {
                int result = (int) (x ^ (x >>> 32));
                result = 31 * result + (int) (y ^ (y >>> 32));
                return result;
            }

            @Override
            public String toString() {
                return "Point{" +
                        "x=" + x +
                        ", y=" + y +
                        '}';
            }
        }
    }
}
/*
input
5 5
0 0
1 -1
-2 2
3 5
3 -1
1 10 0
2 0 5
3 5 5
4 6 1
5 6 5

ans
impossible

input
3 3
0 0
0 10
5 5
1 0 5
2 0 5
3 0 8

ans
possible

 */