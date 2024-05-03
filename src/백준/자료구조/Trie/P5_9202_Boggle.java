package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;


/**
 * P5 9202 Boggle
 * 트라이, 백트래킹
 */
public class P5_9202_Boggle {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String longestWord;
    static int cnt, score, round;
    static int[][] table;
    static boolean[][] visited;
    static int[] dr = {0, 1, -1, 0, 1, -1, 1, -1};
    static int[] dc = {1, 0, 0, -1, 1, 1, -1, -1};
    static Set<String> wordSet = new HashSet<>();

    static void solve(BufferedReader br) throws IOException {
        Trie.init();
        int w = parseInt(br.readLine());
        for (int i = 0; i < w; i++) {
            Trie.insert(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        br.readLine();
        int b = parseInt(br.readLine());
        while (b-- > 0) {
            table = new int[4][4];
            for (int i = 0; i < 4; i++) {
                String line = br.readLine();
                for (int j = 0; j < 4; j++) {
                    table[i][j] = line.charAt(j) - 'A';
                }
            } // for i

            find();
            sb.append(score).append(" ")
                    .append(longestWord).append(" ")
                    .append(cnt);
            if (b > 0) {
                br.readLine();
            }
            sb.append("\n");
        } // while

        System.out.println(sb.toString());
    }

    private static void find() {
        longestWord = "";
        cnt = 0;
        score = 0;
        Trie now = Trie.root;
        wordSet.clear();

        round++;
        visited = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (now.next[table[i][j]] != null && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(now.next[table[i][j]], i, j, 1);
                    visited[i][j] = false;
                }
            } // for j
        } // for i
    }

    static void dfs(Trie now, int r, int c, int depth) {
        if (depth > 8) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
            Trie next = now.next[table[nr][nc]];
            if (next == null) continue;
            if (visited[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(next, nr, nc, depth + 1);
            visited[nr][nc] = false;

        } // for i

        if (now.isEnd && wordSet.add(now.word)) {
            score += getScore(now.word);
            if (now.word.length() > longestWord.length()) {
                longestWord = now.word;
            } else if (now.word.length() == longestWord.length() &&
                    now.word.compareTo(longestWord) < 0) {
                longestWord = now.word;
            }
            cnt++;
        }
    }

    private static int getScore(String word) {
        int len = word.length();
        if (len == 8) {
            return 11;
        } else if (len == 7) {
            return 5;
        } else if (len == 6) {
            return 3;
        } else if (len == 5) {
            return 2;
        } else if (len >= 3) {
            return 1;
        }
        return 0;
    }

    static class Trie {
        private static Trie root;
        private Trie[] next = new Trie[26];
        private boolean isEnd;
        private String word;
        private int visited;

        static void init() {
            root = new Trie();
        }

        static void insert(String str) {
            Trie now = root;
            for (char c : str.toCharArray()) {
                int num = c - 'A';
                if (now.next[num] == null) {
                    now.next[num] = new Trie();
                }
                now = now.next[num];
            }
            now.isEnd = true;
            now.word = str;
        }
    }
}

/*
5
ICPC
ACM
CONTEST
GCPC
PROGRAMM

1
PCMM
RXAI
ORCN
GPCG
 */