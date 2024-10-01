package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P3_5446_용량_부족
 * 자료구조, 문자열, 트리, 트라이
 */
public class P3_5446_용량_부족 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
//            int T = 1;
            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    지워야 할 파일이 아니라 지우면 안되는 파일을 기준으로 Trie를 생성하면 쉽게 풀 수 있다.

    지워야 할 파일들을 Trie에서 탐색했을 때, 중간에 다음 노드를 찾을 수 없는 경우 해당 위치에서 와일드 카드를 사용하여 삭제 연산을 진행한다.

    - ex) Trie에 BAPC까지 삽입되어 있는 경우 BAPC.을 탐색하는 순간 null이 반환되고 rm BAPC.* 명령어를 수행하는 것이 최적이다.

    HashSet을 사용하여 중복을 제거해 주면 set의 size가 수행해야 할 최소 명령어 수가 된다.
     */
    static Set<String> set = new HashSet<>();
    static Object solve(BufferedReader br) throws IOException {
        set.clear();
        int N1 = Integer.parseInt(br.readLine());
        List<String> removeList = new ArrayList<>();
        for (int i = 0; i < N1; i++) {
            removeList.add(br.readLine());
        }

        int N2 = Integer.parseInt(br.readLine());
        if(N2 == 0){
            return 1;
        }
        Trie trie = new Trie();
        for (int i = 0; i < N2; i++) {
            String fileName = br.readLine();
            trie.insert(fileName);
        }

        for (String fileName : removeList) {
            set.add(trie.search(fileName));
        }

//        System.out.println("set = " + set);
        return set.size();
    }

    static class Trie {
        private Node root = new Node();

        public void insert(String fileName) {
            Node node = root;
            for (char c : fileName.toCharArray()) {
                node = node.add(c);
            }
        }

        public String search(String fileName) {
            Node node = root;
            StringBuilder sb = new StringBuilder();
            for (char c : fileName.toCharArray()) {
                sb.append(c);
                node = node.get(c);
                if(node == null){
                    break;
                }
            }
            return sb.toString();
        }

        static class Node{
            private Map<Character, Node> map = new HashMap<>();

            public Node add(char c) {
                if (!map.containsKey(c)) {
                    map.put(c, new Node());
                }
                return map.get(c);
            }

            public Node get(char c) {
                return map.get(c);
            }
        }
    }
}