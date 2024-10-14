package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * P3_16903_수열과_쿼리_20
 * 트라이, 비트연산
 */
public class P3_16903_수열과_쿼리_20 {
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
        Trie trie = new Trie();
        trie.insert(Integer.toBinaryString(0));

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            String binary = Integer.toBinaryString(num);
            switch (cmd) {
                case 1:
                    trie.insert(binary);
                    break;
                case 2:
                    trie.remove(binary);
                    break;
                case 3:
                    sb.append(trie.searchMax(binary)).append("\n");
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        return sb;
    }

    static class Trie {
        private Trie[] next = new Trie[2];
        int cnt;

        public void insert(String binary) {
            Trie node = this;
            // 10^9 < 2^30
            for (int i = 0; i < 30 - binary.length(); i++) {
                if (node.next[0] == null) {
                    node.next[0] = new Trie();
                }
                node = node.next[0];
                node.cnt++;
            } //

            for (int i = 0; i < binary.length(); i++) {
                int bit = binary.charAt(i) - '0';
                if (node.next[bit] == null) {
                    node.next[bit] = new Trie();
                }
                node = node.next[bit];
                node.cnt++;
            } //
        } //

        public void remove(String binary) {
            Trie node = this;
            // binary가 존재하는 경우만 호출됨
            for (int i = 0; i < 30 - binary.length(); i++) {
                node = node.next[0];
                node.cnt--;
            } //

            for (int i = 0; i < binary.length(); i++) {
                int bit = binary.charAt(i) - '0';
                node = node.next[bit];
                node.cnt--;
            } //
        } //

        public int searchMax(String binary) {
            int result = 0;
            Trie node = this;
            for (int i = 0; i < 30 - binary.length(); i++) {
                int bit = node.next[1] != null && node.next[1].cnt > 0 ? 1 : 0;
                result = (result << 1) | bit;
                node = node.next[bit];
            } //

            for (int i = 0; i < binary.length(); i++) {
                int bit = binary.charAt(i) - '0';
                int counterBit = bit ^ 1;
                result = result << 1;
                if (node.next[counterBit] != null && node.next[counterBit].cnt > 0) {
                    result = result | 1;// bit^counterBit = 1
                    node = node.next[counterBit];
                }else{
                    node = node.next[bit];
                }
            }
            return result;
        }
    }
}