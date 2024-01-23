package 백준.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S2 3005 PRVA
 * 문자열
 */
public class S2_3005_PRVA {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = parseInt(st.nextToken());
        int C = parseInt(st.nextToken());
        char[][] arr = new char[R][C];
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                arr[i][j] = c;
                if (c == '#') {
                    if (sb.length() > 1) {
                        pq.add(sb.toString());
                    }
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 1) {
                pq.add(sb.toString());
            }
        }
        br.close();

        for (int i = 0; i < C; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < R; j++) {
                char c = arr[j][i];
                if (c == '#') {
                    if (sb.length() > 1) {
                        pq.add(sb.toString());
                    }
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 1) {
                pq.add(sb.toString());
            }
        }
        System.out.println(pq.poll());
    }

}
