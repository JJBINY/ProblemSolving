package 못깬거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G5_치킨배달
 * https://www.acmicpc.net/problem/15686
 */
public class G5_치킨배달 {
    static int N;
    static int M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashSet<Integer> selected = new HashSet<>(); // 선택된 M개의 치킨집 idx
    static int minChickenDist;
    static List<PriorityQueue<ChickenDist>> distMapper;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // NxN 도시
        M = Integer.parseInt(st.nextToken()); // 최대 M개의 치킨집

        List<Point> house = new ArrayList<>();
        List<Point> chicken = new ArrayList<>();
        readInputData(house, chicken);

        distMapper = getMapper(house, chicken);

        minChickenDist = Integer.MAX_VALUE;
        calculateMinChickenDist(chicken);
        System.out.println(minChickenDist);
    }

    private static void calculateMinChickenDist(List<Point> chicken) {
        selectMFrom(chicken,0,0);
    }

    private static void selectMFrom(List<Point> chicken, int start, int depth) {
        if (depth == M) {
            int chickenDist = getChickenDist(distMapper);
            if (chickenDist < minChickenDist) {
                minChickenDist = chickenDist;
            }
            return;
        }

        for (int cIdx = start; cIdx < chicken.size(); cIdx++) {
            if (!selected.add(cIdx)) {
                continue;
            }
            selectMFrom(chicken,cIdx+1,depth+1);
            selected.remove(cIdx);
        }
    }


    private static int getChickenDist(List<PriorityQueue<ChickenDist>> distMapper) {
        int chickenDist = 0;
        Iterator<PriorityQueue<ChickenDist>> pqIter = distMapper.iterator();
        while (pqIter.hasNext()) {
            PriorityQueue<ChickenDist> pq = pqIter.next();
            Iterator<ChickenDist> distIter = pq.iterator();
            while (distIter.hasNext()){
                ChickenDist cd = distIter.next();
                if (selected.contains(cd.cIdx)) {
                    chickenDist += cd.dist;
                    break;
                }
            }
        }
        return chickenDist;
    }

    private static List<PriorityQueue<ChickenDist>> getMapper(List<Point> house, List<Point> chicken) {
        List<PriorityQueue<ChickenDist>> distMapper = new ArrayList<>();
        for (int i = 0; i < house.size(); i++) {
            Point h = house.get(i);
            PriorityQueue<ChickenDist> pq = new PriorityQueue<>(Comparator.comparing(c->c.dist));
            for (int cIdx = 0; cIdx < chicken.size(); cIdx++) {
                int distance = h.getDistanceFrom(chicken.get(cIdx));
                ChickenDist chickenDist = new ChickenDist(cIdx, distance);
                pq.add(chickenDist);
            }
            distMapper.add(pq);
        }
        return distMapper;
    }

    private static void readInputData(List<Point> house, List<Point> chicken) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int point = Integer.parseInt(st.nextToken());
                if (point == 1) {
                    house.add(new Point(i, j));
                } else if (point == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistanceFrom(Point target) {
            return Math.abs(target.x - x) + Math.abs(target.y - y);
        }
    }

    private static class ChickenDist {
        int cIdx;
        int dist;

        public ChickenDist(int cIdx, int dist) {
            this.cIdx = cIdx;
            this.dist = dist;
        }
    }
}