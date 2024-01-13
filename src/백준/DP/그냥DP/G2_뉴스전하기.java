package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * G2 뉴스전하기
 * https://www.acmicpc.net/problem/1135
 */
public class G2_뉴스전하기 {

    static List<Node> nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        nodes = IntStream.range(0, n).mapToObj(Node::new).collect(Collectors.toList());
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 1; i < n; i++) {
            Node parent = nodes.get(Integer.parseInt(st.nextToken()));
            Node child = nodes.get(i);
            parent.children.add(child);
            child.parent = parent;
        }
        System.out.println(getPTime(nodes.get(0)));
    }

    static int getPTime(Node now){
        if(now.pTime>-1) return now.pTime;
        if(now.children.isEmpty()){
            return now.pTime = 0;
        }

        for (Node child : now.children) {
            getPTime(child);
        }
        now.children.sort(Comparator.comparingInt((Node n) -> n.pTime).reversed());
        int i = 1;
        for (Node child : now.children) {
            now.pTime = Math.max(now.pTime, child.pTime+i++);
        }
        return now.pTime;
    }

    static class Node{
        int id;
        int pTime = -1;
        Node parent;
        List<Node> children = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

    }
}
