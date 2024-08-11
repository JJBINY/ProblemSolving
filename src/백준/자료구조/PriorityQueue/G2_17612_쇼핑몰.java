package 백준.자료구조.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G2_17612_쇼핑몰
 * 우선순위 큐, 구현
 */
public class G2_17612_쇼핑몰 {
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

    /*
    - N명의 고객과 K개의 계산대
    - 고객마다 id와 구입한 물건의 수 w가 주어짐
    - 물건 종류에 무관하게 물건 하나당 계산에 1분 소요
        - 물건 w개 계산하면 w분 소요

    Approach 1) 단순 시뮬레이션
    안내원 -> 우선순위 큐 (계산 빨리 끝나는 순, 계산대 번호 높은 순)
    각 계산대별 큐 구현

    1. 안내원이 손님들을 적절한 계산대에 할당
    1-1. 안내원이 우선순위 큐에서 계산대 하나 꺼냄
    1-2. 해당 계산대 큐에 손님 삽입 (이 때, 손님 별 계산 종료 시간 기록)
    1-3. 계산대 정보 갱신 (계산 종료 시간)
    1-4. 우선순위 큐에 재삽입
    1-5. 모든 손님이 계산대에 할당될 때까지 1-1부터 반복

    2. 손님들이 빠져나가는 순서 구하기
    2-1. 손님 객체들을 정렬하는 우선순위 큐 생성 (계산 종료 시간 빠른 순, 계산대 번호 큰 순)
    2-2. 모든 계산대 큐에서 손님 객체를 꺼내어 해당 우선순위 큐에 삽입
    2-3. 우선순위 큐가 빌 때까지 하나씩 우선순위 큐에서 꺼내며 정답 갱신

     */

    static Object solve(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Counter> counterPQ = new PriorityQueue<>(Comparator
                .comparingInt((Counter c) -> c.last.outAt)
                .thenComparingInt(c -> c.id));

        for (int i = 0; i < K; i++) {
            counterPQ.offer(new Counter(i));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Counter counter = counterPQ.poll();
            counter.addCustomer(new Customer(id, w));
            counterPQ.offer(counter);
        }

        PriorityQueue<Customer> customerPQ = new PriorityQueue<>(Comparator
                .comparingInt((Customer c) -> c.outAt)
                .thenComparingInt(c -> -c.counterId));

        counterPQ.stream()
                .flatMap(counter -> counter.queue.stream())
                .forEach(customer -> customerPQ.add(customer));

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            ans += (long) customerPQ.poll().id * i; // 오버플로우 주의
        }

        return ans;
    }

    static class Customer{
        int id;
        int w;
        int outAt;
        int counterId;

        public Customer(int id, int w) {
            this.id = id;
            this.w = w;
        }
    }

    static class Counter{
        static Customer initC = new Customer(0, 0);

        int id;
        Customer last = initC;
        Deque<Customer> queue = new ArrayDeque<>();

        public Counter(int id) {
            this.id = id;
        }

        public void addCustomer(Customer c){

            c.outAt = last.outAt + c.w;
            c.counterId = id;
            queue.offer(c);
            last = c;
        }
    }
}