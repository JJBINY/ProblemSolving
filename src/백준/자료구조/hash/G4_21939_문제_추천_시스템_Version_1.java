package 백준.자료구조.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G4_21939_문제_추천_시스템_Version_1
 * 자료구조, Map, 우선순위 큐
 */
public class G4_21939_문제_추천_시스템_Version_1 {
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

    static int N, M;
    static Map<Integer, Problem> problems;
    static PriorityQueue<Problem> maxHeap;
    static PriorityQueue<Problem> minHeap;

    static Object solve(BufferedReader br) throws IOException {
        config();

        N = Integer.parseInt(br.readLine()); // N <= 100,000
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int problemNo = Integer.parseInt(st.nextToken());
            int difficulty = Integer.parseInt(st.nextToken());
            add(problemNo, difficulty);
        }

        StringBuilder result = new StringBuilder();
        M = Integer.parseInt(br.readLine()); // M <= 10,000
        while (M-- > 0) {
            String[] commandLine = br.readLine().split(" ");
            handle(commandLine)
                    .ifPresent(problemNo -> result.append(problemNo).append("\n"));
        }

        return result;
    }

    private static void config() {
        problems = new HashMap<>();

        Comparator<Problem> problemComparator = Comparator
                .comparingInt((Problem p) -> -p.difficulty)
                .thenComparingInt(p -> -p.no);
        maxHeap = new PriorityQueue<>(problemComparator);
        minHeap = new PriorityQueue<>(problemComparator.reversed());
    }

    private static Optional<Integer> handle(String[] commandLine) {
        String commend = commandLine[0];
        switch (commend) {
            case "add": {
                int no = Integer.parseInt(commandLine[1]);
                int difficulty = Integer.parseInt(commandLine[2]);
                add(no, difficulty);
                break;
            }
            case "solved": {
                int no = Integer.parseInt(commandLine[1]);
                problems.get(no).removed = true;
                break;
            }
            case "recommend": {
                int x = Integer.parseInt(commandLine[1]);
                int no = x == 1 ? recommend(maxHeap) : recommend(minHeap);
                return Optional.of(no);
            }
            default:
                throw new IllegalArgumentException("잘못된 커맨드 입력");
        }

        return Optional.empty();
    }

    private static void add(int no, int difficulty) {
        Problem problem = new Problem(no, difficulty);
        problems.put(no, problem);
        maxHeap.offer(problem);
        minHeap.offer(problem);
    }

    private static int recommend(PriorityQueue<Problem> heap) {
        while (heap.peek().removed) {
            heap.poll();
        }
        return heap.peek().no;
    }

    static class Problem {
        int no;
        int difficulty;

        boolean removed;

        public Problem(int no, int difficulty) {
            this.no = no;
            this.difficulty = difficulty;
        }
    }
}