package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G4 1327 소트 게임
 * BFS, 해시
 */
public class G4_1327_소트게임 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N,K;
    static int[] arr;
    static String ansStr;

    static void solve(BufferedReader br) throws IOException {
        init(br);
        ansStr = getAnsString();
        System.out.println(bfs());
    }

    private static int bfs() {
        Set<String> visitSet = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
        }

        visitSet.add(sb.toString());
        q.add(new Pair(sb.toString(), 0));

        while (!q.isEmpty()){
            String now = q.peek().str;
            int cnt = q.poll().cnt;
            if(now.equals(ansStr)){
                return cnt;
            }

            for (int i = 0; i < N - K + 1; i++) {
                String next = getNextString(now, i);
                if(!visitSet.add(next)) continue;
                q.offer(new Pair(next, cnt + 1));
            } // for i
        } // while q

        return -1;
    }

    private static String getNextString(String now, int i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sb.append(now.charAt(j));
        } // for 0 to i
        for (int j = i +K-1; j >= i; j--) {
            sb.append(now.charAt(j));
        } // for i to i+K reverse
        for (int j = i +K; j < N; j++) {
            sb.append(now.charAt(j));
        } //for i+K to N
        return sb.toString();
    }

    private static String getAnsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(i);
        }
        return sb.toString();
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }
    }

    static class Pair{
        String str;
        int cnt;

        public Pair(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }
}
