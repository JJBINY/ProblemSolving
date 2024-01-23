package 백준.그래프.트리.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 14267 회사 문화 1
 * 트리, DP
 */
public class G4_14267_회사문화1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 2; i <= N; i++) {
            Node.of(i).parent = Node.of(parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Node.of(parseInt(st.nextToken())).cnt += parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(Node.of(i).calculateCnt()).append(" ");
        }
        System.out.println(sb.toString());
        br.close();
    }
    static class Node{
        private static boolean[] isVisited = new boolean[100001];
        private static Node[] nodes = new Node[100001];
        int id;
        Node parent;
        int cnt;

        private Node(int id) {
            this.id = id;
        }

        public static Node of(int id){
            if(nodes[id] == null){
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }
        public int calculateCnt(){
            if(isVisited[id]) return cnt;
            if(parent == null) return 0;
            isVisited[id] = true;
            return cnt += parent.calculateCnt();
        }

    }

}
