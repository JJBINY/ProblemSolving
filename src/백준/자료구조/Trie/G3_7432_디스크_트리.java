package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * G3_7432_디스크_트리
 * 자료구조, 문자열, 트리, 트라이
 */
public class G3_7432_디스크_트리 {
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
        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            String path = br.readLine();
            trie.insert(path);
        }

        return trie.getDirTree();
    }

    static class Trie {
        private Node root = new Node("root");

        public void insert(String path) {
            String[] directories = path.split("\\\\");
            Node node = root;
            for (String dir : directories) {
                node = node.add(dir);
            }
        }

        public String getDirTree() {
            StringBuilder sb = new StringBuilder();
            root.map.values().stream()
                    .sorted()
                    .forEach(node -> sb.append(node.getDirTree("")));
            return sb.toString();
        }

        static class Node implements Comparable{
            private String dir;
            private Map<String, Node> map = new HashMap<>();

            public Node(String dir) {
                this.dir = dir;
            }

            public Node add(String dir) {
                if (!map.containsKey(dir)) {
                    map.put(dir, new Node(dir));
                }
                return map.get(dir);
            }

            public String getDirTree(String space) {
                StringBuilder sb = new StringBuilder();
                sb.append(space).append(dir).append("\n");
                map.values().stream()
                        .sorted()
                        .forEach(node -> sb.append(node.getDirTree(space+" ")));
                return sb.toString();
            }

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof Node)) {
                    throw new IllegalArgumentException();
                }
                Node n = (Node) o;
                return dir.compareTo(n.dir);
            }
        }
    }
}