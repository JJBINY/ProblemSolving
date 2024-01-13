package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * P4 휴대폰 자판
 * https://www.acmicpc.net/problem/5670
 */
public class P4_휴대폰자판 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringBuilder sb = new StringBuilder();
        while ((input = br.readLine()) != null) {

            int n = Integer.parseInt(input);
            String[] words = new String[n];
            Trie trie = new Trie();
            for (int i = 0; i < n; i++) {
                words[i] = br.readLine();
                trie.insert(words[i]);
            }

            double ans = 0;
            for (int i = 0; i < n; i++) {
                ans += trie.fastSearch(words[i]);
            }
            ans /= n;
            sb.append(new BigDecimal(ans).setScale(2, RoundingMode.HALF_UP)).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new Node());
                }
                node = node.children.get(c);
            }
            node.isEndOfWord = true;
        }

        public int fastSearch(String word) {
            Node node = root;
            int cnt = 1;
            for (int i = 0; i < word.length()-1; i++) {
                char c = word.charAt(i);
                node = node.children.get(c);
                if (node.children.size() > 1) {
                    cnt++;
                } else if (i < word.length() - 1 && node.isEndOfWord) {
                    cnt++;
                }
            }
            return cnt;
        }

        private class Node {
            Map<Character, Node> children = new HashMap<>();
            boolean isEndOfWord;

        }
    }
}