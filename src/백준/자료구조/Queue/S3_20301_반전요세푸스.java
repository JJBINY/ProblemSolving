package 백준.자료구조.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S3 20301 반전 요세푸스
 * 자료구조, Deque, 구현
 */
public class S3_20301_반전요세푸스 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            deque.add(i + 1);
        }

        StringBuilder sb = new StringBuilder();
        boolean direction = false;
        for (int i = 0; i < N; i++) {
            if(i%M == 0){
                direction = !direction;
//                System.out.println("i = " + i);
            }
            if(direction) {
                for (int j = 0; j < K - 1; j++) {
                    deque.addLast(deque.pollFirst());
                }
                sb.append(deque.pollFirst()).append("\n");
            }else{
                for (int j = 0; j < K - 1; j++) {
                    deque.addFirst(deque.pollLast());
                }
                sb.append(deque.pollLast()).append("\n");
            }

        }
        System.out.println(sb.toString());

    }
}