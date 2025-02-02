package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.compare;


/**
 * S2_20006_랭킹전_대기열
 * 구현, 시뮬레이션
 */
public class S2_20006_랭킹전_대기열 {

    /*
        1. 입장 가능한 방이 있는 지 조회
        - 방 생성 플레이어 레벨을 fl이라 할 때, 입장 가능 레벨 범위 = [fl-10,fl+10]
        - 방의 정원이 다 차지 않아야 함
        2. 입장 가능한 방이 없다면 새로운 방을 만든다
        3. 입장 가능한 방이 여러개라면 먼저 생성된 방에 입장
        - 리스트로 방 관리
     */

    static int m;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken()); // 전체 플레이어 수
        m = Integer.parseInt(st.nextToken()); // 방의 정원 수
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            Player player = new Player(l, n);
            rooms.stream().filter(r -> r.canJoin(player)).findFirst()
                    .ifPresentOrElse(r -> r.join(player), () -> {
                        rooms.add(Room.createRoom(player));
                    });
        }

        StringBuilder result = new StringBuilder();
        for (Room room : rooms) {
            result.append(room.print()).append("\n");
        }

        return result.toString();
    }

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

    static class Player {
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }

    static class Room {
        PriorityQueue<Player> pq = new PriorityQueue<>(Comparator.comparing(p -> p.name));
        int lowerLimit;
        int upperLimit;
        int numberLimit;

        public static Room createRoom(Player player) {
            Room room = new Room();
            room.lowerLimit = player.level - 10;
            room.upperLimit = player.level + 10;
            room.numberLimit = m;
            room.join(player);
            return room;
        }

        public boolean canJoin(Player player) {
            return checkLevel(player) & pq.size() < numberLimit;
        }

        private boolean checkLevel(Player player) {
            return compare(lowerLimit, player.level) * compare(upperLimit, player.level) <= 0;
        }

        public void join(Player player) {
            pq.add(player);
        }

        public String print(){
            StringBuilder sb = new StringBuilder();
            if(pq.size() == numberLimit){
                sb.append("Started!");
            }else{
                sb.append("Waiting!");
            }

            while (!pq.isEmpty()){
                Player player = pq.poll();
                sb.append("\n").append(player.level).append(" ").append(player.name);
            }

            return sb.toString();
        }
    }
}