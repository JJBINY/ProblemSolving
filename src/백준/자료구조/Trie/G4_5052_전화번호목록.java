package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;


/**
 * G4 5052 전화번호 목록
 */
public class G4_5052_전화번호목록 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                solve(br);
            }
            System.out.print(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static StringBuilder sb = new StringBuilder();

    static void solve(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        boolean failed = false;
        Trie.init();
        for (int i = 0; i < n; i++) {
            String phone = br.readLine();
            if(!failed) failed = !Trie.insert(phone);
        }
        if(failed) {
            sb.append("NO\n");
        }else{
            sb.append("YES\n");
        }
    }

    static class Trie {
        private static Trie root;
        private Trie[] next = new Trie[10];
        private boolean isEnd;
        private int nChild;

        static void init(){
            root = new Trie();
        }
        static boolean insert(String phone) {
            Trie now = root;
            for (char c : phone.toCharArray()) {
                int num = c - '0';
                if (now.next[num] == null) {
                    now.next[num] = new Trie();
                    now.nChild++;
                }
                now = now.next[num];
                if (now.isEnd) return false;
            }
            now.isEnd = true;
            if(now.nChild>0) return false;
            return true;
        }
    }
}
