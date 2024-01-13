package 프로그래머스.카카오.Lv4;

import java.util.*;

public class Lv4_자동완성 {


    public int solution(String[] words) {

        Trie trie = new Trie();
        Arrays.stream(words).forEach(trie::add);
        return trie.count();
    }

    static class Trie{
        private Node head = Node.of('1');

        public void add(String word){
            Node now = head;
            for (char c : word.toCharArray()) {
                if(!now.hasChildOf(c)){
                    now.addChild(Node.of(c));
                }
                now = now.getChildOf(c);
                now.addPrefix(word);
            }
            now.addPrefix(word);
            now.isWord = true;
        }
        public int count(){
            return head.children.values().stream().mapToInt(this::count).sum();
        }
        private int count(Node now){
            if(!now.hasAnySamePrefix()) return now.depth;
            int cnt = 0;
            if(now.isWord) cnt += now.depth;

            System.out.println(now.val + " = " + now.children.size());
            for (Node node : now.children.values()) {
                System.out.print("node.val = " + node.val);
                System.out.println(" cnt = " + cnt);

                cnt += count(node);

            }
            return cnt;
        }

    }

    static class Node{
        private char val;
        private Set<String> prefixes = new HashSet<>();
        private int depth = 0;
        private Map<Character, Node> children = new HashMap<>();
        boolean isWord = false;

        private Node(char val) {
            this.val = val;
        }

        static public Node of(char val){
            return new Node(val);
        }
        public void addPrefix(String data){
            prefixes.add(data);
        }

        public boolean hasChildOf(char c){
            return children.containsKey(c);
        }

        public void addChild(Node child){
            child.depth = this.depth + 1;
            children.put(child.val, child);
        }

        public Node getChildOf(char c){
            return children.get(c);
        }

        public boolean hasAnySamePrefix(){
            return prefixes.size() > 1;
        }
    }
}