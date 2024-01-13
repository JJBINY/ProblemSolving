package 프로그래머스.카카오.Lv3;


import java.util.*;
import java.util.stream.Collectors;

public class Lv3_셔틀버스 {
    /*
    한번에 m명 탑승 가능
    09:00부터 t분 간격으로 n회 운행
    콘은 같은 시각에 도착한 크루 중 대기열 가장 뒤에 섬

    시간:분 -> 전부 분으로 변환
    9시 -> 540분

    timetable 시간 순으로 정렬 후 큐에 삽입
    1. [0:540]에 해당하는 m명 태움 (첫차 9시)
    2. [0:540+t*n]에 해당하는 m명 태우기를 마지막 셔틀까지 반복
    3. 마지막 셔틀에 빈자리 있다면 마지막 셔틀의 도착시간 리턴
    4. 빈자리가 없다면

    0910 4명
    0909 0910 0910 0910 -> 0909

    0910 4명
    0909 0909 0909 0909 -> 0908

    0910 4명
    0905 0910 0910 0910 -> 0909

    0910 4명
    0905 0907 0910 0910 -> 0909
    */

    public String solution(int n, int t, int m, String[] timetable) {
        Queue<Integer> queue = Arrays.stream(timetable).sorted()
                .mapToInt(this::getMinute).boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        List<List<Integer>> shuttles = getShuttles(n, t, m, queue);

        if (hasEmptySeat(m, shuttles.get(n - 1))) {
            return getStringTime(540 + t * (n-1));
        } else {
            List<Integer> shuttle = shuttles.get(n - 1);
            int idx = m-1;
            while (idx >0 && shuttle.get(idx).equals(shuttle.get(idx-1))){
                idx -= 1;
            }
            return getStringTime(shuttle.get(idx)-1);
        }

    }

    private static String getStringTime(int ans) {
        int hour = ans / 60;
        int minute = ans % 60;
        StringBuilder sb = new StringBuilder();
        sb.append(hour / 10);
        sb.append(hour % 10);
        sb.append(":");
        sb.append(minute / 10);
        sb.append(minute % 10);
        return sb.toString();
    }

    private static boolean hasEmptySeat(int m, List<Integer> shuttle) {
        return shuttle.size() < m;
    }

    private static List<List<Integer>> getShuttles(int n, int t, int m, Queue<Integer> queue) {
        List<List<Integer>> shuttles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> shuttle = pickUpAt(t, m, queue, i);
            shuttles.add(shuttle);
        }
        return shuttles;
    }

    private static List<Integer> pickUpAt(int t, int m, Queue<Integer> queue, int i) {
        List<Integer> shuttle = new ArrayList<>();
        while (!queue.isEmpty()&& queue.peek() <= 540 + t * i && shuttle.size() < m ) {
            shuttle.add(queue.poll());
        }
        return shuttle;
    }

    public int getMinute(String time){
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}

