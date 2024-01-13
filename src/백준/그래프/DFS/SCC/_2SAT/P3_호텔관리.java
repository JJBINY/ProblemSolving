package 백준.그래프.DFS.SCC._2SAT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P3 호텔 관리
 * https://www.acmicpc.net/problem/16915
 */
public class P3_호텔관리 {
    static int sequence;
    static Stack<List<Node>> sccs = new Stack<>();
    static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //방의 갯수
        int m = Integer.parseInt(st.nextToken()); //스위치의 갯수
        Node.init();

        st = new StringTokenizer(br.readLine());
        List<List<Integer>> rooms = new ArrayList<>(); //방번호,스위치
        rooms.add(new ArrayList<>());
        for (int i = 1; i < n + 1; i++) {
            List<Integer> room = new ArrayList<>();
            int state = Integer.parseInt(st.nextToken());
            room.add(state);
            rooms.add(room);
        }

        //방별 스위치 기록
        for (int i = 1; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            while (k-- > 0) {
                int roomNumber = Integer.parseInt(st.nextToken());
                rooms.get(roomNumber).add(i);
            }
        }
        //그래프 생성
        for (int i = 1; i < n + 1; i++) {
            List<Integer> room = rooms.get(i);
            Integer state = room.get(0);
            Integer s1 = room.get(1);
            Integer s2 = room.get(2);
            if (state == 1) {
                Node.of(-s1).nexts.add(Node.of(-s2));
                Node.of(s2).nexts.add(Node.of(s1));
                Node.of(s1).nexts.add(Node.of(s2));
                Node.of(-s2).nexts.add(Node.of(-s1));
            }else{
                Node.of(-s1).nexts.add(Node.of(s2));
                Node.of(-s2).nexts.add(Node.of(s1));
                Node.of(s2).nexts.add(Node.of(-s1));
                Node.of(s1).nexts.add(Node.of(-s2));
            }

        }

        //scc탐색
        for (int i = 1; i < m + 1; i++) {
            if (!Node.of(i).isFinished) {
                dfs(Node.of(i));
            }
            if (!Node.of(-i).isFinished) {
                dfs(Node.of(-i));
            }
        }

        //결과 출력
        if (isSatisfied(m)) {
            System.out.println(1);
        } else {
            System.out.println(0);
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
        private static final Node[] positive = new Node[100_001];
        private static final Node[] negative = new Node[100_001];
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
}
