package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G4_30885_Φsquare
 * 구현, 시뮬레이션, 연결리스트
 */
public class G4_30885_Φsquare {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int len;
    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Node head = new Node();
        Node tail = new Node();
        len = N;
        Node now = head;

        for (int i = 0; i < N; i++) {
            Node newNode = new Node();
            now.next = newNode;
            newNode.prev = now;
            newNode.micro = new Micro(i + 1, Integer.parseInt(st.nextToken()));
            now = newNode;
        }
        now.next = tail;

        now = head.next;
        while (len > 1) {
            long feed = 0;
            if(now.prev != head){
                Micro micro = now.prev.micro;

                if (now.micro.size >= micro.size) {
                    feed += micro.size;
                    remove(now.prev);
                }
            }

            if(now.next != tail){
                Micro micro = now.next.micro;

                if (now.micro.size >= micro.size) {
                    feed += micro.size;
                    remove(now.next);
                }
            }
            now.micro.size += feed;
            now = now.next;
            if(now == tail){
                now = head.next;
            }
        }
        Micro res = head.next.micro;
        StringBuilder sb = new StringBuilder();
        sb.append(res.size).append("\n").append(res.id);
        return sb;
    }

    private static void remove(Node now) {
        now.prev.next = now.next;
        now.next.prev = now.prev;
        len--;
    }

    static class Node{
        Node next;
        Node prev;
        Micro micro;
    }

    static class Micro{
        int id;
        long size;

        public Micro(int id, long size) {
            this.id = id;
            this.size = size;
        }
    }
}