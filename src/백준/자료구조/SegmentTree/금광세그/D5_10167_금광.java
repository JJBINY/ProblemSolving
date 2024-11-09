package 백준.자료구조.SegmentTree.금광세그;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * D5_10167_금광
 * 세그먼트 트리, 좌표 압축
 */
public class D5_10167_금광 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine()); // <= 3,000

        int[] arr = new int[N];
        List<Mine> mines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mines.add(new Mine(parseInt(st.nextToken()),
                    parseInt(st.nextToken()),
                    parseInt(st.nextToken())));
        }

        // 좌표 압축
        compressLocation(mines);

        // y 좌표 별 금광 위치 기록
        List<List<Mine>> minesOnY = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            minesOnY.add(new ArrayList<>());
        }
        for (Mine mine : mines) {
            minesOnY.get(mine.y).add(mine);
        }

        // 최대 개발 이익 구하기
        long ans = 0;
        for (int i = 0; i < N; i++) {
            SegTree segTree = SegTree.init(N);
            for (int j = i; j < N; j++) {
                for (Mine mine : minesOnY.get(j)) { // i에 대해 최대 N번 수행된다
                    segTree.add(mine.x, mine.w); // => 따라서 N^2logN
                }
                ans = Math.max(ans, segTree.query(0, N - 1));
            }
        }

        return ans;
    }

    private static void compressLocation(List<Mine> mines) {
        Map<Integer, Integer> xmap = new HashMap<>();
        mines.sort(Comparator.comparingInt(m -> m.x));
        int rank = 0;
        for (Mine mine : mines) {
            if (xmap.containsKey(mine.x)) continue;
            xmap.put(mine.x, rank++);
        }
        Map<Integer, Integer> ymap = new HashMap<>();
        mines.sort(Comparator.comparingInt(m -> m.y));
        rank = 0;
        for (Mine mine : mines) {
            if (ymap.containsKey(mine.y)) continue;
            ymap.put(mine.y, rank++);
        }

        for (Mine mine : mines) {
            mine.x = xmap.get(mine.x);
            mine.y = ymap.get(mine.y);
        }
    }

    static class Mine {
        int x;
        int y;
        int w;

        public Mine(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    static class SegTree {
        static final int INF = (int) 1e9;
        static final State nullState = new State(0, -INF, -INF, -INF);
        private int N;
        private State[] tree;

        private SegTree(int N) {
            this.N = N;
            this.tree = new State[N * 4];
            Arrays.fill(tree, new State(0, 0, 0, 0));
        }

        public static SegTree init(int N) {
            return new SegTree(N);
        }

        public void add(int at, int val) {
            add(1, 0, N - 1, at, val);
        }

        private void add(int idx, int l, int r, int at, int val) {
            if (at < l || at > r) return;
            if (l == r) {
                tree[idx] = tree[idx].add(val);
                return;
            }
            int m = (l + r) / 2;
            add(idx * 2, l, m, at, val);
            add(idx * 2 + 1, m + 1, r, at, val);
            tree[idx] = getState(tree[idx * 2], tree[idx * 2 + 1]);
        }

        public long query(int from, int to) {
            return query(1, 0, N - 1, from, to).tMax;
        }

        private State query(int idx, int l, int r, int from, int to) {
            if (to < l || r < from) return nullState;
            if (from <= l && r <= to) {
                return tree[idx];
            }

            int m = (l + r) / 2;
            State left = query(idx * 2, l, m, from, to);
            State right = query(idx * 2 + 1, m + 1, r, from, to);
            return getState(left, right);
        }

        private State getState(State left, State right) {
            long total = left.total + right.total;
            long lMax = max(left.lMax, left.total + right.lMax);
            long rMax = max(right.rMax, left.rMax + right.total);
            long tMax = max(left.tMax, right.tMax, left.rMax + right.lMax);
            return new State(total, tMax, lMax, rMax);
        }

        private long max(long... nums) {
            long max = -INF;
            for (long num : nums) {
                max = Math.max(max, num);
            }
            return max;
        }

        static class State {
            final long total;
            final long tMax;
            final long lMax;
            final long rMax;

            public State(long total, long tMax, long lMax, long rMax) {
                this.total = total;
                this.tMax = tMax;
                this.lMax = lMax;
                this.rMax = rMax;
            }

            public State add(int val) {
                return new State(total + val, tMax+val, lMax+val, rMax+val);
            }
        }
    }
}
