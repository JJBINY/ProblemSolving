package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * S1_14426_접두사찾기
 * 문자열, 트라이
 */
public class S1_14426_접두사찾기 {
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

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            trie.insert(br.readLine());
        }
        int ans = 0;
        for (int i = 0; i < M; i++) {
            if (trie.findableAny(br.readLine())) {
                ans++;
            }
        }
        return ans;
    }

    static class Trie{
        Trie[] children = new Trie[26];

        public void insert(String s){
            Trie now = this;
            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'a';
                if (now.children[idx] == null) {
                    now.children[idx] = new Trie();
                }
                now = now.children[idx];
            }
        }

        public boolean findableAny(String s){
            Trie now = this;
            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'a';
                if (now.children[idx] == null) {
                    return false;
                }
                now = now.children[idx];
            }
            return true;
        }

    }
}