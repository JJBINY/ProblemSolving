package 백준.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G5_14891_톱니바퀴
 * 구현, 시뮬레이션
 */
public class G5_14891_톱니바퀴 {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            List<Gear> gears = new ArrayList<>();
            for (int i = 0; i < 4; i++) {

                gears.add(new Gear(br.readLine()));
//                System.out.println(Arrays.toString(gears.get(i).arr));
            }

            int K = Integer.parseInt(br.readLine());
            while (K-- > 0) {
                List<Gear> forward = new ArrayList<>();
                List<Gear> reverse = new ArrayList<>();
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken()) - 1;
                boolean clockwise = st.nextToken().equals("1") ? true : false;
                classify(forward, reverse, clockwise, gears.get(idx));

                searchRight(gears, forward, reverse, idx, clockwise);
                searchLeft(gears, forward, reverse, idx, clockwise);

                forward.stream().forEach(g -> g.rotate(true));
                reverse.stream().forEach(g -> g.rotate(false));

//                System.out.println(K);
//                for (Gear gear : gears) {
//                    System.out.println(Arrays.toString(gear.arr));
//                }
            } // for K

            int score = 0;
            int x = 1;
            for (int i = 0; i < 4; i++) {
                Gear gear = gears.get(i);
                if (gear.arr[0] == 1) {
                    score += x;
                }
                x = x << 1;

            }

            System.out.println(score);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchLeft(List<Gear> gears, List<Gear> forward, List<Gear> reverse, int idx, boolean clockwise) {
        boolean d = !clockwise;
        Gear prev = gears.get(idx);
        Gear now = null;

        for (int i = idx - 1; i > -1; i--) {
            now = gears.get(i);
            if (prev.getLeftHole() == now.getRightHole()) {
                break;
            }
            classify(forward, reverse, d, now);

            d = !d;
            prev = now;
        } // 좌측 탐색
    }

    private static void searchRight(List<Gear> gears, List<Gear> forward, List<Gear> reverse, int idx, boolean clockwise) {
        boolean d = !clockwise;
        Gear prev = gears.get(idx);
        Gear now = null;
        for (int i = idx + 1; i < 4; i++) {
            now = gears.get(i);
            if (prev.getRightHole() == now.getLeftHole()) {
                break;
            }
            classify(forward, reverse, d, now);

            d = !d;
            prev = now;
        } // 우측 탐색
    }

    private static void classify(List<Gear> forward, List<Gear> reverse, boolean d, Gear r) {
        if (d) { // clockwise
            forward.add(r);
        } else {
            reverse.add(r);
        }
    }

    static class Gear {
        static final int r = 2, l = 6;
        int[] arr;

        public Gear(String s) {
            arr = Arrays.stream(s.split("")).mapToInt(Integer::parseInt).toArray();
        }

        // 방향 생각
        void rotate(boolean clockwise) {
            if (clockwise) { // 오른쪽으로 시프트
                int tmp = arr[arr.length - 1];
                for (int i = arr.length - 1; i > 0; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[0] = tmp;
            } else { // 왼쪽으로 시프트
                int tmp = arr[0];
                for (int i = 0; i < arr.length - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = tmp;
            }
        }

        int getLeftHole() {
            return arr[l];
        }

        int getRightHole() {
            return arr[r];
        }
    }
}