package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G3_17471_게리맨더링
 * 조합, DFS, 브루트포스
 */
public class G3_17471_게리맨더링 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, totalPop, cState, res, state;
    static Node[] nodes;

    static Object solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        totalPop = 0;
        cState = 0;
        nodes = new Node[N + 1];
        int INF = 100_000;
        res = INF;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i, parseInt(st.nextToken()));
            totalPop += nodes[i].pop;
            cState |= 1 << i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int adj = parseInt(st.nextToken());
            for (int j = 0; j < adj; j++) {
                nodes[i].edges.add(nodes[parseInt(st.nextToken())]);
            }
        }
        boolean[] selected = new boolean[N + 1];
        selected[1] = true;
        combi(2, nodes[1].pop, selected);

        return res == INF ? -1 : res;
    }

    static void combi(int depth, int pop, boolean[] selected) {
        if (depth == N+1) {
            state = 0;
            dfs(nodes[1], true, selected);
            for (int i = 2; i <= N; i++) {
                if (!selected[i]) {
                    dfs(nodes[i], false, selected);
                    if (state == cState) {
                        res = Math.min(res, Math.abs(totalPop - pop * 2));
                    }
                    break;
                }
            }
            return;
        }
        combi(depth + 1, pop, selected);
        selected[depth] = true;
        combi(depth + 1, pop + nodes[depth].pop, selected);
        selected[depth] = false;
    }

    static void dfs(Node now, boolean group, boolean[] groups) {
        state |= 1 << now.id;
        for (Node next : now.edges) {
            if (groups[next.id] != group) continue;
            if (visited(state, next)) continue;
            dfs(next, group, groups);
        }
    }


    private static boolean visited(int state, Node node) {
        return (state & 1 << node.id) > 0;
    }

    static class Node {
        int id;
        int pop;
        List<Node> edges = new ArrayList<>();

        public Node(int id, int pop) {
            this.id = id;
            this.pop = pop;
        }
    }
}
/*
8
17 42 46 81 71 8 37 12
4 2 4 5 7
5 1 3 4 5 8
2 2 4
5 1 2 3 7 8
5 1 2 6 7 8
2 5 8
4 1 4 5 8
5 2 4 5 6 7
->2
 */