package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P3_3080_아름다운_이름
 * 자료구조, 문자열, 트리, 트라이, 재귀, 정렬
 */
public class P3_3080_아름다운_이름 {
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

    static int MOD = 1_000_000_007;

    static Object solve(BufferedReader br) throws IOException {

        int N = Integer.parseInt(br.readLine());

        List<String> names = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            names.add(br.readLine());
        }
        names.sort(Comparator.naturalOrder());

        Trie trie = new Trie();
        int prev = names.get(0).length();
        for (int i = 0; i < N-1; i++) {
            String name = names.get(i);
            String nname = names.get(i+1);
            int next;
            for (next = 0; next < Math.min(name.length(), nname.length()); next++) {
                if(name.charAt(next) == nname.charAt(next)) {
                    continue;
                }
                break;
            }

            trie.insert(name, Math.max(prev,next)+1);
            prev = next;
        }

        trie.insert(names.get(N-1),Math.min(names.get(N-1).length(), prev+1));

//        System.out.println("set = " + set);
        return trie.countOrders();
    }

    static class Trie {
        private Node root = new Node();

        public void insert(String str, int len) {
            Node node = root;
            int cnt = 0;
            for (char c : str.toCharArray()) {
                node = node.add(c);
                if(++cnt == len) {
                    break;
                }
            }
            node.isEnd = 1;
        }

        public long countOrders() {
            return root.countOrders();
        }

        static class Node {
            public int isEnd; // 0: false, 1 : true
            private Node[] children = new Node[26];
            private int size = 0;

            public Node add(char c) {
                if (children[c-'A'] == null) {
                    children[c-'A'] = new Node();
                    size++;
                }
                return children[c-'A'];
            }

            public Node get(char c) {
                return children[c-'A'];
            }

            public long countOrders() {
                long res = Factorial.get(size + isEnd);
                for (Node next : children) {
                    if(next == null) continue;
                    res = (res * next.countOrders()) % MOD;
                }
                return res;
            }
        }
    }

    static class Factorial {
        static long[] dp = new long[3001];

        public static long get(int n) {
            if (dp[n] > 0) {
                return dp[n];
            } else if (n == 0) {
                return dp[0] = 1;
            }
            return dp[n] = (n * get(n - 1)) % MOD;
        }
    }
}