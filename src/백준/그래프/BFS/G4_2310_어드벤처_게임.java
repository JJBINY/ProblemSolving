package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G4_2310_어드벤처_게임
 * 그래프, BFS
 */
public class G4_2310_어드벤처_게임 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int N;
            while ((N = Integer.parseInt(br.readLine())) != 0) {
                ans.append(solve(N, br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(int N, BufferedReader br) throws IOException {

        Room.init(N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int amount = Integer.parseInt(st.nextToken());
            List<Integer> edges = new ArrayList<>();
            int roomId;
            while ((roomId = Integer.parseInt(st.nextToken())) != 0) {
                edges.add(roomId);
            }
            Room.add(i + 1, type, amount, edges);
        }

        boolean[][] visited = new boolean[N + 1][501];
        visited[0][0] = true;

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{0, 0});

        while (!dq.isEmpty()){
            Room cur = Room.of(dq.peek()[0]);
            int amount = dq.poll()[1];

            if(cur.equals(Room.of(N))){
                return "Yes";
            }

            for (int nextId : cur.edges) {
                Room next = Room.of(nextId);
                int nextAmount = amount;
                if(next.type.equals("T")){
                    nextAmount -= next.amount;
                }else{
                    nextAmount = Math.max(nextAmount, next.amount);
                }

                if(nextAmount < 0) continue;
                if(visited[nextId][nextAmount]) continue;
                visited[nextId][nextAmount] = true;
                dq.offer(new int[]{nextId, nextAmount});
            }
        }

        return "No";
    }

    static class Room{

        static Room[] rooms;
        String type;
        int amount;
        List<Integer> edges;
        static void init(int N){
            rooms = new Room[N+1];
            rooms[0] = new Room("Start", 0, List.of(1));
        }

        static Room of(int id){
            return rooms[id];
        }

        static void add(int id, String type, int amount, List<Integer> edges){
            rooms[id] = new Room(type, amount, edges);
        }

        private Room(String type, int amount, List<Integer> edges) {
            this.type = type;
            this.amount = amount;
            this.edges = edges;
        }
    }
}