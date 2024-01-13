package 백준.자료구조.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * G3 개미굴
 * https://www.acmicpc.net/problem/14725
 */
public class G3_개미굴 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            List<String> feeds = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                feeds.add(st.nextToken());
            }
            Trie.insert(feeds);
        }
        Trie.print();
        br.close();
    }
    static class Trie{
        static private Node root = new Node();

        static public void insert(List<String> words){
            Node node = root;

            for (String word : words) {
                if(!node.children.containsKey(word)){
                    node.children.put(word, new Node());
                }
                node = node.children.get(word);
            }
        }

        static public void print(){
            printKey(root, "");
        }
        static private void printKey(Node node,String depth){
            for (String key : node.children.keySet()
                    .stream().sorted().collect(Collectors.toList())) {
                System.out.println(depth+key);
                printKey(node.children.get(key), depth + "--");
            }

        }
        static class Node{
            Map<String, Node> children = new HashMap<>();

        }
    }
}
