package 못깬거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://www.acmicpc.net/problem/1916
 */
public class G5_최소비용구하기 {
    static int INF = 2100000000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        int cityNum = Integer.parseInt(br.readLine());
        int busNum = Integer.parseInt(br.readLine());

        List<City> cities = IntStream.range(0, cityNum)
                .mapToObj(e -> new City(e))
                .collect(Collectors.toList());

        addBuses(busNum, cities);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken())-1;
        int to = Integer.parseInt(st.nextToken())-1;

        System.out.println(findMinimumArriveCost(cities, from, to));
    }
    private static void addBuses(Integer busNum, List<City> cities) throws IOException {
        for (int i = 0; i < busNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int fare = Integer.parseInt(st.nextToken());
            Bus bus = new Bus(from,to,fare);
            cities.get(from).buses.add(bus);
        }
    }

    static class DTO {
        int cityNum;
        int cost;
        public DTO(int cityNum, int cost) {
            this.cityNum = cityNum;
            this.cost = cost;
        }
    }
    
    private static int findMinimumArriveCost(List<City> cities, int from, int to) {
        int visited = 0;
        List<Integer> dist = new ArrayList<>();
        for (int i = 0; i <cities.size() ; i++) {
            if (i == from) {
                dist.add(0);
            }else{
                dist.add(INF);
            }
        }
        Queue<DTO>pq = new PriorityQueue<>(Comparator.comparing(dto -> dto.cost));
        for (int i = 0; i < dist.size(); i++) {
            pq.add(new DTO(i, dist.get(i)));
        }

        while (!pq.isEmpty()) {
            DTO dto = pq.poll();
            City nowCity = cities.get(dto.cityNum);
            int nowCost = dto.cost;

            if (nowCity.num == to) {
                return nowCost;
            }
            
            if ((visited & (1 << nowCity.num)) > 0) {continue;}
            visited = visited | (1 << nowCity.num);

            for (Bus nowBus : nowCity.buses) {
                City nextCity = cities.get(nowBus.to);

                int cost = nowCost + nowBus.fare;
                if (cost < dist.get(nextCity.num)) {
                    dist.set(nextCity.num,cost);
                    pq.add(new DTO(nextCity.num, cost));
                }
            }
        }
        return -1;
    }

    static class City {
        int num;

        List<Bus> buses = new ArrayList();

        public City(int num) {
            this.num = num;
        }
    }

    static class Bus {
        int from;
        int to;
        int fare;

        public Bus(int from, int to, int fare) {
            this.from = from;
            this.to = to;
            this.fare = fare;
        }
    }
}